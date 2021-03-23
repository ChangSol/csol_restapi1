package csol.restapi1.demo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration //어노테이션 환경 구축, (@Bean...)
@EnableTransactionManagement //트랜잭션 설정
@EnableJpaRepositories( //jpa repo 설정
        basePackages = "csol.restapi1.demo.repository",
        transactionManagerRef = "mariaDB_transactionManager",
        entityManagerFactoryRef = "mariaDB_entityManagerFactory"
)
public class DbConfig {
    @Primary
    @Bean(name = "maria_dataSource")//2
    @ConfigurationProperties(value = "spring.data.maria")
    public DataSource mariaDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "mariaDB_entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("maria_dataSource") DataSource dataSource
    ){
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder.dataSource(dataSource)
                .packages("csol.restapi1.demo.model")
                .properties(map)
                .build();
    }

    @Primary
    @Bean(name = "mariaDB_transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mariaDB_entityManagerFactory") EntityManagerFactory entityManagerFactory)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
