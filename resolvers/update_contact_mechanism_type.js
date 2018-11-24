export default function (obj, args, context, graphql) {
	return context.database.none("update contact_mechanism_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description from contact_mechanism_type where id = ${id}", args))
}
