export default {
	id_type(parent, args, context, info) {
		return context.database.one('select id, description, parent_id from id_type where id=${name_type_id} order by description', parent)
	}
}
