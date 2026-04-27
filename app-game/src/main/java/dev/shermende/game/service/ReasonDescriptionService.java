package dev.shermende.game.service;

import dev.shermende.game.db.entity.Reason;
import dev.shermende.lib.dal.service.CrudService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ReasonDescriptionService extends CrudService<Reason, Long> {

    Reason getOrCreate(
            @NotNull Long gameId,
            @NotNull Long pointId
    );

    Reason create(
            @NotNull Long gameId,
            @NotNull Long reasonId,
            @NotNull String intro,
            @NotNull String description
    );

    Optional<Reason> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long reasonId
    );

}
