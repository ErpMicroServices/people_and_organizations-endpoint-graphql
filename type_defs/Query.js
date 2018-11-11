export default `
type Query {
	
	organizations( start: Int!, records: Int!) : [Organization]!
	parties( start: Int!, records: Int!) : [Party]!
	party_role_type( description: String!): PartyRoleType!
	people(start: Int!, records: Int!) : [Person]!
	person_by_id(id: ID!) : Person
}
`