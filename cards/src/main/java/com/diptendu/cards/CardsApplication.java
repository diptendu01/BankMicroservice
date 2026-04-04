package com.diptendu.cards;

import com.diptendu.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.diptendu.cards.controller") })
@EnableJpaRepositories("com.diptendu.cards.repository")
@EntityScan("com.diptendu.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Diptendu Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Diptendu Das",
						email = "ddas2367@gmail.com",
						url = "https://www.diptendu.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.diptendu.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Diptendu Cards microservice REST API Documentation",
				url = "https://www.diptendu.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
