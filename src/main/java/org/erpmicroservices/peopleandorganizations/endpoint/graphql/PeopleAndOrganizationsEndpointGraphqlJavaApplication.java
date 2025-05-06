package org.erpmicroservices.peopleandorganizations.endpoint.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.erpmicroservices.peopleandorganizations.backend")
public class PeopleAndOrganizationsEndpointGraphqlJavaApplication {

	public static void main(String... args) {
		SpringApplication.run(PeopleAndOrganizationsEndpointGraphqlJavaApplication.class, args);
	}

}
