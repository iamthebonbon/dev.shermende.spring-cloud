package dev.shermende.reference.assembler;

import dev.shermende.reference.controller.MovementPointController;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.lib.model.MovementPointModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MovementPointModelAssembler extends RepresentationModelAssemblerSupport<MovementPoint, MovementPointModel> {

    public MovementPointModelAssembler() {
        super(MovementPointController.class, MovementPointModel.class);
    }

    @NotNull
    @Override
    public MovementPointModel toModel(
            @NotNull MovementPoint entity
    ) {
        return MovementPointModel.builder()
                .id(entity.getId())
                .intro(entity.getIntro())
                .build();
    }

}
