package org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utils {

	public static Pageable pageInfoToPageable(final PageInfo pageInfo) {
		final Sort sort = Sort.by(pageInfo.getSortDirection(), pageInfo.getSortBy().toArray(new String[0]));
		return PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize(), sort);
	}
}
