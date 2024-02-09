package org.erpmicroservices.peopleandorganizations.endpoint.graphql;

import graphql.scalars.ExtendedScalars;
import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@SpringBootApplication
public class PeopleAndOrganizationsEndpointGraphqlJavaApplication {

	public static void main(String... args) {
		SpringApplication.run(PeopleAndOrganizationsEndpointGraphqlJavaApplication.class, args);
	}

}
