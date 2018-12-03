export default `
type Party {
    id: ID!,
    comment: String,
    party_type: PartyType!
    names: [PartyName]
    identifications: [PartyId]
 }
`
