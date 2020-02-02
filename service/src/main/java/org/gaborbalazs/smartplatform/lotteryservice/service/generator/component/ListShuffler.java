package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class ListShuffler {

    private final Random random;

    ListShuffler(Random threadLocalRandom) {
        this.random = threadLocalRandom;
    }

    public <T> List<T> shuffle(List<T> list) {
        Collections.shuffle(list, random);
        return list;
    }
}
