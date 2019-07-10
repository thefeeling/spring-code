package me.daniel.kotlinspringbootcloudstream

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@SpringBootApplication
@EnableScheduling
class KotlinSpringBootCloudStreamApplication {
    companion object {
        val topicExchangeName = "spring-boot-exchange"
        val queueName = "spring-boot"
    }

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(topicExchangeName)
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#")
    }

    @Bean
    fun container(
        connectionFactory: ConnectionFactory,
        listenerAdapter: MessageListenerAdapter
    ): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.setMessageListener(listenerAdapter)
        return container
    }

    @Bean
    fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootCloudStreamApplication>(*args)
}

@Component
class Receiver {

    val latch = CountDownLatch(1)

    fun receiveMessage(message: String) {
        println("Received <$message>")
        latch.countDown()
    }
}

@Component
class Scheduler(
    private val receiver: Receiver,
    private val rabbitTemplate: RabbitTemplate
) {

    @Scheduled(fixedDelay = 3000)
    fun enqueue(): Unit {
        println("Sending message...")
        rabbitTemplate.convertAndSend(KotlinSpringBootCloudStreamApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!")
        receiver.latch.await(10000, TimeUnit.MILLISECONDS)
    }

}


@Component
class ScheduledTasks {

    @Scheduled(fixedDelay = 2000)
    fun reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(Date()))
    }

    companion object {
        private val log = LoggerFactory.getLogger(ScheduledTasks::class.java)
        private val dateFormat = SimpleDateFormat("HH:mm:ss")
    }
}