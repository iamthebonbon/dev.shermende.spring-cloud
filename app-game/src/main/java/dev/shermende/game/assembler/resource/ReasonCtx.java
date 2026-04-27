package dev.shermende.game.assembler.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReasonCtx {
    private Long gameId;
    private Long reasonId;
}
