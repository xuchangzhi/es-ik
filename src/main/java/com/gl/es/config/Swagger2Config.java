package com.gl.es.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2API文档的配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger.enable}")
    private  Boolean enable;

    @Bean
    public Docket createRestApi(){

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        parameterBuilder.name("Authorization").description("授权：值为会员登录后会返还tokenHead和token拼接的字符串， 传递Authorization表示用户登录，拥有登录后的权限,token过期时间为1天").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(parameterBuilder.build())


        ;

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars)
                        .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("词库")
                .description("词库接口文档")
                .contact("gl")
                .version("1.0")
                .build();
    }



    private List<ApiKey> securitySchemes() {
        ApiKey apiKey =  new ApiKey("Authorization", "Authorization", "header");
        ArrayList arrayList = new ArrayList();
        arrayList.add(apiKey);
        return arrayList;
    }
    private List<SecurityContext> securityContexts() {

        SecurityContext securityContext =  SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(securityContext);
        return arrayList;
    }
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        SecurityReference securityReference =  new SecurityReference("Authorization", authorizationScopes);
        ArrayList arrayList = new ArrayList();
        arrayList.add(securityReference);
        return arrayList;
    }
}
