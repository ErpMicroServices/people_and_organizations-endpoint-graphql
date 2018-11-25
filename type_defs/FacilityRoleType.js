export default `
type FacilityRoleType {
    id: ID!,
    description: String!,
    parent_id: ID,
    children: [FacilityRoleType]
 }
`
