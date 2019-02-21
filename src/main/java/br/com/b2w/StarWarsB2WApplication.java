package br.com.b2w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StarWarsB2WApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsB2WApplication.class, args);
    }

}
