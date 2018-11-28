export default function (obj, args, context, graphql) {
	return context.database.none("delete from name_type where id = ${id}", args)
		.then(() => true)
		.catch(error => {
			console.log("delete_name_type.js error: ", error)
			return false
		})
}
