export default function (obj, args, context, graphql) {
	return context.database.any('select id, description, parent_id from priority_type where description=${description}', args)
}
