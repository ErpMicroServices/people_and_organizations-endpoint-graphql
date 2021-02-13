package org.erpmicroservices.peopleandorganizations.endpoint.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRoleTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseRoleTypeQuery implements GraphQLQueryResolver {

 private CaseRoleTypeRepo caseRoleTypeRepo;

 @Autowired
 public CaseRoleTypeQuery(CaseRoleTypeRepo caseRoleTypeRepoØ) {
	this.caseRoleTypeRepo = caseRoleTypeRepo;
 }

 public Iterable<CaseRoleType> findAllCaseRoleTypes(int records, int start) {
	return caseRoleTypeRepo.findAll(PageRequest.of(start, records));
 }

 public List<CaseRoleType> case_role_type_by_description(String description) {
	return caseRoleTypeRepo.findByDescription(description);
 }
}
