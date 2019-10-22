export default function (obj, args, context, graphql) {
	return context.database.none("delete from id_type where id = ${id}", args)
		.then(() => true)
		.catch(error => {
			console.log("delete_id_type.js error: ", error)
			return false
		})
}
