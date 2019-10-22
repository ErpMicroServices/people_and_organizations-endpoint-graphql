export default function (parent, args, context, graphql) {
	return context.database.none("update party_id set ident=${ident} where id=${identity_id}", args)
		.then(() => context.database.one("select id, ident, from_date, thru_date, party_id, id_type_id from party_id where id = ${identity_id}", args))
}
