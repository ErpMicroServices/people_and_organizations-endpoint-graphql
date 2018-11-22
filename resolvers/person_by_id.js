import select_person_by_id from '../data_access/select_person_by_id'

export default function (obj, args, context, graphql) {
	return select_person_by_id(context.database, args.id)
}