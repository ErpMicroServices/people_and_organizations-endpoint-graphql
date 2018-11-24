export default function (obj, args, context, graphql) {
	return context.database.none("update geographic_boundary_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description from geographic_boundary_type where id = ${id}", args))
}
