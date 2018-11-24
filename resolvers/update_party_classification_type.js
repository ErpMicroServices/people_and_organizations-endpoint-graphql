export default function (obj, args, context, graphql) {
	return context.database.none("update party_classification_type set description = ${description} where id = ${id}", args)
		.then(() => context.database.one("select id, description from party_classification_type where id = ${id}", args))
}

