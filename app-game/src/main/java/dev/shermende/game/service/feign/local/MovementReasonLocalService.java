package dev.shermende.game.service.feign.local;

import dev.shermende.reference.lib.api.MovementReasonApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(contextId = "movementReason", value = "app-reference", url = "${app-reference-api.url:http://127.0.0.1:8100}")
public interface MovementReasonLocalService extends MovementReasonApiService {

}
