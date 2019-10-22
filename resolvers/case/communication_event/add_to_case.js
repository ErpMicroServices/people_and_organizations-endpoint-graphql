export default async function (obj, args, context, graphql) {
	const updated_case       =await context.database.one('select id, description, started_at, case_type_id, case_status_type_id from "case" where id = ${case_id}', args)
	const added              =args.communication_event
	added.case_id            =updated_case.id
	const communication_event=await context.database.one("insert into communication_event (started, ended, note, contact_mechanism_type_id, party_relationship_id, communication_event_status_type_id, communication_event_type_id, case_id) values (${started}, ${ended}, ${note}, ${contact_mechanism_type_id}, ${party_relationship_id}, ${communication_event_status_type_id}, ${communication_event_type_id}, ${case_id})returning id, started, ended, note, contact_mechanism_type_id, party_relationship_id, communication_event_type_id,communication_event_status_type_id, case_id", added)
	return updated_case
}
