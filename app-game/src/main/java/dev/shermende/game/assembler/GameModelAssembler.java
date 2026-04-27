package dev.shermende.game.assembler;

import dev.shermende.game.assembler.resource.PointCtx;
import dev.shermende.game.assembler.resource.ReasonCtx;
import dev.shermende.game.assembler.resource.RouteCtx;
import dev.shermende.game.controller.GameController;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.model.GameModel;
import dev.shermende.game.model.PointModel;
import dev.shermende.game.model.ReasonModel;
import dev.shermende.game.model.RouteModel;
import dev.shermende.game.service.GameRouteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameModelAssembler extends RepresentationModelAssemblerSupport<Game, GameModel> {

    private final GameRouteService routeService;
    private final PointModelAssembler pointModelAssembler;
    private final ReasonModelAssembler reasonModelAssembler;
    private final RouteModelAssembler routeModelAssembler;

    public GameModelAssembler(
            GameRouteService routeService,
            PointModelAssembler pointModelAssembler,
            ReasonModelAssembler reasonModelAssembler,
            RouteModelAssembler routeModelAssembler
    ) {
        super(GameController.class, GameModel.class);
        this.routeService = routeService;
        this.pointModelAssembler = pointModelAssembler;
        this.reasonModelAssembler = reasonModelAssembler;
        this.routeModelAssembler = routeModelAssembler;
    }

    @Override
    public @NotNull GameModel toModel(
            @NotNull Game entity
    ) {
        return GameModel
                .builder()
                .id(entity.getId())
                .reason(getReason(entity))
                .target(getTarget(entity))
                .routes(getRoutes(entity))
                .build();
    }

    @NotNull
    private ReasonModel getReason(
            @NotNull Game entity
    ) {
        return reasonModelAssembler.toModel(ReasonCtx.builder()
                .gameId(entity.getId()).reasonId(entity.getReasonId()).build());
    }

    @NotNull
    private PointModel getTarget(
            @NotNull Game entity
    ) {
        return pointModelAssembler.toModel(PointCtx.builder()
                .gameId(entity.getId()).pointId(entity.getPointId()).build());
    }

    @NotNull
    private List<RouteModel> getRoutes(
            @NotNull Game entity
    ) {
        return routeService.findAllByPoint(entity.getId(), entity.getPointId()).stream()
                .map(route -> RouteCtx.builder().route(route).currentPoint(entity.getPointId()).build())
                .map(routeModelAssembler::toModel)
                .collect(Collectors.toList());
    }

}
