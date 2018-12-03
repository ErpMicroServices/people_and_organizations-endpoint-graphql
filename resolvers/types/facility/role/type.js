export default {
	children(parent, args, context, info) {
		return context.database.any('select id, description, parent_id from facility_role_type where parent_id=${id}', parent)
	}
}
