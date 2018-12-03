export default {
	names(parent, args, context, info) {

		return context.database.any('select id, name, from_date, thru_date, name_type_id from party_name where party_id = ${id}', parent)
			.then(data => {
				console.log('party.names: ', data)
				return data
			})
			.catch(error => console.log('party.name: ', error))
	},
	party_type(parent, args, context, info) {
		return context.database.one('select id, description, parent_id from party_type where id = ${party_type_id}', parent)
	}
}
