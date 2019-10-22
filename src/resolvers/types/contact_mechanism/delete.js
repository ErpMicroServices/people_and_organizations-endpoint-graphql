export default function (obj, args, context, graphql) {
	return context.database.none("delete from contact_mechanism_type where id = ${id}", args)
		.then(() => true)
		.catch(error => {
			console.log("delete_party_role_type error: ", error)
			return false
		})
}
