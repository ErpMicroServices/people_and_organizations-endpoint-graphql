export default `
type Case {
    id: ID!
    description: String!
    communication_events: [CommunicationEvent]!
    status: CaseStatusType
    roles: [CaseRole]!
    started_at: String!   
    case_type: CaseType
 }
`
