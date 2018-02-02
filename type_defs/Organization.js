export default `
 type Organization implements Party{
    id: ID!
    name: String!
    casesInvolvedInAs: [CaseRole]!
    classifications: [PartyClassification]!
    contactMechanisms: [PartyContactMechanism]!
    communicationEventsInvolvedInAs: [CommunicationEventRole]!
    facilitiesInvolvedWithAs: [FacilityRole]!
    partyRoles: [PartyRole]!
  }
`;