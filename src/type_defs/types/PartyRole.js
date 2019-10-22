export default `
type PartyRole {
    id: ID!
    from_date: Date!
    party: Party!
    relationshipsInvolvedIn: [PartyRelationship]!
    thru_date: Date
    type: PartyRoleType!
 }
`
