export default {
	type(parent, args, context, info) {
		console.log('role type: type', parent)
		return context.database.one('select id, description, parent_id from case_role_type where id = ${case_role_type_id}', parent)
	},
	party(parent, args, context, info) {
		console.log('role type: party', parent)
		return context.database.one('select id, comment, party_type_id from party where id = ${party_id}', parent)
	}
}
