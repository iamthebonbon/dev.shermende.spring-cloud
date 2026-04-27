package dev.shermende.game.utils;

import lombok.val;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GraphUtilsTest {

    private static final int TRUST_THRESHOLD = 10000;
    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void isMatch() {
        for (int i = 0; i < TRUST_THRESHOLD; i++) {
            Assertions.assertFalse(GraphUtils.isMatch(0));
            Assertions.assertTrue(GraphUtils.isMatch(100));
        }
    }

    @Test
    void isMatchEdgeChangeLessThanZero() {
        for (int i = 0; i < TRUST_THRESHOLD; i++) {
            val edgeChance = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD)) * -1;
            val exception = Assertions.assertThrows(IllegalArgumentException.class, () -> GraphUtils.isMatch(edgeChance == 0 ? -1 : edgeChance));
            Assertions.assertEquals("'edgeChance' cannot be less than zero", exception.getMessage());
        }
    }

    @Test
    void nextPeakPositionSourceEqualsToPeaksCountTest() {
        val peaksCount = 999;
        val source = 999;
        val exception = Assertions.assertThrows(IllegalArgumentException.class, () -> GraphUtils.nextPeakPosition(source, peaksCount));
        Assertions.assertEquals("'source' cannot be large than 'peaksCount'", exception.getMessage());
    }

    @Test
    void nextPeakPositionSourceLessThanZeroTest() {
        val peaksCount = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD));
        val source = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD)) + peaksCount;
        val exception = Assertions.assertThrows(IllegalArgumentException.class, () -> GraphUtils.nextPeakPosition(source * -1, peaksCount));
        Assertions.assertEquals("'source' cannot be less than zero", exception.getMessage());
    }

    @Test
    void nextPeakPositionPeaksCountLessThanZeroTest() {
        val peaksCount = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD));
        val source = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD)) + peaksCount;
        val exception = Assertions.assertThrows(IllegalArgumentException.class, () -> GraphUtils.nextPeakPosition(source, peaksCount * -1));
        Assertions.assertEquals("'source' cannot be less than zero", exception.getMessage());
    }

    @Test
    void nextPeakPositionSourceLargeThanPeaksCountTest() {
        val peaksCount = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD));
        val source = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD)) + peaksCount;
        val exception = Assertions.assertThrows(IllegalArgumentException.class, () -> GraphUtils.nextPeakPosition(source, peaksCount));
        Assertions.assertEquals("'source' cannot be large than 'peaksCount'", exception.getMessage());
    }

    @Test
    void nextPeakPositionSourceTest() {
        val peaksCount = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD));
        for (int i = 0; i < peaksCount; i++) {
            Assertions.assertNotEquals(i, GraphUtils.nextPeakPosition(i, peaksCount));
        }
    }
}
