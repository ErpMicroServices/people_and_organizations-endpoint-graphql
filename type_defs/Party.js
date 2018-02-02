export default `
interface Party {
    id: ID!,
    partyRoles: [PartyRole]!
    casesInvolvedInAs: [CaseRole]!
    classifications: [PartyClassification]!
    contactMechanisms: [PartyContactMechanism]!
    communicationEventsInvolvedInAs: [CommunicationEventRole]!
    facilitiesInvolvedWithAs: [FacilityRole]!  
 }
`