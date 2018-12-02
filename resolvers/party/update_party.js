export default function (obj, args, context, graphql) {
	return context.database.one('update party set comment=${comment}, party_type_id = ${party_type_id} where id=${id} returning id', args)
		.then(data => context.database.one("select id, comment, party_type_id from party where id = ${id}", data))
}
