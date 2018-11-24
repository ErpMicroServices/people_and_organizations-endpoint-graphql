export default function (obj, args, context, graphql) {
	return context.database.any('select id, description from contact_mechanism_type where description=${description}', args)
}
