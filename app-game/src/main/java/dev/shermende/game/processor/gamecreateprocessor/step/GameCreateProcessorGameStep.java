package dev.shermende.game.processor.gamecreateprocessor.step;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.processor.gamecreateprocessor.GameCreateProcessorCtx;
import dev.shermende.game.service.GameService;
import dev.shermende.support.spring.processor.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameCreateProcessorGameStep implements Step<GameCreateProcessorCtx, GameCreateProcessorCtx> {

    private final GameService service;

    @Override
    public GameCreateProcessorCtx execute(
            GameCreateProcessorCtx ctx
    ) {
        return ctx
                .setGame(
                        service.save(
                                Game.builder()
                                        .userId(ctx.getUser().getId())
                                        .scenarioId(ctx.getScenario().getId())
                                        .reasonId(ctx.getScenario().getReasonId())
                                        .pointId(ctx.getScenario().getPointId())
                                        .build()
                        )
                );
    }

}
