package dev.shermende.game.assembler;

import dev.shermende.game.assembler.resource.PointCtx;
import dev.shermende.game.controller.GameController;
import dev.shermende.game.model.PointModel;
import dev.shermende.game.service.PointDescriptionService;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PointModelAssembler extends RepresentationModelAssemblerSupport<PointCtx, PointModel> {

    private final PointDescriptionService pointDescriptionService;

    public PointModelAssembler(
            PointDescriptionService pointDescriptionService
    ) {
        super(GameController.class, PointModel.class);
        this.pointDescriptionService = pointDescriptionService;
    }

    @Override
    public @NotNull PointModel toModel(
            @NotNull PointCtx ctx
    ) {
        val pointDescription = pointDescriptionService.getOrCreate(ctx.getGameId(), ctx.getPointId());
        return PointModel.builder()
                .id(ctx.getPointId())
                .intro(pointDescription.getIntro())
                .description(pointDescription.getDescription())
                .build();
    }

}
