export default function (parent, args, context, graphql) {
	return context.database.none("update party_name set name=${name} where id=${name_id}", args)
		.then(() => context.database.one("select id, name, from_date, thru_date, party_id, name_type_id from party_name where id = ${name_id}", args))
}
