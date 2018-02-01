export default `
interface ContactMechanism {
    id: ID!
    purposes: [ContactMechanismPurpose]!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`;