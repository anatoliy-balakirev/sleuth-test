package com.sleuth.test.demo;

import brave.propagation.ExtraFieldPropagation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.sleuth.test.demo.Constants.USER_ID_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DemoController {

    private final WebClient webClient;

    @GetMapping("/demo")
    public Mono<String> getMessageFromContext() {
        ExtraFieldPropagation.set(USER_ID_KEY, "123");
        log.info("Sending request to another controller. Set userId as an extra field: {}", USER_ID_KEY);

        return webClient.get().uri("/userId").retrieve().bodyToMono(String.class).doOnNext(userId -> {
            log.info("User id from another controller is: {}", userId);
        });
    }

}
