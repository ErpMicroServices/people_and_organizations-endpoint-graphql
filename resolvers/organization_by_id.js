import database from "../database";

export default function (id) {
	return database.one('select id, name from party where id=$1', [id]);
};
