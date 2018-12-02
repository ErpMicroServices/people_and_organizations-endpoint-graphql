import {GraphQLDate} from 'graphql-iso-date'
import add_name_to_party from './name/add_name_to_party'
import create_party from './party/create_party'
import delete_party from './party/delete_party'
import parties from './party/parties'
import parties_by_type from './party/parties_by_type'
import party from './party/party'
import update_party from './party/update_party'
import add_case_role_type_child from './types/case_role/add_case_role_type_child'
import case_role_type_by_description from './types/case_role/case_role_type_by_description'
import create_case_role_type from './types/case_role/create_case_role_type'
import delete_case_role_type from './types/case_role/delete_case_role_type'
import party_type_by_description from './types/case_role/party_type_by_description'
import update_case_role_type from './types/case_role/update_case_role_type'
import add_case_status_type_child from './types/case_status/add_case_status_type_child'
import case_status_type_by_description from './types/case_status/case_status_type_by_description'
import create_case_status_type from './types/case_status/create_case_status_type'
import delete_case_status_type from './types/case_status/delete_case_status_type'
import update_case_status_type from './types/case_status/update_case_status_type'
import add_communication_event_type_child from './types/communication_event/add_communication_event_type_child'
import communication_event_type_by_description
	from './types/communication_event/communication_event_type_by_description'
import create_communication_event_type from './types/communication_event/create_communication_event_type'
import delete_communication_event_type from './types/communication_event/delete_communication_event_type'
import update_communication_event_type from './types/communication_event/update_communication_event_type'
import add_communication_event_purpose_type_child
	from './types/communication_event_purpose/add_communication_event_purpose_type_child'
import communication_event_purpose_type_by_description
	from './types/communication_event_purpose/communication_event_purpose_type_by_description'
import create_communication_event_purpose_type
	from './types/communication_event_purpose/create_communication_event_purpose_type'
import delete_communication_event_purpose_type
	from './types/communication_event_purpose/delete_communication_event_purpose_type'
import update_communication_event_purpose_type
	from './types/communication_event_purpose/update_communication_event_purpose_type'
import add_communication_event_role_type_child
	from './types/communication_event_role/add_communication_event_role_type_child'
import communication_event_role_type_by_description
	from './types/communication_event_role/communication_event_role_type_by_description'
import create_communication_event_role_type from './types/communication_event_role/create_communication_event_role_type'
import delete_communication_event_role_type from './types/communication_event_role/delete_communication_event_role_type'
import update_communication_event_role_type from './types/communication_event_role/update_communication_event_role_type'
import add_communication_event_status_type_child
	from './types/communication_event_status/add_communication_event_status_type_child'
import communication_event_status_type_by_description
	from './types/communication_event_status/communication_event_status_type_by_description'
import create_communication_event_status_type
	from './types/communication_event_status/create_communication_event_status_type'
import delete_communication_event_status_type
	from './types/communication_event_status/delete_communication_event_status_type'
import update_communication_event_status_type
	from './types/communication_event_status/update_communication_event_status_type'
import add_contact_mechanism_type_child from './types/contact_mechanism/add_contact_mechanism_type_child'
import contact_mechanism_type_by_description from './types/contact_mechanism/contact_mechanism_type_by_description'
import create_contact_mechanism_type from './types/contact_mechanism/create_contact_mechanism_type'
import delete_contact_mechanism_type from './types/contact_mechanism/delete_contact_mechanism_type'
import update_contact_mechanism_type from './types/contact_mechanism/update_contact_mechanism_type'
import add_facility_type_child from './types/facility/add_facility_type_child'
import create_facility_type from './types/facility/create_facility_type'
import delete_facility_type from './types/facility/delete_facility_type'
import facility_type_by_description from './types/facility/facility_type_by_description'
import update_facility_type from './types/facility/update_facility_type'
import add_facility_role_type_child from './types/facility_role/add_facility_role_type_child'
import create_facility_role_type from './types/facility_role/create_facility_role_type'
import delete_facility_role_type from './types/facility_role/delete_facility_role_type'
import facility_role_type_by_description from './types/facility_role/facility_role_type_by_description'
import update_facility_role_type from './types/facility_role/update_facility_role_type'
import add_geographic_boundary_type_child from './types/geographic_boundary/add_geographic_boundary_type_child'
import create_geographic_boundary_type from './types/geographic_boundary/create_geographic_boundary_type'
import delete_geographic_boundary_type from './types/geographic_boundary/delete_geographic_boundary_type'
import geographic_boundary_type_by_description
	from './types/geographic_boundary/geographic_boundary_type_by_description'
