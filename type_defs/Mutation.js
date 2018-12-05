export default `
type Mutation {
	case_status_type_add_child( description: String, parent_id: ID!): CaseStatusType
	case_role_type_add_child( description: String, parent_id: ID!): CaseRoleType
	communication_event_purpose_type_add_child( description: String, parent_id: ID!): CommunicationEventPurposeType
	communication_event_role_type_add_child( description: String, parent_id: ID!): CommunicationEventRoleType
	communication_event_status_type_add_child( description: String, parent_id: ID!): CommunicationEventStatusType
	communication_event_type_add_child( description: String, parent_id: ID!): CommunicationEventType
	contact_mechanism_type_add_child( description: String, parent_id: ID!): ContactMechanismType
	facility_role_type_add_child( description: String, parent_id: ID!): FacilityRoleType
	facility_type_add_child( description: String, parent_id: ID!): FacilityType
	party_id_add_to_party(party_id: ID!, ident:String!, id_type_id: ID!) : PartyId
	id_type_add_child( description: String, parent_id: ID!): IdType
	geographic_boundary_type_add_child( description: String, parent_id: ID!): GeographicBoundaryType
	party_name_add_to_party(party_id: ID!, name:String!, name_type_id: ID!) : PartyName
	name_type_add_child( description: String, parent_id: ID!): NameType
	party_classification_type_add_child( description: String, parent_id: ID!): PartyClassificationType
	party_relationship_status_type_add_child( description: String!, parent_id: ID!): PartyRelationshipStatusType
	party_relationship_type_add_child( description: String!, parent_id: ID!): PartyRelationshipType
	party_role_type_add_child( description: String!, parent_id: ID!): PartyRoleType
	party_type_add_child( description: String, parent_id: ID!): PartyType
	priority_type_add_child( description: String, parent_id: ID!): PriorityType
	case_role_type_create( description: String!): CaseRoleType
	case_status_type_create( description: String!): CaseStatusType
	communication_event_purpose_type_create( description: String!): CommunicationEventPurposeType
	communication_event_role_type_create( description: String!): CommunicationEventRoleType
	communication_event_status_type_create( description: String!): CommunicationEventStatusType
	communication_event_type_create( description: String!): CommunicationEventType
	contact_mechanism_type_create( description: String!): ContactMechanismType
	facility_role_type_create( description: String!): FacilityRoleType
	facility_type_create( description: String!): FacilityType
	geographic_boundary_type_create( description: String!): GeographicBoundaryType
	id_type_create( description: String!): IdType
	name_type_create( description: String!): NameType
	party_create( new_party: InputParty!): Party!
	party_classification_type_create( description: String!): PartyClassificationType
	party_relationship_status_type_create( description: String!): PartyRelationshipStatusType
	party_relationship_type_create( description: String!): PartyRelationshipType
	party_role_type_create( description: String!): PartyRoleType
	party_type_create( description: String!): PartyType
	priority_type_create( description: String!): PriorityType
	case_role_type_delete(id: ID!): Boolean
	case_status_type_delete(id: ID!): Boolean
	communication_event_type_delete(id: ID!): Boolean
	communication_event_purpose_type_delete(id: ID!): Boolean
	communication_event_role_type_delete(id: ID!): Boolean
	communication_event_status_type_delete(id: ID!): Boolean
	contact_mechanism_type_delete(id: ID!): Boolean
	facility_role_type_delete(id: ID!): Boolean
	facility_type_delete(id: ID!): Boolean
	geographic_boundary_type_delete(id: ID!): Boolean
	id_type_delete(id: ID!): Boolean
	name_type_delete(id: ID!): Boolean
	party_delete(id: ID!): Boolean
	party_classification_type_delete(id: ID!): Boolean
	party_id_delete(identity_id: ID!): Boolean
	party_name_delete(name_id: ID!): Boolean
	party_relationship_status_type_delete(id: ID!): Boolean
	priority_type_delete(id: ID!): Boolean
	party_relationship_type_delete(id: ID!): Boolean
	party_role_type_delete( id: ID!): Boolean
	party_type_delete( id: ID!): Boolean
	case_status_type_update(id: ID!, description: String!): CaseStatusType
	case_role_type_update(id: ID!, description: String!): CaseRoleType
	communication_event_purpose_type_update(id: ID!, description: String!): CommunicationEventType
	communication_event_type_update(id: ID!, description: String!): CommunicationEventType
	communication_event_role_type_update(id: ID!, description: String!): CommunicationEventRoleType
	communication_event_status_type_update(id: ID!, description: String!): CommunicationEventStatusType
	contact_mechanism_type_update(id: ID!, description: String!): ContactMechanismType
	facility_type_update(id: ID!, description: String!): FacilityType
	facility_role_type_update(id: ID!, description: String!): FacilityRoleType
	geographic_boundary_type_update(id: ID!, description: String!): GeographicBoundaryType
	id_type_update(id: ID!, description: String!): IdType
	name_type_update(id: ID!, description: String!): NameType
	party_update(id: ID!, comment: String, party_type_id:ID!): Party
	party_classification_type_update(id: ID!, description: String!): PartyClassificationType
	party_id_update(identity_id: ID!, ident: String!): PartyId
	party_name_update(name_id: ID!, name: String!): PartyName
	party_relationship_status_type_update(id: ID!, description: String!): PartyRelationshipStatusType
	party_relationship_type_update(id: ID!, description: String!): PartyRelationshipType
	party_role_type_update(id: ID!, description: String!): PartyRoleType
	party_role_update(id: ID!, from_date: Date, thru_date: Date): PartyRole
	party_type_update(id: ID!, description: String!): PartyType
	priority_type_update(id: ID!, description: String!): PriorityType
}
`

