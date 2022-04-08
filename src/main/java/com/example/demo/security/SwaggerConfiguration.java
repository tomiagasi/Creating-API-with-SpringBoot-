package com.example.demo.security;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {
	private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

	Contact contact = new Contact(
			"",
			"",
			"");

	List<VendorExtension> vext = new ArrayList<>();
	ApiInfo apiInfo = new ApiInfo(
			"Backend API",
			"This is API from Tomi Agasi",
			"1.0.0",
			"",
			contact,
			"",
			"",
			vext);

	@Bean
	public Docket api() {
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		parameterBuilder.name("Authorization")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.description("JWT token")
				.required(false)
				.build();
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(parameterBuilder.build());
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
				.produces(DEFAULT_PRODUCES_CONSUMES)
				.consumes(DEFAULT_PRODUCES_CONSUMES)
				.select()
				.build()
				// Setting globalOperationParameters ensures that authentication header is applied to all APIs
				.globalOperationParameters(parameters);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}