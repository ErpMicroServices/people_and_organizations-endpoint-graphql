export default async function (obj, args, context, graphql) {
	let case_role    = await context.database.one("insert into case_role (case_id, case_role_type_id, party_id) values (${case_id}, ${input_case_role.case_role_type_id}, ${input_case_role.party_id}) returning id", args)
	let updated_case = await context.database.one('select id, description, started_at, case_type_id, case_status_type_id from "case" where id = ${case_id}', args)
	return updated_case
}
