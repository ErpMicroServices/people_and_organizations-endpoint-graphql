export default `
type PartyRole {
    id: ID!
    type: PartyRoleType!
    relationshipsInvolvedIn: [PartyRelationship]!
    from_date: Date!
    thru_date: Date
 }
`
