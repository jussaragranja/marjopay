package com.hackaton.marjopay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("API Documentation")
						.version("1.0")
				.description("API documentation for the application")
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
				//.addSecurityItem(new SecurityRequirement().addList("JWT"))
	            //.components(new Components().addSecuritySchemes("JWT", createJWT()));
	}

	private SecurityScheme createJWT() {
		return new SecurityScheme()
	            .name("JWT")
	            .type(SecurityScheme.Type.HTTP)
	            .scheme("bearer")
	            .bearerFormat("JWT")
	            .in(SecurityScheme.In.HEADER)
	            .name("Authorization");
	}

}