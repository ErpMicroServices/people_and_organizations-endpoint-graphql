import database from "../database";

export default function ({
	                         id,
	                         first_name,
	                         last_name,
	                         title,
	                         nickname,
	                         date_of_birth,
	                         comment
                         }) {
	return database
			.one("update party set first_name=$1, last_name=$2, title=$3, nickname=$4, date_of_birth=$5, comment=$6 where id=$7 returning id, first_name, last_name, title, nickname, date_of_birth, comment", [first_name, last_name, title, nickname, date_of_birth, comment, id]);
}