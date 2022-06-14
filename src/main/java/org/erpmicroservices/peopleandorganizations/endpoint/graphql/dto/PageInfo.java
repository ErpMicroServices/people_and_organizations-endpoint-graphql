package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class PageInfo {

	@Builder.Default
	private int pageSize = 100;
	@Builder.Default
	private int pageNumber = 0;
	@Builder.Default
	private List<String> sortBy = Collections.emptyList();
	@Builder.Default
	private Sort.Direction sortDirection = Sort.DEFAULT_DIRECTION;

}
