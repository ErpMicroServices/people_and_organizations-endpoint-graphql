export default {
	name_type(parent, args, context, info) {
		return context.database.one('select id, description, parent_id from name_type where id=${name_type_id} order by description', parent)
	}
}
