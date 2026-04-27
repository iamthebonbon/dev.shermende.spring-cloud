package dev.shermende.game.graph.service.impl;

import dev.shermende.game.graph.resource.GraphEdge;
import dev.shermende.game.graph.service.GraphService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class GraphServiceImpl implements GraphService {

    @Override
    public <T> Set<GraphEdge<T>> generateGraph(
            List<T> vertices
    ) {
        Collections.shuffle(vertices);
        val list = new ArrayList<GraphEdge<T>>();

        for (int i = 1; i < vertices.size(); i++)
            list.add(GraphEdge.<T>builder().source(vertices.get(i - 1)).target(vertices.get(i)).build());

        Collections.shuffle(list);
        return new HashSet<>(list);
    }

}
