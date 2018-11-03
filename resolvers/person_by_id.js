import database from "../database";

export default function (obj, args, context, graphql) {
	return database.one('select id, first_name, last_name, title, nickname, date_of_birth, comment from party where id=${id}', args);
}