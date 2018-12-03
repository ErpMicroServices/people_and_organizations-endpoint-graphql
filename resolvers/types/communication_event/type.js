export default {
	children(parent, args, context, info) {
		return context.database.any('select id, description, parent_id from communication_event_type where parent_id=${id}', parent)
	}
}
