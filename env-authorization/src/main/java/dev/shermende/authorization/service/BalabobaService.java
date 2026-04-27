package dev.shermende.authorization.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(value = "BalabobaService", url = "https://zeapi.yandex.net")
public interface BalabobaService {

    @PostMapping("/lab/api/yalm/text3")
    Optional<String> introspect(
            @RequestBody String request
    );

}
