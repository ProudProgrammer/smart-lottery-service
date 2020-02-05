package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListShufflerTest {

    private ListShuffler underTest;

    @BeforeEach
    void setUp() {
        underTest = new ListShuffler(ThreadLocalRandom.current());
    }

    @Test
    void testShuffle() {
        // GIVEN
        List<Integer> originalList = List.of(1, 2, 3, 4, 5);

        // WHEN
        var result = underTest.shuffle(originalList);

        // THEN
        assertEquals(originalList.size(), result.size());
        assertNotEquals(originalList, result);
        assertEquals(originalList.size(), result.stream().filter(originalList::contains).count());
    }
}
