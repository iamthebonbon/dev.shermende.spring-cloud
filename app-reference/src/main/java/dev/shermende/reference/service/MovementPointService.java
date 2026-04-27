package dev.shermende.reference.service;

import dev.shermende.lib.dal.service.CrudService;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface MovementPointService extends CrudService<MovementPoint, Long> {

    Optional<MovementPoint> findPointByScenarioIdAndIndex(
            @NotNull Long scenarioId,
            int index
    );

    List<Long> findIdsByScenarioId(
            @NotNull Long scenarioId
    );

    Long countByScenarioId(
            @NotNull Long scenarioId
    );


}
