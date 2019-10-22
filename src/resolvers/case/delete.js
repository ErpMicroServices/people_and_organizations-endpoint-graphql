export default function (obj, args, context, graphql) {
	return context.database.none('delete from "case" where id = ${id}', args)
		.then(() => true)
		.catch(error => {
			console.log("delete case: ", error)
			return false
		})
}
