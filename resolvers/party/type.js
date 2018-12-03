export default {
	identifications(parent, args, context, info) {
		return context.database.any('select id, ident, from_date, thru_date, id_type_id from party_id where party_id = ${id}', parent)
	},
	names(parent, args, context, info) {
		return context.database.any('select id, name, from_date, thru_date, name_type_id from party_name where party_id = ${id}', parent)
	},
	party_type(parent, args, context, info) {
		return context.database.one('select id, description, parent_id from party_type where id = ${party_type_id}', parent)
	}
}
