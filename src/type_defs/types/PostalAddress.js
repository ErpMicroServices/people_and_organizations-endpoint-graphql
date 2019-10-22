export default `
type PostalAddress implements ContactMechanism {
    address: String!
    directions: String!
    locationFor: [PartyPostalAddress]!
    within: [GeographicBoundary]!
    id: ID!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`