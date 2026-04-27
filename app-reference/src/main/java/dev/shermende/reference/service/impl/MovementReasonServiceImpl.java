package dev.shermende.reference.service.impl;

import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.QMovementReason;
import dev.shermende.reference.db.repository.movement.MovementReasonRepository;
import dev.shermende.reference.service.MovementReasonService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovementReasonServiceImpl extends AbstractCrudService<MovementReason, Long, QMovementReason>
        implements MovementReasonService {

    private final MovementReasonRepository repository;

    public MovementReasonServiceImpl(
            MovementReasonRepository repository
    ) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<MovementReason> findPointByScenarioIdAndIndex(
            @NotNull Long scenarioId,
            @NotNull Integer index
    ) {
        return Optional.ofNullable(findAll(QMovementReason.movementReason.scenarioId.eq(scenarioId),
                PageRequest.of(0, 99, Sort.Direction.ASC, "id")).getContent().get(index));
    }

    @Override
    public List<Long> findIdsByScenarioId(
            @NotNull Long scenarioId
    ) {
        return repository.findIdsByScenarioId(scenarioId);
    }

    @Override
    public Long countByScenarioId(
            @NotNull Long scenarioId
    ) {
        return count(QMovementReason.movementReason.scenarioId.eq(scenarioId));
    }
}
