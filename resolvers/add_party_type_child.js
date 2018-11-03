export default function (obj, args, context, graphql) {
	return context.database
			.one("insert into party_type (description, parent_id) values (${description}, ${parent_id}) returning id", args)
			.then(data => data.id);
}