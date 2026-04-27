package dev.shermende.game.assembler.resource;

import dev.shermende.game.db.entity.Route;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteCtx {
    private Long currentPoint;
    private Route route;
}
