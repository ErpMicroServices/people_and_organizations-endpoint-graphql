export default `
type EmailAddress implements ContactMechanism {
    email: String!
    id: ID!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`