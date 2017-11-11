import database from "../database";

export default function (obj, args, context, graphql) {
	return database.any('select id, first_name, last_name, title, nickname, date_of_birth, comment from party where party_type_id=$1', [context.person_type_id()]);
}