import {GraphQLDate} from 'graphql-iso-date'
import party_type_by_description from './party/by_description'
import party from './party/by_id'
import parties_by_type from './party/by_type'
import create_party from './party/create'
import delete_party from './party/delete'
import add_id_to_party from './party/id/add_to_party'
import PartyId from './party/id/type'
import parties from './party/list'
import add_name_to_party from './party/name/add_to_party'
import PartyName from './party/name/type'
import Party from './party/type'
import update_party from './party/update'
import add_case_role_type_child from './types/case/role/add_child'
import case_role_type_by_description from './types/case/role/by_description'
import create_case_role_type from './types/case/role/create'
import delete_case_role_type from './types/case/role/delete'
import CaseRoleType from './types/case/role/type'
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
import CommunicationEventPurposeType from './types/communication_event/purpose/type'
import update_communication_event_purpose_type from './types/communication_event/purpose/update'
import add_communication_event_role_type_child from './types/communication_event/role/add_child'
import communication_event_role_type_by_description from './types/communication_event/role/by_description'
import create_communication_event_role_type from './types/communication_event/role/create'
import delete_communication_event_role_type from './types/communication_event/role/delete'
import CommunicationEventRoleType from './types/communication_event/role/type'
import update_communication_event_role_type from './types/communication_event/role/update'
import add_communication_event_status_type_child from './types/communication_event/status/add_child'
import communication_event_status_type_by_description from './types/communication_event/status/by_description'
import create_communication_event_status_type from './types/communication_event/status/create'
import delete_communication_event_status_type from './types/communication_event/status/delete'
import CommunicationEventStatusType from './types/communication_event/status/type'
import update_communication_event_status_type from './types/communication_event/status/update'
import CommunicationEventType from './types/communication_event/type'
import update_communication_event_type from './types/communication_event/update'
import add_contact_mechanism_type_child from './types/contact_mechanism/add_child'
import contact_mechanism_type_by_description from './types/contact_mechanism/by_description'
import create_contact_mechanism_type from './types/contact_mechanism/create'
import delete_contact_mechanism_type from './types/contact_mechanism/delete'
import ContactMechanismType from './types/contact_mechanism/type'
import update_contact_mechanism_type from './types/contact_mechanism/update'
import add_facility_type_child from './types/facility/add_child'
import facility_type_by_description from './types/facility/by_description'
import create_facility_type from './types/facility/create'
import delete_facility_type from './types/facility/delete'
import add_facility_role_type_child from './types/facility/role/add_child'
import facility_role_type_by_description from './types/facility/role/by_description'
import create_facility_role_type from './types/facility/role/create'
import delete_facility_role_type from './types/facility/role/delete'
import FacilityRoleType from './types/facility/role/type'
import update_facility_role_type from './types/facility/role/update'
import FacilityType from './types/facility/type'
import update_facility_type from './types/facility/update'
import add_geographic_boundary_type_child from './types/geographic_boundary/add_child'
import geographic_boundary_type_by_description from './types/geographic_boundary/by_description'
import create_geographic_boundary_type from './types/geographic_boundary/create'
import delete_geographic_boundary_type from './types/geographic_boundary/delete'
import GeographicBoundaryType from './types/geographic_boundary/type'
import update_geographic_boundary_type from './types/geographic_boundary/update'
import add_party_type_child from './types/party/add'
import add_party_classification_type_child from './types/party/classification/add_child'
import party_classification_type_by_description from './types/party/classification/by_description'
import create_party_classification_type from './types/party/classification/create'
import delete_party_classification_type from './types/party/classification/delete'
import PartyClassificationType from './types/party/classification/type'
import update_party_classification_type from './types/party/classification/update'
import create_party_type from './types/party/create'
import delete_party_type from './types/party/delete'
import add_id_type_child from './types/party/id/add_child'
import id_type_by_description from './types/party/id/by_description'
import create_id_type from './types/party/id/create'
import delete_id_type from './types/party/id/delete'
import IdType from './types/party/id/type'
import update_id_type from './types/party/id/update'
import add_name_type_child from './types/party/name/add_child'
import name_type_by_description from './types/party/name/by_description'
import create_name_type from './types/party/name/create'
import delete_name_type from './types/party/name/delete'
import NameType from './types/party/name/type'
import update_name_type from './types/party/name/update'
import add_party_relationship_type_child from './types/party/relationship/add_child'
import party_relationship_type_by_description from './types/party/relationship/by_description'
import create_party_relationship_type from './types/party/relationship/create'
import delete_party_relationship_type from './types/party/relationship/delete'
import add_party_relationship_status_type_child from './types/party/relationship/status/add_child'
import party_relationship_status_type_by_description from './types/party/relationship/status/by_description'
import create_party_relationship_status_type from './types/party/relationship/status/create'
import delete_party_relationship_status_type from './types/party/relationship/status/delete'
import PartyRelationshipStatusType from './types/party/relationship/status/type'
import update_party_relationship_status_type from './types/party/relationship/status/update'
import PartyRelationshipType from './types/party/relationship/type'
import update_party_relationship_type from './types/party/relationship/update'
import add_party_role_type_child from './types/party/role/add_child'
import party_role_type_by_description from './types/party/role/by_description'
import create_party_role_type from './types/party/role/create'
import delete_party_role_type from './types/party/role/delete'
import PartyRoleType from './types/party/role/type'
import update_party_role_type from './types/party/role/update'
import PartyType from './types/party/type'
import update_party_type from './types/party/update'
import add_priority_type_child from './types/priority/add_child'
import priority_type_by_description from './types/priority/by_description'
import create_priority_type from './types/priority/create'
import delete_priority_type from './types/priority/delete'
import PriorityType from './types/priority/type'
import update_priority_type from './types/priority/update'

export default {
	CaseStatusType,
	CommunicationEventPurposeType,
	CommunicationEventRoleType,
	CommunicationEventStatusType,
	CommunicationEventType,
	ContactMechanismType,
	Date    : GraphQLDate,
	FacilityRoleType,
	FacilityType,
	GeographicBoundaryType,
	IdType,
	NameType,
	Party,
	PartyClassificationType,
	PartyId,
	PartyName,
	PartyRelationshipStatusType,
	PartyRelationshipType,
	PartyRoleType,
	PartyType,
	PriorityType,
	CaseRoleType,
	Query   : {
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
		id_type_by_description,
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
	Mutation: {
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
		add_id_to_party,
		add_id_type_child,
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
		create_id_type,
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
		delete_id_type,
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
		update_id_type,
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
