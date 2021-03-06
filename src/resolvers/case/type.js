export default {
	status (parent, args, context, info) {
		return context.database.one('select id, description from case_status_type where id = ${case_status_type_id}', parent)
	},
	case_type (parent, args, context, info) {
		return context.database.one('select id, description, parent_id from case_type where id = ${case_type_id}', parent)
	},
	roles (parent, args, context, info) {
		return context.database.any('select id, case_role_type_id, party_id, from_date, thru_date from case_role where case_id = ${case_id}', {case_id: parent.id})
	},
	communication_events (parent, args, context, info) {
		return context.database.any('select id, started, ended, note, contact_mechanism_type_id, party_relationship_id, communication_event_status_type_id, communication_event_type_id, case_id from communication_event where id = ${case_id}', {case_id: parent.id})
	}
}
