export default `
type PartyRole {
    id: ID!
    type: PartyRoleType!
    relationshipsInvolvedIn: [PartyRelationship]!
    fromDate: String!
    thruDate: String
 }
`