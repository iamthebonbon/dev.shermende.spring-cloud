package dev.shermende.game.assembler;

import dev.shermende.game.assembler.resource.ReasonCtx;
import dev.shermende.game.controller.GameController;
import dev.shermende.game.model.ReasonModel;
import dev.shermende.game.service.ReasonDescriptionService;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ReasonModelAssembler extends RepresentationModelAssemblerSupport<ReasonCtx, ReasonModel> {

    private final ReasonDescriptionService reasonDescriptionService;

    public ReasonModelAssembler(
            ReasonDescriptionService reasonDescriptionService
    ) {
        super(GameController.class, ReasonModel.class);
        this.reasonDescriptionService = reasonDescriptionService;
    }

    @Override
    public @NotNull ReasonModel toModel(
            @NotNull ReasonCtx ctx
    ) {
        val reasonDescription = reasonDescriptionService.getOrCreate(ctx.getGameId(), ctx.getReasonId());
        return ReasonModel.builder()
                .id(ctx.getReasonId())
                .intro(reasonDescription.getIntro())
                .description(reasonDescription.getDescription())
                .build();
    }

}
