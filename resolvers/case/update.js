export default function (obj, args, context, graphql) {
	return context.database.one('update case set comment=${comment}, case_type_id = ${case_type_id} where id=${id} returning id', args)
		.then(data => context.database.one('select id, comment, case_type_id from "case" where id = ${id}', data))
}
