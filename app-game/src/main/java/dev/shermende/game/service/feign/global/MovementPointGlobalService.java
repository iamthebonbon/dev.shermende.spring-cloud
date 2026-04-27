package dev.shermende.game.service.feign.global;

import dev.shermende.reference.lib.api.MovementPointApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "movementPoint", value = "app-reference")
public interface MovementPointGlobalService extends MovementPointApiService {

}
