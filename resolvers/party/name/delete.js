export default function (parent, args, context, graphql) {
	return context.database.none("delete from party_name where id=${name_id}", args)
		.then(() => true)
		.catch(error => {
			console.log("delete party name: ", error)
			return false
		})
}
