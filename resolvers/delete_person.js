import database from "../database";

export default function (obj, args, context, graphql) {
	return database.none("delete from party where id = ${id}", args);
};