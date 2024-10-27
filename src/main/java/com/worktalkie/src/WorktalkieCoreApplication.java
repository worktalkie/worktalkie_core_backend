package com.worktalkie.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WorktalkieCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorktalkieCoreApplication.class, args);
    }

}
