package dev.shermende.game.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class GraphUtils {
    private final Random random = new Random();

    public boolean isMatch(
            int edgeChance
    ) {
        if (edgeChance < 0) throw new IllegalArgumentException("'edgeChance' cannot be less than zero");
        return random.nextInt(100) < edgeChance;
    }

    public int nextPeakPosition(
            int source,
            int peaksCount
    ) {
        if (source < 0) throw new IllegalArgumentException("'source' cannot be less than zero");
        if (peaksCount < 0) throw new IllegalArgumentException("'source' cannot be less than zero");
        if (source >= peaksCount) throw new IllegalArgumentException("'source' cannot be large than 'peaksCount'");
        int target;
        do {
            target = random.nextInt(peaksCount);
        } while (source == target);
        return target;
    }

//    public int nextPeakPosition(
//            List<GraphEdge> graphEdges,
//            int source,
//            int peaksCount
//    ) {
//        if (source < 0) throw new IllegalArgumentException("'source' cannot be less than zero");
//        if (peaksCount < 0) throw new IllegalArgumentException("'source' cannot be less than zero");
//        if (source >= peaksCount) throw new IllegalArgumentException("'source' cannot be large than 'peaksCount'");
//        int target;
//        do {
//            target = random.nextInt(peaksCount);
//        } while (source == target || isCircular(graphEdges, source, target) || isContainEdge(graphEdges, source, target));
//        return target;
//    }

//    private boolean isCircular(
//            List<GraphEdge> graphEdges,
//            int source,
//            int target
//    ) {
//        for (GraphEdge edge : graphEdges) if (edge.getSource() == target && edge.getTarget() == source) return true;
//        return false;
//    }
//
//    private boolean isContainEdge(
//            List<GraphEdge> graphEdges,
//            int source,
//            int target
//    ) {
//        for (GraphEdge edge : graphEdges) if (edge.getSource() == source && edge.getTarget() == target) return true;
//        return false;
//    }

}
