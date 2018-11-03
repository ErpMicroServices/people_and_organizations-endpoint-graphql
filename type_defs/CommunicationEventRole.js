export default `
type CommunicationEventRole {
    id: ID!
    type: CommunicationEventRoleType!
    describedBy: CommunicationEventRoleType!
    forParty: Party!
    of: CommunicationEvent!
 }
`