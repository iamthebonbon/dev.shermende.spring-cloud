package dev.shermende.game.service.feign;

import dev.shermende.game.resource.BalabobaRequestResource;
import dev.shermende.game.resource.BalabobaResponseResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(value = "BalabobaService", url = "https://zeapi.yandex.net")
public interface BalabobaService {

    @PostMapping("/lab/api/yalm/text3")
    Optional<BalabobaResponseResource> introspect(
            @RequestBody BalabobaRequestResource request
    );

}
