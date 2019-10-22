export default `
type PartyRelationshipStatusType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [PartyRelationshipStatusType]
 }
`
