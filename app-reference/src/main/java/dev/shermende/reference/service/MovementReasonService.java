package dev.shermende.reference.service;

import dev.shermende.lib.dal.service.CrudService;
import dev.shermende.reference.db.entity.movement.MovementReason;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface MovementReasonService extends CrudService<MovementReason, Long> {

    Optional<MovementReason> findPointByScenarioIdAndIndex(
            @NotNull Long scenarioId,
            @NotNull Integer index
    );

    List<Long> findIdsByScenarioId(
            @NotNull Long scenarioId
    );

    Long countByScenarioId(
            @NotNull Long scenarioId
    );

}
