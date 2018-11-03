export default `
 type PartyContactMechanism {
  id: ID!
  comment: String
  solicitable: Boolean!
  specifiedFor: PartyRole
  contactMechanism: ContactMechanism!
  purposes: [PartyContactMechanismPurpose]!
  fromDate: String!
  thruDate: String
  }
`