export default `
type Case {
    id: ID!
    description: String!
    encompassing: [CommunicationEvent]!
    inTheStateOf: CaseStatusType
    involving: [CaseRole]!
    start: String!   
 }
`