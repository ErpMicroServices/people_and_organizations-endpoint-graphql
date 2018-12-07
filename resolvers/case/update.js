export default function (parent, args, context, graphql) {
	let update = Object.assign({}, args.update_case, {id: args.id})
	return context.database.one('update "case" set description=${description} where id=${id} returning id', update)
		.then(data => context.database.one('select id, description, started_at, case_type_id, case_status_type_id from "case" where id = ${id}', data))
}
