export default `
 type PartyContactMechanism {
  id: ID!
  comment: String
  solicitable: Boolean!
  specifiedFor: PartyRole
  contactMechanism: ContactMechanism!
  purposes: [PartyContactMechanismPurpose]!
  from_date: Date!
  thru_date: Date
  }
`
