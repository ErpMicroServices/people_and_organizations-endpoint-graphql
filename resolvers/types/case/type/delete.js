export default function (obj, args, context, graphql) {
	return context.database.none("delete from case_type where id = ${id}", args)
		.then(() => true)
		.catch(error => {
			console.log("case_type error: ", error)
			return false
		})
}
