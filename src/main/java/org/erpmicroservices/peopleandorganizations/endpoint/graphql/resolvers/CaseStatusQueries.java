package org.erpmicroservices.peopleandorganizations.endpoint.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseStatusTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CaseStatusQueries implements GraphQLQueryResolver {

 @Autowired
 private CaseStatusTypeRepo caseStatusTypeRepo;

 public List<CaseStatusType> case_status_type_by_description(String description) {
  return caseStatusTypeRepo.findByDescription(description);
 }
}
