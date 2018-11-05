import select_person_by_id from '../data_access/select_person_by_id'

export default function (obj, args, context, graphql) {
	return context.database
		.one('update party set first_name=${first_name},last_name=${last_name}, title=${title}, nickname=${nickname}, date_of_birth=${date_of_birth}, comment=${comment} where id=${id} returning id', args)
		.then(id => select_person_by_id(context.database, args.id))
}