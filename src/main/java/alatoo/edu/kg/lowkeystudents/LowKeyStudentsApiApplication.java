package alatoo.edu.kg.lowkeystudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LowKeyStudentsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LowKeyStudentsApiApplication.class, args);
    }

}
