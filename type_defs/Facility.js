export default `
type Facility {
  id: ID!
  contactedVia: [FacilityContactMechanism]!
  description: String!
  involving: [FacilityRole]!
  madeUpOf: [Facility]
  partOf: Facility
 }
`