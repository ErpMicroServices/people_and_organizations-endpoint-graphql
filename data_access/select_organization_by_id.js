export default function (db_connection, id) {
	return db_connection.one('select id, name from party where id=${id}', {id})
}