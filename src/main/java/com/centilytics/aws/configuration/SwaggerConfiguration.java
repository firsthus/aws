package com.centilytics.aws.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration  implements WebMvcConfigurer {

    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.centilytics.aws"))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui/")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    private ApiInfo apiDetails () {
        return new ApiInfo(
                "AWS TEST API",
                "AWS Test Api",
                "1.0",
                "Free to use",
                new Contact("Festus Audu",
                        "https://www.linkedin.com/in/firsthus",
                        "audufirsthus@gmail.com"),
                "API License",
                "https://www.github.com/firsthus",
                Collections.emptyList()
        );
    }



}
