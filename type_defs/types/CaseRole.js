export default `
type CaseRole {
    id: ID!
    type: CaseRoleType!
    party: Party!
    relationshipsInvolvedIn: [PartyRelationship]!
    fromDate: String!
    thruDate: String
 }
`
