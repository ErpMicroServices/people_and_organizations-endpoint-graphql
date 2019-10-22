export default function (obj, args, context, graphql) {
	return context.database.one('insert into communication_event_type (description) values(${description}) returning id', args)
		.then(data => context.database.one("select id, description from communication_event_type where id = ${id}", data))
}
