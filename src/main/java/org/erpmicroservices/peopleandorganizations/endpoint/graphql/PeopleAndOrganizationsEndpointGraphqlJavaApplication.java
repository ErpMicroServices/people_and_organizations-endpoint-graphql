package org.erpmicroservices.peopleandorganizations.endpoint.graphql;

import graphql.scalars.ExtendedScalars;
import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@SpringBootApplication
public class PeopleAndOrganizationsEndpointGraphqlJavaApplication implements RuntimeWiringConfigurer {

	public static void main(String... args) {
		SpringApplication.run(PeopleAndOrganizationsEndpointGraphqlJavaApplication.class, args);
	}

	/**
	 * Apply changes to the {@link RuntimeWiring.Builder} such as registering
	 * {@link DataFetcher}s, custom scalar types, and more.
	 *
	 * @param builder the builder to configure
	 */
	@Override
	public void configure(final RuntimeWiring.Builder builder) {
		builder
				.scalar(ExtendedScalars.DateTime)
				.scalar(ExtendedScalars.Date);
	}
}
