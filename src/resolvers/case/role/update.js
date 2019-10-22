export default async function (obj, args, context, graphql) {
	await context.database.none('update case_role set case_role_type_id = ${case_role_type_id}, party_id = ${party_id} where id=${case_role_id}', {
		case_role_type_id: args.input_case_role.case_role_type_id,
		party_id         : args.input_case_role.party_id,
		case_role_id     : args.case_role_id
	})
	let updated_case = await context.database.one('select "case".id as id, "case".description as description, "case".started_at as started_at, "case".case_type_id as case_type_id, "case".case_status_type_id as case_status_type_id from "case", "case_role" where case_role.id = ${case_role_id} and "case".id = case_role.case_id', args)
	return updated_case
}
