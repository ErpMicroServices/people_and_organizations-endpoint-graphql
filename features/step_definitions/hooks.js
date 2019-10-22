// features/step_definitions/hooks.js


import {Before} from 'cucumber'

Before(async function (result) {
	await this.db.any('delete from party_contact_mechanism')
	await this.db.any('delete from case_role')
	await this.db.any('delete from communication_event')
	await this.db.any('delete from "case"')
	await this.db.any('delete from case_type')
	await this.db.any('delete from case_role_type')
	await this.db.any('delete from case_status_type')
	await this.db.any('delete from communication_event_type')
	await this.db.any('delete from communication_event_purpose_type')
	await this.db.any('delete from communication_event_role_type')
	await this.db.any('delete from communication_event_status_type')
	await this.db.any('delete from contact_mechanism')
	await this.db.any('delete from contact_mechanism_type')
	await this.db.any('delete from facility_role_type')
	await this.db.any('delete from facility_type')
	await this.db.any('delete from geographic_boundary_association')
	await this.db.any('delete from geographic_boundary')
	await this.db.any('delete from geographic_boundary_type')
	await this.db.any('delete from party_classification_type')
	await this.db.any('delete from party_relationship')
	await this.db.any('delete from party_relationship_status_type')
	await this.db.any('delete from party_relationship_type')
	await this.db.any('delete from party_role')
	await this.db.any('delete from party_role_type')
	await this.db.any('delete from priority_type')
	await this.db.any('delete from party_name')
	await this.db.any('delete from party_id')
	await this.db.any('delete from name_type')
	await this.db.any('delete from party')
	await this.db.any('delete from id_type')
	await this.db.any('delete from party_type')
	this.contact_mechanism_types= await this.db.any('select id, description from contact_mechanism_type order by description')
	this.party_types            = await this.db.any('select id, description from party_type order by description')

})

