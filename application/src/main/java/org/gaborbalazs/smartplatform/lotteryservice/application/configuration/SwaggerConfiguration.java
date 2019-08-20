package org.gaborbalazs.smartplatform.lotteryservice.application.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    private static final String TITLE = "SmartPlatform - Lottery Service";
    private static final String DESCRIPTION = "Lottery service for different lottery types.";
    private static final String VERSION = "1.0";
    private static final String TERMS_OF_SERVICE_URL = null;
    private static final String CONTACT_NAME = "Balázs Gábor";
    private static final String CONTACT_URL = "https://github.com/ProudProgrammer";
    private static final String CONTACT_EMAIL = "mail.gabor.balazs@gmail.com";
    private static final String LICENSE = "GNU General Public License";
    private static final String LICENSE_URL = "https://www.gnu.org/licenses/gpl-3.0.en.html";
    private static final List<VendorExtension> VENDOR_EXTENSIONS = Collections.emptyList();

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(TITLE, DESCRIPTION, VERSION, TERMS_OF_SERVICE_URL, contact(), LICENSE, LICENSE_URL, VENDOR_EXTENSIONS);
    }

    private Contact contact() {
        return new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
    }
}
