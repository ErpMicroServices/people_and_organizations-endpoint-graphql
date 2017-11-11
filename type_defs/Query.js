export default `
type Query {
	people : [Person]
	person_by_id(id: ID!) : Person
	organization_by_id(id: ID!) : Organization
}
`;