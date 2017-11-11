import database from "../database";

export default function ({
	                         first_name,
	                         last_name,
	                         title,
	                         nickname,
	                         date_of_birth,
	                         comment,
	                         email
                         }) {
	return database
			.one("insert into party (first_name, last_name, title, nickname, date_of_birth, comment, party_type_id) values($1, $2, $3, $4, $5, $6, $7) returning id",
					[first_name, last_name, title, nickname, date_of_birth || null, comment, person_type_id()])
			.then((party_id) => {
				if (email) {
					return database
							.one("INSERT INTO contact_mechanism(end_point, contact_mechanism_type_id) VALUES ( $1, $2) returning id", [email, email_id()])
							.then((data) => database.one("INSERT INTO party_contact_mechanism(party_id, contact_mechanism_id)VALUES ( $1, $2) returning party_id as id", [party_id.id, data.id]));
				} else {
					return party_id;
				}
			});
}