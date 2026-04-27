package dev.shermende.game.service.feign.global;

import dev.shermende.reference.lib.api.MovementScenarioApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "movementScenario", value = "app-reference")
public interface MovementScenarioGlobalService extends MovementScenarioApiService {

}
