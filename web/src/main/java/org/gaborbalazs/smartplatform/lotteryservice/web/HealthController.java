package org.gaborbalazs.smartplatform.lotteryservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private static final String SERVICE_NAME = "Smart Lottery Service";

    @GetMapping("/service-name")
    public String getServiceName() {
        return SERVICE_NAME;
    }
}
