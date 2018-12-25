export default `
  type PartyPostalAddress {
    id: ID!
    comment: String
    locatedAt: PostalAddress
    from_date: Date!
    thru_date: Date
  }
  `
