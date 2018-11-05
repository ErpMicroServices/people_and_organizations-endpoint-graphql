export default function (db_connection, id) {
	return db_connection.one('select id, first_name, last_name, title, nickname, date_of_birth, comment from party where id=${id}', {id})
		.then(person => {
			console.log("Person: ", person)
			return person
		})
}