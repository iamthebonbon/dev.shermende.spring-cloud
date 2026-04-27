package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.Point;
import dev.shermende.game.db.entity.QPoint;
import dev.shermende.game.db.repository.PointRepository;
import dev.shermende.game.service.PointDescriptionService;
import dev.shermende.game.service.TextProvider;
import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.reference.lib.api.MovementPointApiService;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointServiceImpl extends AbstractCrudService<Point, Long, QPoint>
        implements PointDescriptionService {

    private final MovementPointApiService pointApiService;
    private final TextProvider<String, String> textProvider;

    public PointServiceImpl(
            PointRepository repository,
            MovementPointApiService pointApiService,
            TextProvider<String, String> textProvider
    ) {
        super(repository);
        this.pointApiService = pointApiService;
        this.textProvider = textProvider;
    }

    @Override
    public Point getOrCreate(
            @NotNull Long gameId,
            @NotNull Long pointId
    ) {
        return findByGameIdAndPointId(gameId, pointId)
                .orElseGet(() -> {
                    val point = pointApiService.findById(pointId);
                    return create(gameId, pointId, point.getIntro(), textProvider.generate(point.getIntro()));
                });
    }

    @Override
    public Point create(
            @NotNull Long gameId,
            @NotNull Long pointId,
            @NotNull String intro,
            @NotNull String description
    ) {
        return save(
                Point.builder()
                        .gameId(gameId)
                        .pointId(pointId)
                        .intro(intro)
                        .description(description)
                        .build()
        );
    }

    @Override
    public Optional<Point> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long pointId
    ) {
        return findOne(QPoint.point.gameId.eq(gameId)
                .and(QPoint.point.pointId.eq(pointId)));
    }

}
