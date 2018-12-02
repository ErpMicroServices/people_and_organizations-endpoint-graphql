import {GraphQLDate} from 'graphql-iso-date'
import add_name_to_party from './name/add_to_party'
import party_type_by_description from './party/by_description'
import party from './party/by_id'
import parties_by_type from './party/by_type'
import create_party from './party/create'
import delete_party from './party/delete'
import parties from './party/list'
import update_party from './party/update'
import add_case_role_type_child from './types/case/role/add_child'
import case_role_type_by_description from './types/case/role/by_description'
import create_case_role_type from './types/case/role/create'
import delete_case_role_type from './types/case/role/delete'
import update_case_role_type from './types/case/role/update'
import add_case_status_type_child from './types/case/status/add_child'
import case_status_type_by_description from './types/case/status/by_description'
import create_case_status_type from './types/case/status/create'
import delete_case_status_type from './types/case/status/delete'
import CaseStatusType from './types/case/status/type'
import update_case_status_type from './types/case/status/update'
import add_communication_event_type_child from './types/communication_event/add_child'
import communication_event_type_by_description from './types/communication_event/by_description'
import create_communication_event_type from './types/communication_event/create'
import delete_communication_event_type from './types/communication_event/delete'
import add_communication_event_purpose_type_child from './types/communication_event/purpose/add_child'
import communication_event_purpose_type_by_description from './types/communication_event/purpose/by_description'
import create_communication_event_purpose_type from './types/communication_event/purpose/create'
import delete_communication_event_purpose_type from './types/communication_event/purpose/delete'
import update_communication_event_purpose_type from './types/communication_event/purpose/update'
import add_communication_event_role_type_child from './types/communication_event/role/add_child'
import communication_event_role_type_by_description from './types/communication_event/role/by_description'
import create_communication_event_role_type from './types/communication_event/role/create'
import delete_communication_event_role_type from './types/communication_event/role/delete'
import update_communication_event_role_type from './types/communication_event/role/update'
import add_communication_event_status_type_child from './types/communication_event/status/add_child'
import communication_event_status_type_by_description from './types/communication_event/status/by_description'
import create_communication_event_status_type from './types/communication_event/status/create'
import delete_communication_event_status_type from './types/communication_event/status/delete'
import update_communication_event_status_type from './types/communication_event/status/update'
import update_communication_event_type from './types/communication_event/update'
import add_contact_mechanism_type_child from './types/contact_mechanism/add_child'
import contact_mechanism_type_by_description from './types/contact_mechanism/by_description'
import create_contact_mechanism_type from './types/contact_mechanism/create'
import delete_contact_mechanism_type from './types/contact_mechanism/delete'
import update_contact_mechanism_type from './types/contact_mechanism/update'
import add_facility_type_child from './types/facility/add_child'
import facility_type_by_description from './types/facility/by_description'
import create_facility_type from './types/facility/create'
import delete_facility_type from './types/facility/delete'
import add_facility_role_type_child from './types/facility/role/add_child'
import facility_role_type_by_description from './types/facility/role/by_description'
import create_facility_role_type from './types/facility/role/create'
import delete_facility_role_type from './types/facility/role/delete'
import update_facility_role_type from './types/facility/role/update'
import update_facility_type from './types/facility/update'
import add_geographic_boundary_type_child from './types/geographic_boundary/add_child'
import geographic_boundary_type_by_description from './types/geographic_boundary/by_description'
import create_geographic_boundary_type from './types/geographic_boundary/create'
import delete_geographic_boundary_type from './types/geographic_boundary/delete'
import update_geographic_boundary_type from './types/geographic_boundary/update'
import add_name_type_child from './types/name/add_child'
import name_type_by_description from './types/name/by_description'
import create_name_type from './types/name/create'
import delete_name_type from './types/name/delete'
import update_name_type from './types/name/update'
import add_party_type_child from './types/party/add'
import add_party_classification_type_child from './types/party/classification/add_child'
import party_classification_type_by_description from './types/party/classification/by_description'
import create_party_classification_type from './types/party/classification/create'
import delete_party_classification_type from './types/party/classification/delete'
import update_party_classification_type from './types/party/classification/update'
import create_party_type from './types/party/create'
import delete_party_type from './types/party/delete'
import add_party_relationship_type_child from './types/party/relationship/add_child'
import party_relationship_type_by_description from './types/party/relationship/by_description'
import create_party_relationship_type from './types/party/relationship/create'
import delete_party_relationship_type from './types/party/relationship/delete'
import add_party_relationship_status_type_child from './types/party/relationship/status/add_child'
import party_relationship_status_type_by_description from './types/party/relationship/status/by_description'
import create_party_relationship_status_type from './types/party/relationship/status/create'
import delete_party_relationship_status_type from './types/party/relationship/status/delete'
import update_party_relationship_status_type from './types/party/relationship/status/update'
import update_party_relationship_type from './types/party/relationship/update'
import add_party_role_type_child from './types/party/role/add_child'
import party_role_type_by_description from './types/party/role/by_description'
import create_party_role_type from './types/party/role/create'
import delete_party_role_type from './types/party/role/delete'
import update_party_role_type from './types/party/role/update'
import update_party_type from './types/party/update'
import add_priority_type_child from './types/priority/add_child'
import priority_type_by_description from './types/priority/by_description'
import create_priority_type from './types/priority/create'
import delete_priority_type from './types/priority/delete'
import update_priority_type from './types/priority/update'

export default {
	CaseStatusType,
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
