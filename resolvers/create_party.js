export default function (obj, args, context, graphql) {
	return context.database.one('insert into party (comment, party_type_id) values(${comment}, ${party_type_id}) returning id', args)
		.then(data => context.database.one("select id, comment, party_type_id from party where id = ${id}", data))
}
