export default `
input InputParty {
    id: ID,
    comment: String
    party_type_id: ID!
    names: [InputPartyName]
 }
`
