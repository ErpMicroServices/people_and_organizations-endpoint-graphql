export default `
type GeographicBoundary {
    boundaryFor: [PostalAddress]!
    code: String
    contains: [GeographicBoundary]!
    name: String!
    type: GeographicBoundaryType!
    within: [GeographicBoundary]!
    "Reorder guideline is a list of ids from Product domain"
    reorderGuidelines: [ID]!
    "The Sales tax lookup ids from Product domain"
    salesTaxLookup: [ID]!
 }
`