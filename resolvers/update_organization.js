import select_organization_by_id from '../data_access/select_organization_by_id'

export default function (obj, args, context, graphql) {
	return context.database
		.one('update party set name=${name},government_id=${government_id}, comment=${comment} where id=${id} returning id', args)
		.then(id => select_organization_by_id(context.database, args.id))
}