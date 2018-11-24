export default function (obj, args, context, graphql) {
	return context.database.any('select id, description from geographic_boundary_type where description=${description}', args)
}
