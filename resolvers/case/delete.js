export default function (obj, args, context, graphql) {
	return context.database.none('delete from "case" where id = ${id}', args)
		.then(() => true)
		.catch(error => {
			console.log("delete_party_classification_type: ", error)
			return false
		})
}
