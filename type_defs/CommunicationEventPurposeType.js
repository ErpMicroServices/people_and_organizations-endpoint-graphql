export default `
type CommunicationEventPurposeType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [CommunicationEventPurposeType]
 }
`
