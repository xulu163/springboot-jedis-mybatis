package com.xulu.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
/**
 * @author xulu on 2019/3/11.
 */
public class Swagger2 {
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wolves.tolink.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);

    }
    private ApiInfo apiInfo() {
        Contact contact = new Contact(" AngryWolves ", "https://github.com/AngryWolves", "java_xul@163.com");
        ApiInfo apiInfo = new ApiInfo("AngryWolves APIs",
                        "",
                "1.0",
                "https://github.com/AngryWolves",
                contact,
                "AngryWolves",
                "https://github.com/AngryWolves"
        );
        return apiInfo;
    }
}
