package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeTestDataBuilder {

	public static final ZonedDateTime zonedDateTimeNow() {
		return ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).withNano(0);
	}
}
