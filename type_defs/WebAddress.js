export default `
type WebAddress implements ContactMechanism {
  "A list of ids from the e-commerce domain"
   hostOf: [ID]!
   url: String!
   id: ID!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`