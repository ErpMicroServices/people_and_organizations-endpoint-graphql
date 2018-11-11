export default function (db_connection, id) {
	console.log("id: ", id)
	return db_connection.one('select id, name from party where id=${id}', {id})
}