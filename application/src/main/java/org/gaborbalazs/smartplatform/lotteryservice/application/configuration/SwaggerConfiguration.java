package org.gaborbalazs.smartplatform.lotteryservice.application.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("!prod & !integration-test")
@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Value("${swagger.title:#{null}}")
    private String title;

    @Value("${swagger.description:#{null}}")
    private String description;

    @Value("${swagger.version:#{null}}")
    private String version;

    @Value("${swagger.terms-of-service-url:#{null}}")
    private String terms_of_service_url;

    @Value("${swagger.contact-name:#{null}}")
    private String contact_name;

    @Value("${swagger.contact-url:#{null}}")
    private String contact_url;

    @Value("${swagger.contact-email:#{null}}")
    private String contact_email;

    @Value("${swagger.license:#{null}}")
    private String license;

    @Value("${swagger.license-url:#{null}}")
    private String license_url;

    private List<VendorExtension> VENDOR_EXTENSIONS = Collections.emptyList();

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
        return new ApiInfo(title, description, version, terms_of_service_url, contact(), license, license_url, VENDOR_EXTENSIONS);
    }

    private Contact contact() {
        return new Contact(contact_name, contact_url, contact_email);
    }
}
