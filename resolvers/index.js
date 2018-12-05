import {GraphQLDate} from 'graphql-iso-date'
import party from './party/by_id'
import parties_by_type from './party/by_type'
import party_create from './party/create'
import party_delete from './party/delete'
import party_id_add_to_party from './party/id/add_to_party'
import party_id_delete from './party/id/delete'
import PartyId from './party/id/type'
import party_id_update from './party/id/update_id'
import parties from './party/list'
import party_name_add_to_party from './party/name/add_to_party'
import party_name_delete from './party/name/delete'
import PartyName from './party/name/type'
import party_name_update from './party/name/update'
import Party from './party/type'
import party_update from './party/update'
import case_role_type_add_child from './types/case/role/add_child'
import case_role_type_by_description from './types/case/role/by_description'
import case_role_type_create from './types/case/role/create'
import case_role_type_delete from './types/case/role/delete'
import CaseRoleType from './types/case/role/type'
import case_role_type_update from './types/case/role/update'
import case_status_type_add_child from './types/case/status/add_child'
import case_status_type_by_description from './types/case/status/by_description'
import case_status_type_create from './types/case/status/create'
import case_status_type_delete from './types/case/status/delete'
import CaseStatusType from './types/case/status/type'
import case_status_type_update from './types/case/status/update'
import communication_event_type_add_child from './types/communication_event/add_child'
import communication_event_type_by_description from './types/communication_event/by_description'
import communication_event_type_create from './types/communication_event/create'
import communication_event_type_delete from './types/communication_event/delete'
import communication_event_purpose_type_add_child from './types/communication_event/purpose/add_child'
import communication_event_purpose_type_by_description from './types/communication_event/purpose/by_description'
import communication_event_purpose_type_create from './types/communication_event/purpose/create'
import communication_event_purpose_type_delete from './types/communication_event/purpose/delete'
import CommunicationEventPurposeType from './types/communication_event/purpose/type'
import communication_event_purpose_type_update from './types/communication_event/purpose/update'
import communication_event_role_type_add_child from './types/communication_event/role/add_child'
import communication_event_role_type_by_description from './types/communication_event/role/by_description'
import communication_event_role_type_create from './types/communication_event/role/create'
import communication_event_role_type_delete from './types/communication_event/role/delete'
import CommunicationEventRoleType from './types/communication_event/role/type'
import communication_event_role_type_update from './types/communication_event/role/update'
import communication_event_status_type_add_child from './types/communication_event/status/add_child'
import communication_event_status_type_by_description from './types/communication_event/status/by_description'
import communication_event_status_type_create from './types/communication_event/status/create'
import communication_event_status_type_delete from './types/communication_event/status/delete'
import CommunicationEventStatusType from './types/communication_event/status/type'
import communication_event_status_type_update from './types/communication_event/status/update'
import CommunicationEventType from './types/communication_event/type'
import communication_event_type_update from './types/communication_event/update'
import contact_mechanism_type_add_child from './types/contact_mechanism/add_child'
import contact_mechanism_type_by_description from './types/contact_mechanism/by_description'
import contact_mechanism_type_create from './types/contact_mechanism/create'
import contact_mechanism_type_delete from './types/contact_mechanism/delete'
import ContactMechanismType from './types/contact_mechanism/type'
import contact_mechanism_type_update from './types/contact_mechanism/update'
import facility_type_add_child from './types/facility/add_child'
import facility_type_by_description from './types/facility/by_description'
import facility_type_create from './types/facility/create'
import facility_type_delete from './types/facility/delete'
import facility_role_type_add_child from './types/facility/role/add_child'
import facility_role_type_by_description from './types/facility/role/by_description'
import facility_role_type_create from './types/facility/role/create'
import facility_role_type_delete from './types/facility/role/delete'
import FacilityRoleType from './types/facility/role/type'
import facility_role_type_update from './types/facility/role/update'
import FacilityType from './types/facility/type'
import facility_type_update from './types/facility/update'
import geographic_boundary_type_add_child from './types/geographic_boundary/add_child'
import geographic_boundary_type_by_description from './types/geographic_boundary/by_description'
import geographic_boundary_type_create from './types/geographic_boundary/create'
import geographic_boundary_type_delete from './types/geographic_boundary/delete'
import GeographicBoundaryType from './types/geographic_boundary/type'
import geographic_boundary_type_update from './types/geographic_boundary/update'
import party_type_add_child from './types/party/add'
import party_type_by_description from './types/party/by_description'
import party_classification_type_add_child from './types/party/classification/add_child'
import party_classification_type_by_description from './types/party/classification/by_description'
import party_classification_type_create from './types/party/classification/create'
import party_classification_type_delete from './types/party/classification/delete'

