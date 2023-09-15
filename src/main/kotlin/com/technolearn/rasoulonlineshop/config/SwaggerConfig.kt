package com.technolearn.rasoulonlineshop.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//@Configuration
//@EnableSwagger2
//class SwaggerConfig {
//
//    @Bean
//    fun api(): Docket {
//        return Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .paths(PathSelectors.ant("/api/**"))
//                .apis(RequestHandlerSelectors.basePackage("com.technolearn.rasoulonlineshop.controllers"))
//                .build()
//    }
//}


@Configuration
class OpenApiConfig {
    @Bean
    fun usersMicroserviceOpenAPI(): OpenAPI {
        return OpenAPI()
                .info(Info().title("Your API Title")
                        .description("Your API Description")
                        .version("1.0"))
    }
}