import update_geographic_boundary_type from './types/geographic_boundary/update_geographic_boundary_type'
import add_name_type_child from './types/name/add_name_type_child'
import create_name_type from './types/name/create_name_type'
import delete_name_type from './types/name/delete_name_type'
import name_type_by_description from './types/name/name_type_by_description'
import update_name_type from './types/name/update_name_type'
import add_party_type_child from './types/party/add_party_type_child'
import create_party_type from './types/party/create_party_type'
import delete_party_type from './types/party/delete_party_type'
import update_party_type from './types/party/update_party_type'
import add_party_classification_type_child from './types/party_classification/add_party_classification_type_child'
import create_party_classification_type from './types/party_classification/create_party_classification_type'
import delete_party_classification_type from './types/party_classification/delete_party_classification_type'
import party_classification_type_by_description
	from './types/party_classification/party_classification_type_by_description'
import update_party_classification_type from './types/party_classification/update_party_classification_type'
import add_party_relationship_type_child from './types/party_relationship/add_party_relationship_type_child'
import create_party_relationship_type from './types/party_relationship/create_party_relationship_type'
import delete_party_relationship_type from './types/party_relationship/delete_party_relationship_type'
import party_relationship_type_by_description from './types/party_relationship/party_relationship_type_by_description'
import update_party_relationship_type from './types/party_relationship/update_party_relationship_type'
import add_party_relationship_status_type_child
	from './types/party_relationship_status/add_party_relationship_status_type_child'
import create_party_relationship_status_type
	from './types/party_relationship_status/create_party_relationship_status_type'
import delete_party_relationship_status_type
	from './types/party_relationship_status/delete_party_relationship_status_type'
import party_relationship_status_type_by_description
	from './types/party_relationship_status/party_relationship_status_type_by_description'
import update_party_relationship_status_type
	from './types/party_relationship_status/update_party_relationship_status_type'
import add_party_role_type_child from './types/party_role/add_party_role_type_child'
import create_party_role_type from './types/party_role/create_party_role_type'
import delete_party_role_type from './types/party_role/delete_party_role_type'
import party_role_type_by_description from './types/party_role/party_role_type_by_description'
import update_party_role_type from './types/party_role/update_party_role_type'
import add_priority_type_child from './types/priority/add_priority_type_child'
import create_priority_type from './types/priority/create_priority_type'
import delete_priority_type from './types/priority/delete_priority_type'
import priority_type_by_description from './types/priority/priority_type_by_description'
import update_priority_type from './types/priority/update_priority_type'

