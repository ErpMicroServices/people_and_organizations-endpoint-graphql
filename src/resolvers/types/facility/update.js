export default function (obj, args, context, graphql) {
	return context.database.none("update facility_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description, parent_id from facility_type where id = ${id}", args))
}
