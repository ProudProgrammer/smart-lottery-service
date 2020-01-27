package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PartitionService {

    public List<Partition> createPartitions(int usedPartitions, int numberOfPartitions, int setOfNumbers) {
        if (setOfNumbers <= numberOfPartitions || numberOfPartitions <= usedPartitions || setOfNumbers % numberOfPartitions != 0) {
            String msg = MessageFormat.format("Set of numbers must be larger than number of partitions and their division with remainder must be 0 and number of partitions must be larger than used partitions. Set of numbers: {0}, Number of partitions: {1}, Used partitions: {2}", setOfNumbers, numberOfPartitions, usedPartitions);
            throw new IllegalArgumentException(msg);
        }
        List<Partition> partitions = new ArrayList<>();
        return partitions;
    }
}
