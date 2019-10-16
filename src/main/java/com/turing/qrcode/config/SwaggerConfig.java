package com.turing.qrcode.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Meng
 * @date 2019/9/19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("二维码座位管理系统前端接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.turing.qrcode.controller"))
                .paths(PathSelectors.any()).build();
    }
//    @Bean
//    public Docket createRestApi1(){
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("二维码座位管理系统后台接口")).groupName("后台管理页面接口")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.turing.qrcode.controller.admin"))
//                .paths(PathSelectors.any()).build();
//    }

    private ApiInfo apiInfo(String title){
        return new ApiInfoBuilder()
                .title(title)
                .description("座位管理")
                .version("1.0").build();
    }
}
