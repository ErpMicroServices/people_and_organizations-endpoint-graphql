import {GraphQLDate} from 'graphql-iso-date'
import add_party_type_child from './add_party_type_child'
import create_person from './create_person'
import delete_party_type from './delete_party_type'
import delete_person from './delete_person'
import organizations from './organizations'
import parties from './parties'
import people from "./people"
import person_by_id from './person_by_id'
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
		organizations,
		parties,
		people,
		person_by_id
	},
	Mutation: {
		add_party_type_child,
		create_person,
		delete_party_type,
		delete_person,
		update_party_type,
		update_person
	}
}