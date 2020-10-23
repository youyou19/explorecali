package com.itx.corps.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
@EnableSwagger2
public class ExplorecaliApplication {

	@Bean
	public Docket docket(){
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.itx.corps.playground")).paths(any()).build()
				.apiInfo(new ApiInfo("ITX.corps New York API's",
						"API's for the Explore New York API's", "1.0", null,
						new Contact("Final Interview", "https://www.itx.com/", ""),
						null, null, new ArrayList<>()));
	}

	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliApplication.class, args);
	}

}
