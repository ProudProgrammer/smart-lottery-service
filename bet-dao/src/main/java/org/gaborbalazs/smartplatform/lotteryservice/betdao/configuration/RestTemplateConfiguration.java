package org.gaborbalazs.smartplatform.lotteryservice.betdao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplateUtf8() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .stream()
                .filter(c -> c instanceof StringHttpMessageConverter)
                .forEach(c -> ((StringHttpMessageConverter) c).setDefaultCharset(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
