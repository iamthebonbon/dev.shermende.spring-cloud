package dev.shermende.reference.assembler;

import dev.shermende.reference.controller.MovementScenarioController;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.lib.model.MovementScenarioModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MovementScenarioModelAssembler extends RepresentationModelAssemblerSupport<MovementScenario, MovementScenarioModel> {

    public MovementScenarioModelAssembler(
    ) {
        super(MovementScenarioController.class, MovementScenarioModel.class);
    }

    @NotNull
    @Override
    public MovementScenarioModel toModel(
            @NotNull MovementScenario entity
    ) {
        return MovementScenarioModel.builder()
                .id(entity.getId())
                .reasonId(entity.getReasonId())
                .pointId(entity.getPointId())
                .build();
    }

}
