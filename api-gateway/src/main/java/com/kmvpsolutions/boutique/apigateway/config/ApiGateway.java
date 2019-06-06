package com.kmvpsolutions.boutique.apigateway.config;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApiGateway {

    private final ZuulProperties zuulProperties;

    public ApiGateway(ZuulProperties zuulProperties) {
        this.zuulProperties = zuulProperties;
    }

    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            this.zuulProperties
                    .getRoutes()
                    .values()
                    .forEach(route -> resources.add(createSwaggerResource(route.getId(), "2.0")));
            return resources;
        };
    }

    private SwaggerResource createSwaggerResource(String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(location);
        swaggerResource.setLocation("/" + location + "/v2/api-docs");
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
