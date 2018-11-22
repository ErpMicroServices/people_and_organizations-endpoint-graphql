export default function (obj, args, context, graphql) {
	let person = args
	person.party_type_id = context.party_types.get('Person').id
	person.date_of_birth = person.date_of_birth || null
	return context.database
		.one('insert into party (first_name, last_name, title, nickname, date_of_birth, comment, party_type_id) values(${first_name}, ${last_name}, ${title}, ${nickname}, ${date_of_birth}, ${comment}, ${party_type_id}) returning id', person)
		.then((party_id) => {
			if (person.email) {
				return context.database
					.one('INSERT INTO contact_mechanism(end_point, contact_mechanism_type_id) VALUES ( $1, $2) returning id', [person.email, context.contact_mechanism_types.get('Email Address').id])
					.then((data) => context.database.one('INSERT INTO party_contact_mechanism(party_id, contact_mechanism_id)VALUES ( $1, $2) returning party_id as id', [party_id.id, data.id]))
			} else {
				return party_id
			}
		})
}
