export default function (obj, args, context, graphql) {
	return context.database.none("update party_relationship_status_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description, parent_id from party_relationship_status_type where id = ${id}", args))
}

