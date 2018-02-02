export default `
interface ContactMechanism {
    id: ID!
    usedByFacilities: [FacilityContactMechanism]!
    usedByParties: [PartyContactMechanism]!
 }
`;