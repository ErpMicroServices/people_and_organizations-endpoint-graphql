export default `
interface ContactMechanismPurpose {
    id: ID!
    purposes: [ContactMechanismPurpose]!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`