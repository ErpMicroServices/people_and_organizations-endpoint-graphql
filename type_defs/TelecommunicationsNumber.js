export default `
type TelecommunicationsNumber implements ContactMechanism {
    acceptsData: Boolean!
    acceptsFaxes: Boolean!
    acceptsTextMessages: Boolean!
    countryCode: String!
    areaCode: String!
    contactNumber: String!
    extension: String!
 }
`