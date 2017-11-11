export default `
type Mutation {
	add_party_type_child(description: String!, parent_id: ID!):ID!
	create_person(first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, comment: String, email: String): Person
  update_person(id: ID!,first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, comment: String): Person
  delete_person(id: ID!): String
}
`;
