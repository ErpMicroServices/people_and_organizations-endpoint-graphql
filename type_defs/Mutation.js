export default `
type Mutation {
	add_party_type_child( description: String, parent_id: ID!): PartyType
	add_party_role_type_child( description: String!, parent_id: ID!): PartyRoleType
	create_organization(name: String!, government_id: String, comment: String): Organization
	create_party_role_type( description: String!): PartyRoleType
	create_party_type( description: String!): PartyType
	create_person(first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, 
								comment: String, email: String) : Person
	delete_organization(id: ID!): Boolean
	delete_party_role_type( id: ID!): Boolean
	delete_party_type( id: ID!): Boolean
	delete_person(id: ID!) :Boolean
	update_organization(id: ID!, name: String, government_id: String, comment: String): Organization
	update_person(id:ID!, first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, 
								comment: String, email: String) :Person
	update_party_role_type(id: ID!, description: String!): PartyRoleType
	update_party_type(id: ID!, description: String!): PartyType
}
`

