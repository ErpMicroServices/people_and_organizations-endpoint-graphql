export default `
type CaseType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [CaseType]
 }
`
