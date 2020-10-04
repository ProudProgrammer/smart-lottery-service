package org.gaborbalazs.smartplatform.lotteryservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "org.gaborbalazs.smartplatform.lotteryservice")
@EnableCaching
public class LotteryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryServiceApplication.class, args);
    }

}
