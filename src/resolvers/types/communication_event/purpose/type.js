export default {
	children(parent, args, context, info) {
		return context.database.any('select id, description, parent_id from communication_event_purpose_type where parent_id=${id}', parent)
	}
}