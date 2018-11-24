import {GraphQLDate} from 'graphql-iso-date'
import add_party_role_type_child from './add_party_role_type_child'
import add_party_type_child from './add_party_type_child'
import case_role_type_by_description from './case_role_type_by_description'
import case_status_type_by_description from './case_status_type_by_description'
import communication_event_purpose_type_by_description from './communication_event_purpose_type_by_description'
import communication_event_role_type_by_description from './communication_event_role_type_by_description'
import communication_event_status_type_by_description from './communication_event_status_type_by_description'
import communication_event_type_by_description from './communication_event_type_by_description'
import contact_mechanism_type_by_description from './contact_mechanism_type_by_description'
import create_case_role_type from './create_case_role_type'
import create_case_status_type from './create_case_status_type'
import create_communication_event_purpose_type from './create_communication_event_purpose_type'
import create_communication_event_role_type from './create_communication_event_role_type'
import create_communication_event_status_type from './create_communication_event_status_type'
import create_communication_event_type from './create_communication_event_type'
import create_contact_mechanism_type from './create_contact_mechanism_type'
import create_facility_role_type from './create_facility_role_type'
import create_facility_type from './create_facility_type'
import create_geographic_boundary_type from './create_geographic_boundary_type'
import create_party_classification_type from './create_party_classification_type'
import create_party_relationship_status_type from './create_party_relationship_status_type'
import create_party_relationship_type from './create_party_relationship_type'
import create_party_role_type from './create_party_role_type'
import create_party_type from './create_party_type'
import create_person from './create_person'
import create_priority_type from './create_priority_type'
import delete_case_role_type from './delete_case_role_type'
import delete_case_status_type from './delete_case_status_type'
import delete_communication_event_purpose_type from './delete_communication_event_purpose_type'
import delete_communication_event_role_type from './delete_communication_event_role_type'
import delete_communication_event_status_type from './delete_communication_event_status_type'
import delete_communication_event_type from './delete_communication_event_type'
import delete_contact_mechanism_type from './delete_contact_mechanism_type'
import delete_facility_role_type from './delete_facility_role_type'
import delete_facility_type from './delete_facility_type'
import delete_geographic_boundary_type from './delete_geographic_boundary_type'
import delete_organization from './delete_organization'
import delete_party_classification_type from './delete_party_classification_type'
import delete_party_relationship_status_type from './delete_party_relationship_status_type'
import delete_party_relationship_type from './delete_party_relationship_type'
import delete_party_role_type from './delete_party_role_type'
import delete_party_type from './delete_party_type'
import delete_person from './delete_person'
import delete_priority_type from './delete_priority_type'
import facility_role_type_by_description from './facility_role_type_by_description'
import facility_type_by_description from './facility_type_by_description'
import geographic_boundary_type_by_description from './geographic_boundary_type_by_description'
import organization_by_id from './organization_by_id'
import organizations from './organizations'
import parties from './parties'
import party_classification_type_by_description from './party_classification_type_by_description'
import party_relationship_status_type_by_description from './party_relationship_status_type_by_description'
import party_relationship_type_by_description from './party_relationship_type_by_description'
import party_role_type_by_description from './party_role_type_by_description'
import party_type_by_description from './party_type_by_description'
import people from "./people"
import person_by_id from './person_by_id'
import priority_type_by_description from './priority_type_by_description'
import update_case_role_type from './update_case_role_type'
import update_case_status_type from './update_case_status_type'
import update_communication_event_purpose_type from './update_communication_event_purpose_type'
import update_communication_event_role_type from './update_communication_event_role_type'
import update_communication_event_status_type from './update_communication_event_status_type'
import update_communication_event_type from './update_communication_event_type'
import update_contact_mechanism_type from './update_contact_mechanism_type'
import update_facility_role_type from './update_facility_role_type'
import update_facility_type from './update_facility_type'
import update_geographic_boundary_type from './update_geographic_boundary_type'
import update_organization from './update_organization'
import update_party_classification_type from './update_party_classification_type'
import update_party_relationship_status_type from './update_party_relationship_status_type'
import update_party_relationship_type from './update_party_relationship_type'
import update_party_role_type from './update_party_role_type'
import update_party_type from './update_party_type'
import update_person from "./update_person"
import update_priority_type from './update_priority_type'

export default {
	Date    : GraphQLDate,
	Party   : {
		__resolveType(obj, context, info) {
			if (obj.party_type_id === context.person_type_id()) {
				return 'Person'
			} else if (obj.party_type_id == context.organization_type_id()) {
				return 'Organization'
			} else {
				return null
			}
		}
	},
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
		organization_by_id,
		organizations,
		parties,
		party_classification_type_by_description,
		party_relationship_status_type_by_description,
		party_relationship_type_by_description,
		party_role_type_by_description,
		party_type_by_description,
		people,
		person_by_id,
		priority_type_by_description
	},
	Mutation: {
		add_party_role_type_child,
		add_party_type_child,
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
		create_party_classification_type,
		create_party_relationship_status_type,
		create_party_relationship_type,
		create_party_role_type,
		create_party_type,
		create_person,
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
		delete_party_classification_type,
		delete_party_relationship_status_type,
		delete_party_relationship_type,
		delete_party_role_type,
		delete_priority_type,
		delete_organization,
		delete_party_type,
		delete_person,
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
		update_organization,
		update_party_classification_type,
		update_party_relationship_type,
		update_party_relationship_status_type,
		update_party_role_type,
		update_party_type,
		update_person,
		update_priority_type,
	}
}
