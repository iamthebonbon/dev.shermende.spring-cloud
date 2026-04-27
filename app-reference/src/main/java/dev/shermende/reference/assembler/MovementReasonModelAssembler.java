package dev.shermende.reference.assembler;

import dev.shermende.reference.controller.MovementPointController;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.lib.model.MovementReasonModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MovementReasonModelAssembler extends RepresentationModelAssemblerSupport<MovementReason, MovementReasonModel> {

    public MovementReasonModelAssembler() {
        super(MovementPointController.class, MovementReasonModel.class);
    }

    @NotNull
    @Override
    public MovementReasonModel toModel(
            @NotNull MovementReason entity
    ) {
        return MovementReasonModel.builder()
                .id(entity.getId())
                .intro(entity.getIntro())
                .build();
    }

}
