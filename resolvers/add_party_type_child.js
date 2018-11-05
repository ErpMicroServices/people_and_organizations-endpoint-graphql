export default function (obj, args, context, graphql) {
	console.log("args: ", args)
	return context.database.one("insert into party_type (description, parent_id) values (${description}, ${parent_id}) returning id", args)
}