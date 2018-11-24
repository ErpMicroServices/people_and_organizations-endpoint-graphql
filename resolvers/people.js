import database from "../database"

export default function (obj, args, context, graphql) {
	return database.any('select id, first_name, last_name, title, nickname, date_of_birth, comment ' +
		'from party ' +
		'where party_type_id=${party_type_id} ' +
		'order by last_name, first_name ' +
		'limit ${limit} ' +
		'offset ${offset}',
		{
			party_type_id: context.person_type_id(),
			limit        : args.records,
			offset       : args.start
		})
}
