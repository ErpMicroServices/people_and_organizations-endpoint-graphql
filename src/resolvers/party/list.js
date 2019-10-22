export default function (obj, args, context, graphql) {
	return context.database.any('select id, comment, party_type_id ' +
		'from party ' +
		'limit ${limit} ' +
		'offset ${offset}',
		{
			limit : args.records,
			offset: args.start
		})
}
