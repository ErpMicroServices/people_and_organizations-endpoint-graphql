export default `
type NameType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [NameType]
 }
`
