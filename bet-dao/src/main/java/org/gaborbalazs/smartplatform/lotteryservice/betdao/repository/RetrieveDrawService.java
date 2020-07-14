package org.gaborbalazs.smartplatform.lotteryservice.betdao.repository;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
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

    RetrieveDrawService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String retrieveAllByLotteryType(LotteryType lotteryType) throws RestClientException {
        String result = "";
        if (lotteryType == LotteryType.FIVE_OUT_OF_NINETY) {
            result = restTemplate.getForEntity(betFiveOutOfNinetyUrl, String.class).getBody();
        } else if (lotteryType == LotteryType.SIX_OUT_OF_FORTY_FIVE) {
            result = restTemplate.getForEntity(betSixOutOfFortyFiveUrl, String.class).getBody();
        } else if (lotteryType == LotteryType.SCANDINAVIAN) {
            result = restTemplate.getForEntity(betScandinavianUrl, String.class).getBody();
        } else if (lotteryType == LotteryType.JOKER) {
            result = restTemplate.getForEntity(betJokerUrl, String.class).getBody();
        }
        return result;
    }
}
