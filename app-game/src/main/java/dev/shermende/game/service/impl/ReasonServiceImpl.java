package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.QReason;
import dev.shermende.game.db.entity.Reason;
import dev.shermende.game.db.repository.ReasonRepository;
import dev.shermende.game.service.ReasonDescriptionService;
import dev.shermende.game.service.TextProvider;
import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.reference.lib.api.MovementReasonApiService;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReasonServiceImpl extends AbstractCrudService<Reason, Long, QReason> implements ReasonDescriptionService {

    private final MovementReasonApiService reasonApiService;
    private final TextProvider<String, String> textProvider;

    public ReasonServiceImpl(
            ReasonRepository repository,
            MovementReasonApiService reasonApiService,
            TextProvider<String, String> textProvider
    ) {
        super(repository);
        this.reasonApiService = reasonApiService;
        this.textProvider = textProvider;
    }

    @Override
    public Reason getOrCreate(
            @NotNull Long gameId,
            @NotNull Long reasonId
    ) {
        return findByGameIdAndPointId(gameId, reasonId)
                .orElseGet(() -> {
                    val reason = reasonApiService.findById(reasonId);
                    return create(gameId, reasonId, reason.getIntro(), textProvider.generate(reason.getIntro()));
                });
    }

    @Override
    public Reason create(
            @NotNull Long gameId,
            @NotNull Long reasonId,
            @NotNull String intro,
            @NotNull String description
    ) {
        return save(
                Reason.builder()
                        .gameId(gameId)
                        .reasonId(reasonId)
                        .intro(intro)
                        .description(description)
                        .build()
        );
    }

    @Override
    public Optional<Reason> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long reasonId
    ) {
        return findOne(QReason.reason.gameId.eq(gameId)
                .and(QReason.reason.reasonId.eq(reasonId)));
    }

}
