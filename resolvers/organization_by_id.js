export default function (obj, args, context, graphql) {
	return context.database.one('select id, name, government_id, comment from party where id=${id}', args)
};
