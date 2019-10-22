export default function (parent, args, context, graphql) {
	return context.database.one("insert into party_id (ident, party_id, id_type_id) values (${ident}, ${party_id}, ${id_type_id}) returning id", args)
		.then(data => context.database.one("select id, ident, from_date, thru_date, party_id, id_type_id from party_id where id = ${id}", data))
}
