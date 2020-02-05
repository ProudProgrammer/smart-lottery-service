package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
class ListShuffler {

    private final Random random;

    ListShuffler(Random threadLocalRandom) {
        this.random = threadLocalRandom;
    }

    <T> List<T> shuffle(List<T> list) {
        List<T> newList = new ArrayList<>(list);
        Collections.shuffle(newList, random);
        return newList;
    }
}