import PartyClassificationType from './types/party/classification/type'
import party_classification_type_update from './types/party/classification/update'
import party_type_create from './types/party/create'
import party_type_delete from './types/party/delete'
import id_type_add_child from './types/party/id/add_child'
import id_type_by_description from './types/party/id/by_description'
import id_type_create from './types/party/id/create'
import id_type_delete from './types/party/id/delete'
import IdType from './types/party/id/type'
import id_type_update from './types/party/id/update'
import name_type_add_child from './types/party/name/add_child'
import name_type_by_description from './types/party/name/by_description'
import name_type_create from './types/party/name/create'
import name_type_delete from './types/party/name/delete'
import NameType from './types/party/name/type'
import name_type_update from './types/party/name/update'
import party_relationship_type_add_child from './types/party/relationship/add_child'
import party_relationship_type_by_description from './types/party/relationship/by_description'
import party_relationship_type_create from './types/party/relationship/create'
import party_relationship_type_delete from './types/party/relationship/delete'
import party_relationship_status_type_add_child from './types/party/relationship/status/add_child'
import party_relationship_status_type_by_description from './types/party/relationship/status/by_description'
import party_relationship_status_type_create from './types/party/relationship/status/create'
import party_relationship_status_type_delete from './types/party/relationship/status/delete'
import PartyRelationshipStatusType from './types/party/relationship/status/type'
import party_relationship_status_type_update from './types/party/relationship/status/update'
import PartyRelationshipType from './types/party/relationship/type'
import party_relationship_type_update from './types/party/relationship/update'
import party_role_type_add_child from './types/party/role/add_child'
import party_role_type_by_description from './types/party/role/by_description'
import party_role_type_create from './types/party/role/create'
import party_role_type_delete from './types/party/role/delete'
import PartyRoleType from './types/party/role/type'
import party_role_type_update from './types/party/role/update'
import PartyType from './types/party/type'
import party_type_update from './types/party/update'
import priority_type_add_child from './types/priority/add_child'
import priority_type_by_description from './types/priority/by_description'
import priority_type_create from './types/priority/create'
import priority_type_delete from './types/priority/delete'
import PriorityType from './types/priority/type'
import priority_type_update from './types/priority/update'

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
		case_role_type_by_description,
		case_status_type_by_description,
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
		case_role_type_add_child,
		case_role_type_create,
		case_role_type_delete,
		case_role_type_update,
		case_status_type_add_child,
		case_status_type_create,
		case_status_type_delete,
		case_status_type_update,
		communication_event_purpose_type_add_child,
		communication_event_purpose_type_create,
		communication_event_purpose_type_delete,
		communication_event_purpose_type_update,
		communication_event_role_type_add_child,
		communication_event_role_type_create,
		communication_event_role_type_delete,
		communication_event_role_type_update,
		communication_event_status_type_add_child,
		communication_event_status_type_create,
		communication_event_status_type_delete,
		communication_event_status_type_update,
		communication_event_type_add_child,
		communication_event_type_create,
		communication_event_type_delete,
		communication_event_type_update,
		contact_mechanism_type_add_child,
		contact_mechanism_type_create,
		contact_mechanism_type_delete,
		contact_mechanism_type_update,
		facility_role_type_add_child,
		facility_role_type_create,
		facility_role_type_delete,
		facility_role_type_update,
		facility_type_add_child,
		facility_type_create,
		facility_type_delete,
		facility_type_update,
		geographic_boundary_type_add_child,
		geographic_boundary_type_create,
		geographic_boundary_type_delete,
		geographic_boundary_type_update,
		id_type_add_child,
		id_type_create,
		id_type_delete,
		id_type_update,
		name_type_add_child,
		name_type_create,
		name_type_delete,
		name_type_update,
		party_classification_type_add_child,
		party_classification_type_create,
		party_classification_type_delete,
		party_classification_type_update,
		party_create,
		party_delete,
		party_update,
		party_id_add_to_party,
		party_id_delete,
		party_id_update,
		party_name_add_to_party,
		party_name_delete,
		party_name_update,
		party_relationship_status_type_add_child,
		party_relationship_status_type_create,
		party_relationship_status_type_delete,
		party_relationship_status_type_update,
		party_relationship_type_add_child,
		party_relationship_type_create,
		party_relationship_type_delete,
		party_relationship_type_update,
		party_role_type_add_child,
		party_role_type_create,
		party_role_type_delete,
		party_role_type_update,
		party_type_add_child,
		party_type_create,
		party_type_delete,
		party_type_update,
		priority_type_add_child,
		priority_type_create,
		priority_type_delete,
		priority_type_update
	}
}
