package dev.shermende.game.service;

import dev.shermende.game.db.entity.Point;
import dev.shermende.lib.dal.service.CrudService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface PointDescriptionService extends CrudService<Point, Long> {

    Point getOrCreate(
            @NotNull Long gameId,
            @NotNull Long pointId
    );

    Point create(
            @NotNull Long gameId,
            @NotNull Long pointId,
            @NotNull String intro,
            @NotNull String description
    );

    Optional<Point> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long pointId
    );

}
