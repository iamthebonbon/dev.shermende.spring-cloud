package dev.shermende.reference.lib.api;

import dev.shermende.reference.lib.model.MovementScenarioModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MovementScenarioApiService {

    @GetMapping("/movement-scenario/{id}")
    MovementScenarioModel findById(
            @PathVariable Long id
    );

}
