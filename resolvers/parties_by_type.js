export default function (obj, args, context, graphql) {
	return context.database.any('select party.id as id, party.comment as comment, party.party_type_id as party_type_id from party, party_type where party_type.description = ${description} and party.party_type_id = party_type.id limit ${limit} offset ${offset}',
		{
			limit      : args.records,
			offset     : args.start,
			description: args.party_type
		})
}
