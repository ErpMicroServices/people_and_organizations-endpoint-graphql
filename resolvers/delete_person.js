import database from "../database";

export default function ({
	                         id
                         }) {
	return database.none("delete from party where id = $1", [id]);
};