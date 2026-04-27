package dev.shermende.reference.service.impl;

import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.QMovementPoint;
import dev.shermende.reference.db.repository.movement.MovementPointRepository;
import dev.shermende.reference.service.MovementPointService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovementPointServiceImpl extends AbstractCrudService<MovementPoint, Long, QMovementPoint>
        implements MovementPointService {

    private final MovementPointRepository repository;

    public MovementPointServiceImpl(
            MovementPointRepository repository
    ) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<MovementPoint> findPointByScenarioIdAndIndex(
            @NotNull Long scenarioId,
            int index
    ) {
        return Optional.ofNullable(findAll(QMovementPoint.movementPoint.scenarioId.eq(scenarioId),
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
        return count(QMovementPoint.movementPoint.scenarioId.eq(scenarioId));
    }

}
