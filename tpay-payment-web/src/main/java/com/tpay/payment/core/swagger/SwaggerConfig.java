package com.tpay.payment.core.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-06-16 9:47
 **/
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan("com.***.payment.controller")
public class SwaggerConfig {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("full-platform").apiInfo(apiInfo())
                .forCodeGeneration(true);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Trazen")
                .description("©2018 Copyright. Powered By TUYONG.")
                .termsOfServiceUrl("http://www.tpay.fun/")
                .version("1.0")
                .build();
    }

}
