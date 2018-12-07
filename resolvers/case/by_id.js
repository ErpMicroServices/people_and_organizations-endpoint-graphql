export default function (obj, args, context, graphql) {
	return context.database.one('select id, description, started_at, case_type_id, case_status_type_id from "case" where id = ${id}', args)

}
