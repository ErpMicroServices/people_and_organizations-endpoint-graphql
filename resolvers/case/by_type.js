export default function (obj, args, context, graphql) {
	return context.database.any('select "case".id as id, "case".description as description, "case".started_at as started_at, "case".case_type_id as case_type_id,"case".case_status_type_id as case_status_type_id from "case", case_type where case_type.description = ${description} and "case".case_type_id = case_type.id limit ${limit} offset ${offset}',
		{
			limit      : args.records,
			offset     : args.start,
			description: args.case_type
		})
}
