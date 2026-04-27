package dev.shermende.reference.lib.api;

import dev.shermende.reference.lib.model.MovementPointModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface MovementPointApiService {

    @GetMapping("/movement-point/{id}")
    MovementPointModel findById(
            @NotNull @PathVariable Long id
    );

    @GetMapping("/movement-point/find-ids-by-scenario-id")
    List<Long> findIdsByScenarioId(
            @NotNull @RequestParam("scenario-id") Long scenarioId
    );

}
