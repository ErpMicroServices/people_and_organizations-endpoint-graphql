package org.erpmicroservices.peopleandorganizations.endpoint.graphql;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Map;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureGraphQlTester
public class CucumberSpringBootContext {

    protected GraphQlTester.Response response;

    @Autowired
    protected GraphQlTester graphQlTester;

//    public CucumberSpringBootContext(@Autowired GraphQlTester graphQlTester) {
//        this.graphQlTester = graphQlTester;
//    }

    protected static Map<String, String> pageInfoSortingOn(final String sortBy) {
        return Map.of("pageNumber", "0"
                , "pageSize", "100"
                , "sortBy", sortBy
                , "sortDirection", "ASC");
    }
}
