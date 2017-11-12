import add_party_type_child from "./add_party_type_child";
import create_person from "./create_person";
import delete_party_type from "./delete_person";
import delete_person from "./delete_person";
import organization_by_id from "./organization_by_id";
import people from "./people";
import person_by_id from "./person_by_id";
import update_party_type from "./update_party_type";
import update_person from "./update_person";

export default {
	Mutation: {
		add_party_type_child,
		create_person,
		delete_party_type,
		delete_person,
		update_party_type,
		update_person
	},
	Query   : {
		organization_by_id,
		people,
		person_by_id
	}
};