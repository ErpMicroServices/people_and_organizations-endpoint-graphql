import create_person from './create_person'
import organizations from './organizations'
import parties from './parties'
import people from "./people"

export default {
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
		people
	},
	Mutation: {
		create_person
	}
}