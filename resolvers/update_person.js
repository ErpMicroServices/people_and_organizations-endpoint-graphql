import database from "../database";

export default function (obj, args, context, graphql) {
	return database
			.one("update party set first_name=${first_name}, last_name=${last_name}, title=${title}, nickname=${nickname}, date_of_birth=${date_of_birth}, comment=${comment} where id=${id} returning id, first_name, last_name, title, nickname, date_of_birth, comment", args);
}