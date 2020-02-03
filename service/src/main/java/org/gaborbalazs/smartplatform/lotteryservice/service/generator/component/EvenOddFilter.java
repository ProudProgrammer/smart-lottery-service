package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
class EvenOddFilter {

    List<Integer> getEvenNumbers(int lowerLimit, int upperLimit) {
        return apply(true, lowerLimit, upperLimit);
    }

    List<Integer> getOddNumbers(int lowerLimit, int upperLimit) {
        return apply(false, lowerLimit, upperLimit);
    }

    private List<Integer> apply(boolean getEvenNumbers, int lowerLimit, int upperLimit) {
        validate(lowerLimit, upperLimit);
        List<Integer> result = new ArrayList<>();
        for (int i = lowerLimit; i <= upperLimit; i++) {
            if (getEvenNumbers && i % 2 == 0) {
                result.add(i);
            } else if (!getEvenNumbers && i % 2 != 0) {
                result.add(i);
            }
        }
        return result;
    }

    private void validate(int lowerLimit, int upperLimit) {
        if (upperLimit <= lowerLimit) {
            String msg = MessageFormat.format("Upper limit must be larger than lower limit. Lower limit: {0}, Upper limit: {1}", lowerLimit, upperLimit);
            throw new IllegalArgumentException(msg);
        }
    }
}
