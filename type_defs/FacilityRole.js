export default `
type FacilityRole {
    id: ID!
    type: FacilityRoleType!
    of: Facility!
    dateTimeRange: DateTimeRange!
 }
`