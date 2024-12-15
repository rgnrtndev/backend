//package com.rcc.dev.backend.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.service.Parameter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//public class InitConfig {
//
//    @Bean
//    public Docket api() {
//        var parameterBuilder = new ParameterBuilder();
//        parameterBuilder.name("Authorization")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(true)
//                .defaultValue("Bearer [token]")
//                .build();
//        List<Parameter> parameters = new ArrayList<>();
//        parameters.add(parameterBuilder.build());
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(parameters)
//                .enable(true);
//    }
//}
