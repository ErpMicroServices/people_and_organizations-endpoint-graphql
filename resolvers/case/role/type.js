export default {
	type(parent, args, context, info) {
		return context.database.one('select id, description, parent_id from case_role_type where id = ${case_role_type_id}', parent)
	},
	party(parent, args, context, info) {
		return context.database.one('select id, comment, party_type_id from party where id = ${party_id}', parent)
	}
}
