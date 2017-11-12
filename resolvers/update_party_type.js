export default function (obj, args, context, graphql) {
	return context.database.none("update party_type set description = ${description} where id = ${id}", args)
			.then(() => context.database.one("select id, description, parrent_id from party_type where id = ${id}", args))
			.then((data) => data);
}