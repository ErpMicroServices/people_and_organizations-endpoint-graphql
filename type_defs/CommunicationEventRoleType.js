export default `
type CommunicationEventRoleType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [CommunicationEventRoleType]
 }
`
