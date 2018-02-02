export default `
 type Organization implements Party{
    name: String!
    id: ID!
    partyRoles: [PartyRole]!
    casesInvolvedInAs: [CaseRole]!
    classifications: [PartyClassification]!
    contactMechanisms: [PartyContactMechanism]!
    communicationEventsInvolvedInAs: [CommunicationEventRole]!
    facilitiesInvolvedWithAs: [FacilityRole]! 
  }
`;