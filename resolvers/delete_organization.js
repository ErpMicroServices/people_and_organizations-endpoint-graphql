export default function (obj, args, context, graphql) {
	return context.database.none("delete from party where id = ${id}", args)
};