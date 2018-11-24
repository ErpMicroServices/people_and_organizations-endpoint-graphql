export default function (obj, args, context, graphql) {
	return context.database.any('select id, description from facility_role_type where description=${description}', args)
};
