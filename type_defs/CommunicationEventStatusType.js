export default `
type CommunicationEventStatusType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [CommunicationEventStatusType]
 }
`
