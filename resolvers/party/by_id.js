export default function (obj, args, context, graphql) {
	return context.database.one('select id, comment, party_type_id from party where id = ${id}', args)

}
