import {GraphQLDate} from 'graphql-iso-date'
import add_party_role_type_child from './add_party_role_type_child'
import add_party_type_child from './add_party_type_child'
import create_party_role_type from './create_party_role_type'
import create_party_type from './create_party_type'
import create_person from './create_person'
import delete_organization from './delete_organization'
import delete_party_role_type from './delete_party_role_type'
import delete_party_type from './delete_party_type'
import delete_person from './delete_person'
import organization_by_id from './organization_by_id'
import organizations from './organizations'
import parties from './parties'
import party_role_type from './party_role_type'
import people from "./people"
import person_by_id from './person_by_id'
import update_organization from './update_organization'
import update_party_role_type from './update_party_role_type'
import update_party_type from './update_party_type'
import update_person from "./update_person"

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
		organization_by_id,
		organizations,
		parties,
		party_role_type,
		people,
		person_by_id
	},
	Mutation: {
		add_party_type_child,
		add_party_role_type_child,
		create_party_role_type,
		create_party_type,
		create_person,
		delete_party_role_type,
		delete_organization,
		delete_party_type,
		delete_person,
		update_organization,
		update_party_role_type,
		update_party_type,
		update_person
	}
}