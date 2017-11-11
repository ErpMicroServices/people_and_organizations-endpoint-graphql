import database from "../database";

export default function (id) {
	return database.one('select id, first_name, last_name, title, nickname, date_of_birth, comment from party where id=$1', [id]);
}