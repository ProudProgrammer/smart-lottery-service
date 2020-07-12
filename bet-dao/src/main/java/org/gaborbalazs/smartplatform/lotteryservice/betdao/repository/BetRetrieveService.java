package org.gaborbalazs.smartplatform.lotteryservice.betdao.repository;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
public class BetRetrieveService {

    @Value("${bet.url.five-out-of-ninety}")
    private String betFiveOutOfNinetyUrl;

    @Value("${bet.url.six-out-of-forty-five}")
    private String betSixOutOfFortyFiveUrl;

    @Value("${bet.url.scandinavian}")
    private String betScandinavianUrl;

    private final RestTemplate restTemplate;

    BetRetrieveService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String retrieveEveryDraws(LotteryType lotteryType) throws RestClientException {
        String result = "";
        if (lotteryType == LotteryType.FIVE_OUT_OF_NINETY) {
            ResponseEntity<String> retrievedData = restTemplate.getForEntity(betFiveOutOfNinetyUrl, String.class);
            result = retrievedData.getBody();
        } else if (lotteryType == LotteryType.SIX_OUT_OF_FORTY_FIVE) {
            ResponseEntity<String> retrievedData = restTemplate.getForEntity(betSixOutOfFortyFiveUrl, String.class);
            result = retrievedData.getBody();
        } else if (lotteryType == LotteryType.SCANDINAVIAN) {
            ResponseEntity<String> retrievedData = restTemplate.getForEntity(betScandinavianUrl, String.class);
            result = retrievedData.getBody();
        }
        return result;
    }
}
