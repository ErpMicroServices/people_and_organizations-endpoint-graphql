export default function (obj, args, context, graphql) {
	return context.database.none("update communication_event_purpose_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description from communication_event_purpose_type where id = ${id}", args))
}
