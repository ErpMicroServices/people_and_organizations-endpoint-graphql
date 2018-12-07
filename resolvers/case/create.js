export default async function (party, args, context, graphql) {
	let new_case = Object.assign({}, args.new_case)
	if (!new_case.description) {
		new_case.description = ''
	}
	let case_id      = await context.database.one('insert into "case" (description, case_type_id, case_status_type_id) values(${description}, ${case_type_id}, ${case_status_type_id}) returning id', new_case)
	let created_case = await context.database.one('select id, description, started_at, case_type_id, case_status_type_id from "case" where id = ${id}', case_id)
	return created_case
}
