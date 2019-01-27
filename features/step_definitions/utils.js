/**
 * Created by JimBarrows on 2019-01-26.
 */


export const create_parties_of_type = async function (db, party_type_description, number_of_parties) {
	let party_type = await db.one('select id, description from party_type where description = ${party_type_description}', {party_type_description})
	let parties    = []
	for (let i = 0; i < number_of_parties; i++) {
		let party_id = await db.one('insert into party (comment, party_type_id) values ( ${comment}, ${party_type_id} ) returning id', {
			comment      : `party number ${i}`,
			party_type_id: party_type.id
		})
		let party    = await db.one('select id, comment, party_type_id from party where id = ${id}', party_id)
		parties.push(party)
	}
	return parties
}


