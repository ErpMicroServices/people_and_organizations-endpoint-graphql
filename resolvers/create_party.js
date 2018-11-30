export default async function (obj, args, context, graphql) {
	console.log('create_party: ', args)
	let party_id = await context.database.one('insert into party (comment, party_type_id) values(${comment}, ${party_type_id}) returning id', args.new_party)
	console.log("create_party party_id: ", party_id)
	if (args.new_party.names && args.new_party.names.length > 0) {
		for (const name of args.new_party.names) {
			console.log("create_party: name: ", name)
			await context.database.none('insert into party_name (name, party_id, name_type_id) values(${name}, ${party_id}, ${name_type_id})', {
				name        : name.name,
				party_id    : party_id.id,
				name_type_id: name.name_type_id
			})
		}
	}
	let party = await context.database.one("select id, comment, party_type_id from party where id = ${id}", party_id)
	console.log("create_party - party: ", party)
	return party
}
