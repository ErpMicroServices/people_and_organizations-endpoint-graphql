import database from "../database"

export default function (obj, args, context, graphql) {
  return database.any('select id, government_id, first_name, last_name, name, title, nickname, date_of_birth, comment, party_type_id ' +
    'from party ' +
    'order by last_name, first_name, name ' +
    'limit ${limit} ' +
    'offset ${offset}',
    {
      party_type_id: context.organization_type_id(),
      limit: args.records,
      offset: args.start
    })
}