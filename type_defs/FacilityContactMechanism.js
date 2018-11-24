export default `
type FacilityContactMechanism {
  id: ID!
  mechanismToContact: Facility!
  specifiedVia: ContactMechanism!
  dateTimeRange: DateTimeRange!
}
`