let contact_mechanism_types = []
let party_types             = []

database.any("select id, description, parent_id from party_type order by description")
	.then((data) => party_types = data)
	.then(() => database.any("select id, description from contact_mechanism_type order by description"))
	.then((data) => contact_mechanism_types = data)

const email_id       = () => contact_mechanism_types.find((cm) => cm.description === 'Email Address').id
const party_type_id  = (party_type) => party_types.find(pt => pt.description === party_type).id
const person_type_id = () => party_type_id("Person")

export email_id,
party_type_id, person_type_id
