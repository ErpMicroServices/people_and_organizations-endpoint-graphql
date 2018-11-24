export default function (obj, args, context, graphql) {
	return context.database.none("update case_role_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description from case_role_type where id = ${id}", args))
}
