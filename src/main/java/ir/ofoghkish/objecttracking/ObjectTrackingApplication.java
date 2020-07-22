package ir.ofoghkish.objecttracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class ObjectTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectTrackingApplication.class, args);
    }

}
