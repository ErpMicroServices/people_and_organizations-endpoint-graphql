export default function (obj, args, context, graphql) {
	return context.database.one("insert into party_name (name, party_id, name_type_id) values (${name}, ${party_id}, ${name_type_id}) returning id", args)
		.then(data => context.database.one("select id, name, from_date, thru_date, party_id, name_type_id from party_name where id = ${id}", data))
}
