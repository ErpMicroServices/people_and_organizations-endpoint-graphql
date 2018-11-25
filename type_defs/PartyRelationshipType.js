export default `
type PartyRelationshipType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [PartyRelationshipType]
 }
`
