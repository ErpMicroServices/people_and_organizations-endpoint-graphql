export default function (obj, args, context, graphql) {
	return context.database.none("update name_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description, parent_id from name_type where id = ${id}", args))
}
