export default function (obj, args, context, graphql) {
	return context.database.any('select id, description, parent_id from party_classification_type where description=${description}', args)
}
