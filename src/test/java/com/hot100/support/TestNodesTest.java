package com.hot100.support;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestNodesTest {

    @Test
    void buildsLinkedLists() {
        assertAll(
                () -> assertArrayEquals(new int[]{1, 2, 3}, TestNodes.toArray(TestNodes.list(1, 2, 3))),
                () -> assertNull(TestNodes.list())
        );
    }

    @Test
    void buildsAndNormalizesTrees() {
        assertAll(
                () -> assertEquals(
                        Arrays.asList(1, 2, 3, null, 4),
                        TestNodes.toLevelOrder(TestNodes.tree(1, 2, 3, null, 4))
                ),
                () -> assertEquals(List.of(), TestNodes.toLevelOrder(null))
        );
    }
}

