export default function (obj, args, context, graphql) {
	return context.database.any('select id, description, started_at, case_type_id, case_status_type_id ' +
		'from "case" ' +
		'limit ${limit} ' +
		'offset ${offset}',
		{
			limit : args.records,
			offset: args.start
		})
}
