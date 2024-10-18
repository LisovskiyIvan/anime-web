package com.example.anime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .addOpenApiCustomizer(openApi -> {
                    Resource resource = new ClassPathResource("static/openapi-public.json");
                    try {
                        byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
                        String openApiContent = new String(data, StandardCharsets.UTF_8);

                        OpenAPI openAPI = new OpenAPIV3Parser().readContents(openApiContent, null, null).getOpenAPI();

                        openApi.info(openAPI.getInfo());
                        openApi.paths(openAPI.getPaths());
                        openApi.components(openAPI.getComponents());
                        openApi.security(openAPI.getSecurity());
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to load OpenAPI specification from file", e);
                    }
                })
                .pathsToMatch("/user/**", "/anime/**")
                .build();
    }


    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .addOpenApiCustomizer(openApi -> {
                    Resource resource = new ClassPathResource("static/openapi-admin.json");
                    try {
                        byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
                        String openApiContent = new String(data, StandardCharsets.UTF_8);

                        OpenAPI openAPI = new OpenAPIV3Parser().readContents(openApiContent, null, null).getOpenAPI();

                        openApi.info(openAPI.getInfo());
                        openApi.paths(openAPI.getPaths());
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to load OpenAPI specification from file", e);
                    }
                })
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/swagger-ui/**", "/v3/api-docs/**"
        );
    }


}
