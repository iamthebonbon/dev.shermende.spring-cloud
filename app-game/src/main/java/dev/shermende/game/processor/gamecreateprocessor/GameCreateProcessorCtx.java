package dev.shermende.game.processor.gamecreateprocessor;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import dev.shermende.reference.lib.model.MovementScenarioModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameCreateProcessorCtx {

    private GameCreateResource resource;

    private PrincipalUser user;

    private MovementScenarioModel scenario;

    private List<Long> points;

    private List<Long> reasons;

    private Game game;

}
