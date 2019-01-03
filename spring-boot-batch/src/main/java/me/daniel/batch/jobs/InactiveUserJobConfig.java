package me.daniel.batch.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.daniel.batch.domain.User;
import me.daniel.batch.domain.enums.UserStatus;
import me.daniel.batch.repository.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Configuration
public class InactiveUserJobConfig {

    // 생성자 주입
    private UserRepository userRepository;
    private static final int CHUNK_SIZE = 5;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    @Bean
    public Job InactiveUserJob(
        JobBuilderFactory jobBuilderFactory,
        Step inactiveJobStep
    ) {
        return jobBuilderFactory.get("inactiveUserJob")
            .preventRestart()
            .start(inactiveJobStep)
            .build();
    }

    @Bean
    public Step inactiveJobStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("inactiveUserStep")
            .<User, User>chunk(CHUNK_SIZE)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }

//    @Bean
//    @StepScope
//    public ListItemReader<User> itemReader() {
//        List<User> oldUsers = userRepository.findByUpdatedDateBeforeAndStatusEquals(
//            LocalDateTime.now().minusYears(1), UserStatus.ACTIVE
//        );
//        return new ListItemReader<>(oldUsers);
//    }

//    @Bean(destroyMethod = "")
//    @StepScope
//    public JpaPagingItemReader<User> itemReader(@Value("#{jobParameters[nowDate]}") Date nowDate) {
//        JpaPagingItemReader<User> jpaPagingItemReader = new JpaPagingItemReader<User>() {
//            @Override
//            public int getPage() {
//                return 0;
//            }
//        };
//        jpaPagingItemReader.setQueryString(
//            "select u from User as u where u.updatedDate < :updatedDate and u.status = :status"
//        );
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("updatedDate", (LocalDateTime.ofInstant(nowDate.toInstant(), ZoneId.systemDefault())).minusYears(1));
//        map.put("status", UserStatus.ACTIVE);
//        jpaPagingItemReader.setParameterValues(map);
//        jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
//        jpaPagingItemReader.setPageSize(CHUNK_SIZE);
//        return jpaPagingItemReader;
//    }

//    @Bean(destroyMethod = "")
//    @StepScope
//    public JdbcPagingItemReader<User> itemReader() throws Exception {
//        Map<String, Object> parameterValues = new HashMap<>();
//        parameterValues.put("updatedDate", LocalDateTime.now().minusYears(1));
//        parameterValues.put("status", UserStatus.ACTIVE.toString());
//
//        return new JdbcPagingItemReaderBuilder<User>()
//            .fetchSize(CHUNK_SIZE)
//            .dataSource(dataSource)
//            .rowMapper(new BeanPropertyRowMapper<>(User.class))
//            .queryProvider(pagingQueryProvider(dataSource))
//            .parameterValues(parameterValues)
//            .name("jdbcPagingItemReader")
//            .build();
//    }
//

//    @Bean
//    public PagingQueryProvider pagingQueryProvider(DataSource dataSource) throws Exception {
//        SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
//        sqlPagingQueryProviderFactoryBean.setDataSource(dataSource);
//        sqlPagingQueryProviderFactoryBean.setSelectClause("idx, email, name, password, social_type, status, grade, created_date, updated_date");
//        sqlPagingQueryProviderFactoryBean.setFromClause("from user");
//        sqlPagingQueryProviderFactoryBean.setWhereClause("where updated_date < :updatedDate and status = :status");
//        sqlPagingQueryProviderFactoryBean.setSortKey("idx");
//        return sqlPagingQueryProviderFactoryBean.getObject();
//    }


    @Bean
    @StepScope
    public RepositoryItemReader<User> itemReader() {
        return new RepositoryItemReaderBuilder()
            .repository(userRepository)
            .methodName("findByUpdatedDateBeforeAndStatusEquals")
//            .pageSize(CHUNK_SIZE)
            .maxItemCount(CHUNK_SIZE)
            .arguments(Arrays.asList(LocalDateTime.now().minusYears(1), UserStatus.ACTIVE))
            .sorts(Collections.singletonMap("idx", Sort.Direction.ASC))
            .name("repositoryItemReader")
            .build();
    }

    public ItemProcessor<User, User> itemProcessor() {
        return User::setInactive;
    }

//    public ItemWriter<User> itemWriter() {
//        return ((List<? extends User> user) -> {
//            user.forEach(o -> log.info(">>>> user = {}", o));
//            userRepository.saveAll(user);
//        });
//    }

//    JpaItemWriter 작성
    public JpaItemWriter<User> itemWriter() {
        JpaItemWriter<User> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }

}
