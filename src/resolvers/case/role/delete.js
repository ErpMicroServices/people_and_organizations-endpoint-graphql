export default async function (obj, args, context, graphql) {
	let deleted_case_role_case = await context.database.one('select "case".id as id, "case".description as description, "case".started_at as started_at, "case".case_type_id as case_type_id, "case".case_status_type_id as case_status_type_id from "case", "case_role" where case_role.id = ${case_role_id} and "case".id = case_role.case_id', args)
	await context.database.none('delete from case_role where id=${case_role_id}', {
		case_role_id: args.case_role_id
	})
	return deleted_case_role_case
}