export default {
	CaseStatusType               : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from case_status_type where parent_id=${id}', parent)
		}
	},
	CommunicationEventPurposeType: {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from communication_event_purpose_type where parent_id=${id}', parent)
		}
	},
	CommunicationEventRoleType   : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from communication_event_role_type where parent_id=${id}', parent)
		}
	},
	CommunicationEventStatusType : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from communication_event_status_type where parent_id=${id}', parent)
		}
	},
	CommunicationEventType       : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from communication_event_type where parent_id=${id}', parent)
		}
	},
	ContactMechanismType         : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from contact_mechanism_type where parent_id=${id}', parent)
		}
	},
	Date                         : GraphQLDate,
	FacilityRoleType             : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from facility_role_type where parent_id=${id}', parent)
		}
	},
	FacilityType                 : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from facility_type where parent_id=${id}', parent)
		}
	},
	GeographicBoundaryType       : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from geographic_boundary_type where parent_id=${id}', parent)
		}
	},
	NameType                     : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from name_type where parent_id=${id}', parent)
		}
	},
	Party                        : {
		names(parent, args, context, info) {

			return context.database.any('select id, name, from_date, thru_date, name_type_id from party_name where party_id = ${id}', parent)
				.then(data => {
					console.log('party.names: ', data)
					return data
				})
				.catch(error => console.log('party.name: ', error))
		},
		party_type(parent, args, context, info) {
			return context.database.one('select id, description, parent_id from party_type where id = ${party_type_id}', parent)
		}
	},
	PartyClassificationType      : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from party_classification_type where parent_id=${id}', parent)
		}
	},
	PartyName                    : {
		name_type(parent, args, context, info) {
			return context.database.one('select id, description, parent_id from name_type where id=${name_type_id} order by description', parent)
		}
	},
	PartyRelationshipStatusType  : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from party_relationship_status_type where parent_id=${id}', parent)
		}
	},
	PartyRelationshipType        : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from party_relationship_type where parent_id=${id}', parent)
		}
	},
	PartyRoleType                : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from party_role_type where parent_id=${id}', parent)
		}
	},
	PartyType                    : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from party_type where parent_id=${id}', parent)
		}
	},
	PriorityType                 : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from priority_type where parent_id=${id}', parent)
		}
	},
	CaseRoleType                 : {
		children(parent, args, context, info) {
			return context.database.any('select id, description, parent_id from case_role_type where parent_id=${id}', parent)
		}
	},
	Query                        : {
		case_status_type_by_description,
		case_role_type_by_description,
		communication_event_purpose_type_by_description,
		communication_event_role_type_by_description,
		communication_event_status_type_by_description,
		communication_event_type_by_description,
		contact_mechanism_type_by_description,
		facility_role_type_by_description,
		facility_type_by_description,
		geographic_boundary_type_by_description,
		name_type_by_description,
		party,
		parties,
		parties_by_type,
		party_classification_type_by_description,
		party_relationship_status_type_by_description,
		party_relationship_type_by_description,
		party_role_type_by_description,
		party_type_by_description,
		priority_type_by_description
	}
	,
	Mutation                     : {
		add_case_status_type_child,
		add_case_role_type_child,
		add_communication_event_purpose_type_child,
		add_communication_event_role_type_child,
		add_communication_event_status_type_child,
		add_communication_event_type_child,
		add_contact_mechanism_type_child,
		add_facility_role_type_child,
		add_facility_type_child,
		add_geographic_boundary_type_child,
		add_name_to_party,
		add_name_type_child,
		add_party_classification_type_child,
		add_party_relationship_type_child,
		add_party_relationship_status_type_child,
		add_party_role_type_child,
		add_party_type_child,
		add_priority_type_child,
		create_facility_role_type,
		create_facility_type,
		create_case_role_type,
		create_case_status_type,
		create_communication_event_role_type,
		create_communication_event_purpose_type,
		create_communication_event_status_type,
		create_communication_event_type,
		create_contact_mechanism_type,
		create_geographic_boundary_type,
		create_name_type,
		create_party,
		create_party_classification_type,
		create_party_relationship_status_type,
		create_party_relationship_type,
		create_party_role_type,
		create_party_type,
		create_priority_type,
		delete_case_role_type,
		delete_case_status_type,
		delete_communication_event_purpose_type,
		delete_communication_event_role_type,
		delete_communication_event_status_type,
		delete_communication_event_type,
		delete_contact_mechanism_type,
		delete_facility_role_type,
		delete_facility_type,
		delete_geographic_boundary_type,
		delete_name_type,
		delete_party,
		delete_party_classification_type,
		delete_party_relationship_status_type,
		delete_party_relationship_type,
		delete_party_role_type,
		delete_priority_type,
		delete_party_type,
		update_case_status_type,
		update_case_role_type,
		update_contact_mechanism_type,
		update_communication_event_purpose_type,
		update_communication_event_status_type,
		update_communication_event_role_type,
		update_communication_event_type,
		update_facility_role_type,
		update_facility_type,
		update_geographic_boundary_type,
		update_name_type,
		update_party,
		update_party_classification_type,
		update_party_relationship_type,
		update_party_relationship_status_type,
		update_party_role_type,
		update_party_type,
		update_priority_type,
	}
}
