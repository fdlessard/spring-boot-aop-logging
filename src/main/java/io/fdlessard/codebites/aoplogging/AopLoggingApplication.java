package io.fdlessard.codebites.aoplogging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AopLoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopLoggingApplication.class, args);
    }
}
