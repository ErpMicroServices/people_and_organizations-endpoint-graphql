export default async function (obj, args, context, graphql) {
	let new_party = Object.assign({}, args.new_party)
	if (!new_party.comment) {
		new_party.comment = ''
	}
	let party_id = await context.database.one('insert into party (comment, party_type_id) values(${comment}, ${party_type_id}) returning id', new_party)
	if (new_party.names && new_party.names.length > 0) {
		for (const name of new_party.names) {
			await context.database.none('insert into party_name (name, party_id, name_type_id) values(${name}, ${party_id}, ${name_type_id})', {
				name        : name.name,
				party_id    : party_id.id,
				name_type_id: name.name_type_id
			})
		}
	}
	if (new_party.identifications && new_party.identifications.length > 0) {
		for (const id of new_party.identifications) {
			await context.database.none('insert into party_id (ident, party_id, id_type_id) values(${ident}, ${party_id}, ${id_type_id})', {
				ident     : id.ident,
				party_id  : party_id.id,
				id_type_id: id.id_type_id
			})
		}
	}
	let party = await context.database.one("select id, comment, party_type_id from party where id = ${id}", party_id)
	return party
}
