export default `
type IpAddress implements ContactMechanism {
    address: String!
    ipv4: Boolean!
    ipv6: Boolean!
    id: ID!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`