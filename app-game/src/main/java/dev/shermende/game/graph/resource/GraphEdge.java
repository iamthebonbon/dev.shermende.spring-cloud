package dev.shermende.game.graph.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GraphEdge<T> {
    private T source;
    private T target;
}
