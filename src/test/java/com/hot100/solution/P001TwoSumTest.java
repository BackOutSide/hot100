package com.hot100.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class P001TwoSumTest {

    private final P001TwoSum solution = new P001TwoSum();

    @Test
    void examplesAndBoundaryCases() {
        assertAll(
                () -> assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{2, 7, 11, 15}, 9)),
                () -> assertArrayEquals(new int[]{1, 2}, solution.twoSum(new int[]{3, 2, 4}, 6)),
                () -> assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{3, 3}, 6))
        );
    }
}

