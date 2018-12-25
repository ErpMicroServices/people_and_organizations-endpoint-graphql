export default `
type CaseRole {
    id: ID!
    type: CaseRoleType!
    party: Party!
    relationshipsInvolvedIn: [PartyRelationship]!
    from_date: Date!
    thru_date: Date
 }
`
