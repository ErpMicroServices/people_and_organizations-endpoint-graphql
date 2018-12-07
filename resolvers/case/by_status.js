export default async function (parent, args, context, graphql) {
	let case_status_types = await context.database.any('select id, description, parent_id from case_status_type where description=${description}', {description: args.case_status})
	let cases             = await context.database.any('select "case".id as id, ' +
		'"case".description as description, ' +
		'"case".started_at as started_at, ' +
		'"case".case_type_id as case_type_id, ' +
		'"case".case_status_type_id as case_status_type_id ' +
		'from "case", case_status_type ' +
		'where case_status_type.description = ${description} ' +
		'and "case".case_status_type_id = case_status_type.id ' +
		'limit ${limit} ' +
		'offset ${offset}',
		{
			limit      : args.records,
			offset     : args.start,
			description: args.case_status
		})
	return cases

}
