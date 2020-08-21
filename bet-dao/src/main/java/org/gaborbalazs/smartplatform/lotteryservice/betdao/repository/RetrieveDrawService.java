package org.gaborbalazs.smartplatform.lotteryservice.betdao.repository;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
public class RetrieveDrawService {

    @Value("${bet.url.five-out-of-ninety}")
    private String betFiveOutOfNinetyUrl;

    @Value("${bet.url.six-out-of-forty-five}")
    private String betSixOutOfFortyFiveUrl;

    @Value("${bet.url.scandinavian}")
    private String betScandinavianUrl;

    @Value("${bet.url.joker}")
    private String betJokerUrl;

    private final RestTemplate restTemplate;

    private final Logger logger;

    RetrieveDrawService(RestTemplate restTemplate, Logger logger) {
        this.restTemplate = restTemplate;
        this.logger = logger;
    }

    String retrieveAllByLotteryType(LotteryType lotteryType) throws RestClientException {
        String result = "";
        if (lotteryType == LotteryType.FIVE_OUT_OF_NINETY) {
            result = getResult(betFiveOutOfNinetyUrl);
        } else if (lotteryType == LotteryType.SIX_OUT_OF_FORTY_FIVE) {
            result = getResult(betSixOutOfFortyFiveUrl);
        } else if (lotteryType == LotteryType.SCANDINAVIAN) {
            result = getResult(betScandinavianUrl);
        } else if (lotteryType == LotteryType.JOKER) {
            result = getResult(betJokerUrl);
        }
        return result;
    }

    private String getResult(String url) {
        try {
            return restTemplate.getForEntity(url, String.class).getBody();
        } catch (RestClientException e) {
            logger.error("Cannot retrieve lottery numbers from external service.", e);
            throw e;
        }
    }
}
