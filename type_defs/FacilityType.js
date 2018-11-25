export default `
type FacilityType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [FacilityType]
 }
`
