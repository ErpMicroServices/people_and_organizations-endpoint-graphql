export default `
type PartyClassificationType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [PartyClassificationType]
 }
`
