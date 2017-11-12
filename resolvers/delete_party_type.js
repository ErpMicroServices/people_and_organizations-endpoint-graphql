export default function (obj, args, context, graphql) {
	return context.database.one("delete from party_type where id = ${id}", args)
			.then(() => "success")
			.catch((error) => "failure");
}