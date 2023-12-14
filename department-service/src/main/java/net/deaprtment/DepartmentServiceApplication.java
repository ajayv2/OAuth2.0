package net.deaprtment;

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
				title = "Spring Boot Department Service API Documentation",
				description = "Spring BOOT Department Service API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Ajay",
						email = "ajay.upadhyay@v2solutions.com",
						url = "https://abc.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.abc.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot Department API documentation",
				url = "https://abc.com"
		)
)
public class DepartmentServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
