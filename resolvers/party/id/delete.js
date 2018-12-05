export default function (parent, args, context, graphql) {
	return context.database.none("delete from party_id where id=${identity_id}", args)
		.then(() => true)
		.catch(error => {
			console.log("delete party_id: ", error)
			return false
		})

}
