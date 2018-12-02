export default `
type CaseStatusType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [CaseStatusType]
 }
`
