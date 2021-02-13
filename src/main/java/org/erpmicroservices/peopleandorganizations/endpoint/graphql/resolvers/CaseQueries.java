package org.erpmicroservices.peopleandorganizations.endpoint.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CaseQueries implements GraphQLQueryResolver {


 private final CaseRepo caseRepo;

 @Autowired
 public CaseQueries(CaseRepo caseRepo) {
  this.caseRepo = caseRepo;
 }

 public Case case_by_id(UUID id) {
  return caseRepo.findById(id).get();
 }

}
