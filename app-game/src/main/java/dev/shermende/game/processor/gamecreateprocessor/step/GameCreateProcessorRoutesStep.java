package dev.shermende.game.processor.gamecreateprocessor.step;

import dev.shermende.game.db.entity.Route;
import dev.shermende.game.graph.service.GraphService;
import dev.shermende.game.processor.gamecreateprocessor.GameCreateProcessorCtx;
import dev.shermende.game.service.GameRouteService;
import dev.shermende.support.spring.processor.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameCreateProcessorRoutesStep implements Step<GameCreateProcessorCtx, GameCreateProcessorCtx> {

    private final GraphService graphService;
    private final GameRouteService routeService;

    @Override
    public GameCreateProcessorCtx execute(
            GameCreateProcessorCtx ctx
    ) {
        graphService.generateGraph(ctx.getPoints())
                .forEach(graphEdge ->
                        routeService.save(Route.builder()
                                .userId(ctx.getUser().getId())
                                .gameId(ctx.getGame().getId())
                                .sourcePointId(graphEdge.getSource())
                                .reasonId(ctx.getReasons().get(new Random().nextInt(ctx.getReasons().size())))
                                .targetPointId(graphEdge.getTarget())
                                .build()));
        return ctx;
    }

}
