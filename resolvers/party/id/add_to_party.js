export default function (obj, args, context, graphql) {
	return context.database.one("insert into party_id (name, party_id, id_type_id) values (${name}, ${party_id}, ${id_type_id}) returning id", args)
		.then(data => context.database.one("select id, name, from_date, thru_date, party_id, id_type_id from party_id where id = ${id}", data))
}
