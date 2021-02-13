package org.erpmicroservices.peopleandorganizations.endpoint.graphql.resolvers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseTypeQueries {

 @Autowired
 private CaseTypeRepo caseTypeRepo;

 public List<CaseType> case_type_by_description(String description) {
  return caseTypeRepo.findByDescription(description);
 }
}
