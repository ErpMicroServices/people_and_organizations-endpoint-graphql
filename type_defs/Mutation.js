export default `
type Mutation {
	create_person(first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, comment: String, email: String) : Person
}
`
