export default function (obj, args, context, graphql) {
	return context.database.one('insert into contact_mechanism_type (description) values(${description}) returning id', args)
		.then(data => context.database.one("select id, description from contact_mechanism_type where id = ${id}", data))
}
