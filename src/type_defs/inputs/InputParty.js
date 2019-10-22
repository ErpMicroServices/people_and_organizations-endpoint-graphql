export default `
input InputParty {
    id: ID,
    comment: String
    identifications: [InputPartyId]
    names: [InputPartyName]
    party_type_id: ID!
    
 }
`
