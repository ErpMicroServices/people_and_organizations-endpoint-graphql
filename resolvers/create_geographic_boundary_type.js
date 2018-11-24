export default function (obj, args, context, graphql) {
	return context.database.one('insert into geographic_boundary_type (description) values(${description}) returning id', args)
		.then(data => context.database.one("select id, description from geographic_boundary_type where id = ${id}", data))
}
