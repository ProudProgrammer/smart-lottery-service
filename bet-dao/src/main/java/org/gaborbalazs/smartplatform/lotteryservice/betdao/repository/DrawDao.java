package org.gaborbalazs.smartplatform.lotteryservice.betdao.repository;

import org.gaborbalazs.smartplatform.lotteryservice.betdao.factory.FiveOutOfNinetyDrawFactory;
import org.gaborbalazs.smartplatform.lotteryservice.betdao.factory.JokerDrawFactory;
import org.gaborbalazs.smartplatform.lotteryservice.betdao.factory.ScandinavianDrawFactory;
import org.gaborbalazs.smartplatform.lotteryservice.betdao.factory.SixOutOfFortyFiveDrawFactory;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DrawDao {

    private static final String LINE_DELIMITER = "\\r?\\n";

    private final RetrieveDrawService retrieveDrawService;
    private final FiveOutOfNinetyDrawFactory fiveOutOfNinetyDrawFactory;
    private final SixOutOfFortyFiveDrawFactory sixOutOfFortyFiveDrawFactory;
    private final ScandinavianDrawFactory scandinavianDrawFactory;
    private final JokerDrawFactory jokerDrawFactory;

    DrawDao(RetrieveDrawService retrieveDrawService, FiveOutOfNinetyDrawFactory fiveOutOfNinetyDrawFactory, SixOutOfFortyFiveDrawFactory sixOutOfFortyFiveDrawFactory, ScandinavianDrawFactory scandinavianDrawFactory, JokerDrawFactory jokerDrawFactory) {
        this.retrieveDrawService = retrieveDrawService;
        this.fiveOutOfNinetyDrawFactory = fiveOutOfNinetyDrawFactory;
        this.sixOutOfFortyFiveDrawFactory = sixOutOfFortyFiveDrawFactory;
        this.scandinavianDrawFactory = scandinavianDrawFactory;
        this.jokerDrawFactory = jokerDrawFactory;
    }

    public List<Draw> findAllByLotteryType(LotteryType lotteryType) throws RestClientException {
        List<Draw> result = Collections.emptyList();
        String rawData;
        if (lotteryType == LotteryType.FIVE_OUT_OF_NINETY) {
            rawData = retrieveDrawService.retrieveAllByLotteryType(LotteryType.FIVE_OUT_OF_NINETY);
            result = Arrays.stream(rawData.split(LINE_DELIMITER)).map(fiveOutOfNinetyDrawFactory::create).collect(Collectors.toList());
        } else if (lotteryType == LotteryType.SIX_OUT_OF_FORTY_FIVE) {
            rawData = retrieveDrawService.retrieveAllByLotteryType(LotteryType.SIX_OUT_OF_FORTY_FIVE);
            result = Arrays.stream(rawData.split(LINE_DELIMITER)).map(sixOutOfFortyFiveDrawFactory::create).collect(Collectors.toList());
        } else if (lotteryType == LotteryType.SCANDINAVIAN) {
            rawData = retrieveDrawService.retrieveAllByLotteryType(LotteryType.SCANDINAVIAN);
            result = Arrays.stream(rawData.split(LINE_DELIMITER)).map(scandinavianDrawFactory::create).collect(Collectors.toList());
        } else if (lotteryType == LotteryType.JOKER) {
            rawData = retrieveDrawService.retrieveAllByLotteryType(LotteryType.JOKER);
            result = Arrays.stream(rawData.split(LINE_DELIMITER)).map(jokerDrawFactory::create).collect(Collectors.toList());
        }
        return result;
    }
}
