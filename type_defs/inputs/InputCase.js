export default `
input InputCase {
	description: String,
	started_at: String,
	case_type_id: ID!,
	case_status_type_id: ID!
	roles: [InputCaseRole]
}
`
