package org.erpmicroservices.peopleandorganizations.endpoint.graphql;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@Configuration
public class GraphqlConfiguration {
	//	@Bean
//	public GraphQLScalarType dateTime() {
//		return ExtendedScalars.DateTime;
//	}
//
//	@Bean
//	ObjectMapperConfigurer objectMapperConfigurer() {
//		return (ObjectMapper mapper) -> mapper.registerModule(new JavaTimeModule());
//	}
	@Bean
	public GraphQLScalarType zonedDateTimeScalar() {
		return GraphQLScalarType.newScalar()
				       .name("DateTime")
				       .description("Java 8 Zoned Date Time as scalar.")
				       .coercing(new Coercing<ZonedDateTime, String>() {
					       @Override
					       public String serialize(final Object dataFetcherResult) {
						       if (dataFetcherResult instanceof ZonedDateTime) {
							       return dataFetcherResult.toString();
						       } else {
							       throw new CoercingSerializeException("Expected a ZonedDateTime object.");
						       }
					       }

					       @Override
					       public ZonedDateTime parseValue(final Object input) {
						       try {
							       if (input instanceof String) {
								       return ZonedDateTime.parse((String) input);
							       } else {
								       throw new CoercingParseValueException("Expected a String");
							       }
						       } catch (DateTimeParseException e) {
							       throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
							       );
						       }
					       }

					       @Override
					       public ZonedDateTime parseLiteral(final Object input) {
						       if (input instanceof StringValue) {
							       try {
								       return ZonedDateTime.parse(((StringValue) input).getValue());
							       } catch (DateTimeParseException e) {
								       throw new CoercingParseLiteralException(e);
							       }
						       } else {
							       throw new CoercingParseLiteralException("Expected a StringValue.");
						       }
					       }
				       }).build();
	}
}
