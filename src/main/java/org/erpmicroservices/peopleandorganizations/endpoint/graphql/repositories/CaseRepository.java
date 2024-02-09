package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface CaseRepository extends JpaRepository<Case, UUID>, QuerydslPredicateExecutor<Case> {

}
