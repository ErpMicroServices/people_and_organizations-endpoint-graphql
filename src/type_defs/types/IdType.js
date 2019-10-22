export default `
type IdType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [IdType]
 }
`
