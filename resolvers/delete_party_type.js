export default function (obj, args, context, graphql) {
	return context.database.one("delete from party_type where id = ${id}", args)
		.then(() => true)
		.catch(error => {
			console.log("delete_party_type error: ", error)
			return false
		})
}