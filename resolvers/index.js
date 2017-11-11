import create_person from "./create_person";
import delete_person from "./delete_person";
import organization_by_id from "./organization_by_id";
import people from "./people";
import person_by_id from "./person_by_id";
import update_person from "./update_person";

export default {
	Mutation: {
		create_person,
		delete_person,
		update_person
	},
	Query   : {
		organization_by_id,
		people,
		person_by_id
	}
};