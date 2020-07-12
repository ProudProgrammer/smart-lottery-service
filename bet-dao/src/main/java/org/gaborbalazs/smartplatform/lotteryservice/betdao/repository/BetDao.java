package org.gaborbalazs.smartplatform.lotteryservice.betdao.repository;

import org.gaborbalazs.smartplatform.lotteryservice.betdao.factory.FiveOutOfNinetyDrawFactory;
import org.gaborbalazs.smartplatform.lotteryservice.betdao.factory.ScandinavianDrawFactory;
import org.gaborbalazs.smartplatform.lotteryservice.betdao.factory.SixOutOfFortyFiveDrawFactory;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.FiveOutOfNinetyDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.ScandinavianDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.SixOutOfFortyFiveDraw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BetDao {

    private static final String LINE_DELIMITER = "\\r?\\n";

    private final BetRetrieveService betRetrieveService;
    private final FiveOutOfNinetyDrawFactory fiveOutOfNinetyDrawFactory;
    private final SixOutOfFortyFiveDrawFactory sixOutOfFortyFiveDrawFactory;
    private final ScandinavianDrawFactory scandinavianDrawFactory;

    BetDao(BetRetrieveService betRetrieveService, FiveOutOfNinetyDrawFactory fiveOutOfNinetyDrawFactory, SixOutOfFortyFiveDrawFactory sixOutOfFortyFiveDrawFactory, ScandinavianDrawFactory scandinavianDrawFactory) {
        this.betRetrieveService = betRetrieveService;
        this.fiveOutOfNinetyDrawFactory = fiveOutOfNinetyDrawFactory;
        this.sixOutOfFortyFiveDrawFactory = sixOutOfFortyFiveDrawFactory;
        this.scandinavianDrawFactory = scandinavianDrawFactory;
    }

    public List<FiveOutOfNinetyDraw> getAllFiveOutOfNinetyDraws() throws RestClientException {
        String rawData = betRetrieveService.retrieveEveryDraws(LotteryType.FIVE_OUT_OF_NINETY);
        return Arrays.stream(rawData.split(LINE_DELIMITER)).map(fiveOutOfNinetyDrawFactory::create).collect(Collectors.toList());
    }

    public List<SixOutOfFortyFiveDraw> getAllSixOutOfFortyFiveDraws() throws RestClientException {
        String rawData = betRetrieveService.retrieveEveryDraws(LotteryType.SIX_OUT_OF_FORTY_FIVE);
        return Arrays.stream(rawData.split(LINE_DELIMITER)).map(sixOutOfFortyFiveDrawFactory::create).collect(Collectors.toList());
    }

    public List<ScandinavianDraw> getAllScandinavianDraws() throws RestClientException {
        String rawData = betRetrieveService.retrieveEveryDraws(LotteryType.SCANDINAVIAN);
        return Arrays.stream(rawData.split(LINE_DELIMITER)).map(scandinavianDrawFactory::create).collect(Collectors.toList());
    }
}
