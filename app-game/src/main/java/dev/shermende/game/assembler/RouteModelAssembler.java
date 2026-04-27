package dev.shermende.game.assembler;

import dev.shermende.game.assembler.resource.PointCtx;
import dev.shermende.game.assembler.resource.ReasonCtx;
import dev.shermende.game.assembler.resource.RouteCtx;
import dev.shermende.game.controller.GameController;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.model.PointModel;
import dev.shermende.game.model.ReasonModel;
import dev.shermende.game.model.RouteModel;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RouteModelAssembler extends RepresentationModelAssemblerSupport<RouteCtx, RouteModel> {

    private final PointModelAssembler pointModelAssembler;
    private final ReasonModelAssembler reasonModelAssembler;

    public RouteModelAssembler(
            PointModelAssembler pointModelAssembler,
            ReasonModelAssembler reasonModelAssembler
    ) {
        super(GameController.class, RouteModel.class);
        this.pointModelAssembler = pointModelAssembler;
        this.reasonModelAssembler = reasonModelAssembler;
    }

    @Override
    public @NotNull RouteModel toModel(
            @NotNull RouteCtx ctx
    ) {
        val route = ctx.getRoute();
        return RouteModel.builder()
                .id(route.getId())
                .reason(getReason(route))
                .target(getTarget(ctx))
                .build();
    }

    @NotNull
    private ReasonModel getReason(
            @NotNull Route route
    ) {
        return reasonModelAssembler.toModel(ReasonCtx.builder()
                .gameId(route.getGameId()).reasonId(route.getReasonId()).build());
    }

    @NotNull
    private PointModel getTarget(
            @NotNull RouteCtx ctx
    ) {
        val route = ctx.getRoute();
        //
        if (ctx.getCurrentPoint().equals(ctx.getRoute().getTargetPointId()))
            return pointModelAssembler.toModel(PointCtx.builder()
                    .gameId(route.getGameId()).pointId(route.getSourcePointId()).build());
        //
        return pointModelAssembler.toModel(PointCtx.builder()
                .gameId(route.getGameId()).pointId(route.getTargetPointId()).build());
    }

}
