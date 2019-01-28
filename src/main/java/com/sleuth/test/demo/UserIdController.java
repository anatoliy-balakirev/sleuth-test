package com.sleuth.test.demo;

import brave.propagation.ExtraFieldPropagation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.sleuth.test.demo.Constants.USER_ID_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserIdController {


    @GetMapping("/userId")
    public Mono<String> getMessage() {
        return Mono.fromCallable(() -> {
            log.info("Got into userId controller");
            return ExtraFieldPropagation.get(USER_ID_KEY);
        });
    }

}
