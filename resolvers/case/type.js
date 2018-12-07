export default {
	status(parent, args, context, info) {
		return context.database.one('select id, description from case_status_type where id = ${case_status_type_id}', parent)
	},
	case_type(parent, args, context, info) {
		return context.database.one('select id, description, parent_id from case_type where id = ${case_type_id}', parent)
	}
}
