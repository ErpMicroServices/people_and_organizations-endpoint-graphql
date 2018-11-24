export default function (obj, args, context, graphql) {
	return context.database.any('select id, description from communication_event_status_type where description=${description}', args)
}
