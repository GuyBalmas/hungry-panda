package org.example.hungry_panda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import org.springdoc.core.GroupedOpenApi;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {
    
    @Value("${springdoc.version}") 
    String appVersion;

    @Bean
    public GroupedOpenApi helloOpenApi() {
        String[] paths = {"/v1/**"}; // { "/hello/**", "/k8s/**" };
        return GroupedOpenApi.builder()
        .group(appVersion)
        .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Hungry Panda API").version(appVersion)))
        .pathsToMatch(paths)
        .build();
    }

}
