package me.bazzi.jpareactor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
public class SchedulerConfiguration {
    private final Integer connectionPoolSize;

    public SchedulerConfiguration(@Value("${spring.datasource.hikari.maximum-pool-size}") Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    @Bean
    @ConditionalOnMissingBean(Scheduler.class)
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }

    /**
     * https://stackoverflow.com/questions/58513071/spring-mvc-to-spring-webflux-migration-block-vs-subscribe
     * @return
     */
    @Bean
    @Primary
    public Scheduler boundedElasticScheduler() {
        return Schedulers.newBoundedElastic(
                connectionPoolSize,
                connectionPoolSize / 2,
                "boundedElasticScheduler"
        );
    }

    private org.h2.tools.Server webServer;
    private org.h2.tools.Server server;

    @EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start();
        this.server = org.h2.tools.Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
        this.server.stop();
    }

}
