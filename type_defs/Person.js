export default `
type Person implements Party {
    first_name: String
    last_name: String
    title: String
    nickname: String
    date_of_birth: Date
    comment: String
    id: ID!
    partyRoles: [PartyRole]!
    casesInvolvedInAs: [CaseRole]!
    classifications: [PartyClassification]!
    contactMechanisms: [PartyContactMechanism]!
    communicationEventsInvolvedInAs: [CommunicationEventRole]!
    facilitiesInvolvedWithAs: [FacilityRole]! 
 }
`