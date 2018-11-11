export default function (obj, args, context, graphql) {
	return context.database.one('select id, description, parent_id from party_role_type where description=${description}', args)
};
