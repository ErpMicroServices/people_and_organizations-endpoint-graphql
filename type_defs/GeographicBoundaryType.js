export default `
type GeographicBoundaryType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [GeographicBoundaryType]
 }
`
