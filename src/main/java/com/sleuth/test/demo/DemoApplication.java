package com.sleuth.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import static com.sleuth.test.demo.Constants.USER_ID_KEY;

@Slf4j
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public WebClient myWebClient(final WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://localhost:8080")
            .filter((request, exchangeFunction) -> {
                final String userId = request.headers().getFirst(USER_ID_KEY);
                log.info("User id in filter is: {}", userId);
                return exchangeFunction.exchange(request);
            })
            .build();
    }

}

