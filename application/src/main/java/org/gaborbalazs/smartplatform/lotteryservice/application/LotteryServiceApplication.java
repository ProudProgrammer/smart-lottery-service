package org.gaborbalazs.smartplatform.lotteryservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.gaborbalazs.smartplatform.lotteryservice"})
public class LotteryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryServiceApplication.class, args);
    }

}
