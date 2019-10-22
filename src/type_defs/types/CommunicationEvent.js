export default `
type CommunicationEvent {
    id: ID!,
    partOf: [Case]!
    categorizedBy: [CommunicationEventPurpose]!
    dateTimeEnded: String
    dateTimeStarted: String!
    inTheContextOf: PartyRelationship!
    involving: [CommunicationEventRole]!
    monitoredBy: CommunicationEventStatusType!
    note: String
    occursVia: PartyContactMechanism!
 }
`