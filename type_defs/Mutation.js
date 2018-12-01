export default `
type Mutation {
	add_case_status_type_child( description: String, parent_id: ID!): CaseStatusType
	add_case_role_type_child( description: String, parent_id: ID!): CaseRoleType
	add_communication_event_purpose_type_child( description: String, parent_id: ID!): CommunicationEventPurposeType
	add_communication_event_role_type_child( description: String, parent_id: ID!): CommunicationEventRoleType
	add_communication_event_status_type_child( description: String, parent_id: ID!): CommunicationEventStatusType
	add_communication_event_type_child( description: String, parent_id: ID!): CommunicationEventType
	add_contact_mechanism_type_child( description: String, parent_id: ID!): ContactMechanismType
	add_facility_role_type_child( description: String, parent_id: ID!): FacilityRoleType
	add_facility_type_child( description: String, parent_id: ID!): FacilityType
	add_geographic_boundary_type_child( description: String, parent_id: ID!): GeographicBoundaryType
	add_name_to_party(party_id: ID!, name:String!, name_type_id: ID!) : PartyName
	add_name_type_child( description: String, parent_id: ID!): NameType
	add_party_classification_type_child( description: String, parent_id: ID!): PartyClassificationType
	add_party_relationship_status_type_child( description: String!, parent_id: ID!): PartyRelationshipStatusType
	add_party_relationship_type_child( description: String!, parent_id: ID!): PartyRelationshipType
	add_party_role_type_child( description: String!, parent_id: ID!): PartyRoleType
	add_party_type_child( description: String, parent_id: ID!): PartyType
	add_priority_type_child( description: String, parent_id: ID!): PriorityType
	create_case_role_type( description: String!): CaseRoleType
	create_case_status_type( description: String!): CaseStatusType
	create_communication_event_purpose_type( description: String!): CommunicationEventPurposeType
	create_communication_event_role_type( description: String!): CommunicationEventRoleType
	create_communication_event_status_type( description: String!): CommunicationEventStatusType
	create_communication_event_type( description: String!): CommunicationEventType
	create_contact_mechanism_type( description: String!): ContactMechanismType
	create_facility_role_type( description: String!): FacilityRoleType
	create_facility_type( description: String!): FacilityType
	create_geographic_boundary_type( description: String!): GeographicBoundaryType
	create_name_type( description: String!): NameType
	create_party( new_party: InputParty!): Party!
	create_party_classification_type( description: String!): PartyClassificationType
	create_party_relationship_status_type( description: String!): PartyRelationshipStatusType
	create_party_relationship_type( description: String!): PartyRelationshipType
	create_party_role_type( description: String!): PartyRoleType
	create_party_type( description: String!): PartyType
	create_priority_type( description: String!): PriorityType
	delete_case_role_type(id: ID!): Boolean
	delete_case_status_type(id: ID!): Boolean
	delete_communication_event_type(id: ID!): Boolean
	delete_communication_event_purpose_type(id: ID!): Boolean
	delete_communication_event_role_type(id: ID!): Boolean
	delete_communication_event_status_type(id: ID!): Boolean
	delete_contact_mechanism_type(id: ID!): Boolean
	delete_facility_role_type(id: ID!): Boolean
	delete_facility_type(id: ID!): Boolean
	delete_geographic_boundary_type(id: ID!): Boolean
	delete_name_type(id: ID!): Boolean
	delete_party(id: ID!): Boolean
	delete_party_classification_type(id: ID!): Boolean
	delete_party_relationship_status_type(id: ID!): Boolean
	delete_priority_type(id: ID!): Boolean
	delete_party_relationship_type(id: ID!): Boolean
	delete_party_role_type( id: ID!): Boolean
	delete_party_type( id: ID!): Boolean
	update_case_status_type(id: ID!, description: String!): CaseStatusType
	update_case_role_type(id: ID!, description: String!): CaseRoleType
	update_communication_event_purpose_type(id: ID!, description: String!): CommunicationEventType
	update_communication_event_type(id: ID!, description: String!): CommunicationEventType
	update_communication_event_role_type(id: ID!, description: String!): CommunicationEventRoleType
	update_communication_event_status_type(id: ID!, description: String!): CommunicationEventStatusType
	update_contact_mechanism_type(id: ID!, description: String!): ContactMechanismType
	update_facility_type(id: ID!, description: String!): FacilityType
	update_facility_role_type(id: ID!, description: String!): FacilityRoleType
	update_geographic_boundary_type(id: ID!, description: String!): GeographicBoundaryType
	update_name_type(id: ID!, description: String!): NameType
	update_party(id: ID!, comment: String, party_type_id:ID!): Party
	update_party_classification_type(id: ID!, description: String!): PartyClassificationType
	update_party_relationship_status_type(id: ID!, description: String!): PartyRelationshipStatusType
	update_party_relationship_type(id: ID!, description: String!): PartyRelationshipType
	update_party_role_type(id: ID!, description: String!): PartyRoleType
	update_party_role(id: ID!, from_date: Date, thru_date: Date): PartyRole
	update_party_type(id: ID!, description: String!): PartyType
	update_priority_type(id: ID!, description: String!): PriorityType
}
`

