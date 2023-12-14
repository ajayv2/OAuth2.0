package net.orgranization;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Organization Service API Documentation",
				description = "Spring BOOT Organization Service API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Smita",
						email = "smita.shrivastava@v2solutions.com",
						url = "https://abc.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.abc.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot organization Service API documentation",
				url = "https://abc.com"
		)
)
public class OrganizationServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
