package dev.shermende.game.service.feign.global;

import dev.shermende.reference.lib.api.MovementReasonApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "movementReason", value = "app-reference")
public interface MovementReasonGlobalService extends MovementReasonApiService {

}
