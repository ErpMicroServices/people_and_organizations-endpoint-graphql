export default `
type InstantMessaging implements ContactMechanism {
    protocol: String!
    service: String!
    username: String!
    id: ID!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`