package dev.shermende.reference.lib.api;

import dev.shermende.reference.lib.model.MovementReasonModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface MovementReasonApiService {

    @GetMapping("/movement-reason/{id}")
    MovementReasonModel findById(
            @NotNull @PathVariable Long id
    );

    @GetMapping("/movement-reason/find-ids-by-scenario-id")
    List<Long> findIdsByScenarioId(
            @NotNull @RequestParam("scenario-id") Long scenarioId
    );

}
