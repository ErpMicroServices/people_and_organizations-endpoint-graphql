export default `
type CaseRole {
    id: ID!
    type: CaseRoleType!
    of: Party!
    relationshipsInvolvedIn: [PartyRelationship]!
    fromDate: String!
    thruDate: String
 }
`