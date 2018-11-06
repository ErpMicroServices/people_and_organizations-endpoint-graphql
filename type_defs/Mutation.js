export default `
type Mutation {
	add_party_type_child( description: String, parent_id: ID!): PartyType
	create_person(first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, 
								comment: String, email: String) : Person
	create_party_type( description: String!): PartyType
	delete_party_type( id: ID!): Boolean
	delete_person(id: ID!) :Boolean
	update_person(id:ID!, first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, 
								comment: String, email: String) :Person
	update_party_type(id: ID!, description: String!): PartyType
}
`
