import InputParty from './inputs/InputParty'
import InputPartyName from './inputs/InputPartyName'
import Mutation from './Mutation'
import Query from './Query'
import SchemaDefinition from './SchemaDefinition'
import Case from './types/Case'
import CaseRole from './types/CaseRole'
import CaseRoleType from './types/CaseRoleType'
import CaseStatusType from './types/CaseStatusType'
import CommunicationEvent from './types/CommunicationEvent'
import CommunicationEventPurpose from './types/CommunicationEventPurpose'
import CommunicationEventPurposeType from './types/CommunicationEventPurposeType'
import CommunicationEventRole from './types/CommunicationEventRole'
import CommunicationEventRoleType from './types/CommunicationEventRoleType'
import CommunicationEventStatusType from './types/CommunicationEventStatusType'
import CommunicationEventType from './types/CommunicationEventType'
import CommunicationEventWorkEffort from './types/CommunicationEventWorkEffort'
import ContactMechanism from './types/ContactMechanism'
import ContactMechanismPurposeType from './types/ContactMechanismPurposeType'
import ContactMechanismType from './types/ContactMechanismType'
import DateTimeRange from './types/DateTimeRange'
import EmailAddress from './types/EmailAddress'
import Facility from './types/Facility'
import FacilityContactMechanism from './types/FacilityContactMechanism'
import FacilityRole from './types/FacilityRole'
import FacilityRoleType from './types/FacilityRoleType'
import FacilityType from './types/FacilityType'
import GeographicBoundary from './types/GeographicBoundary'
import GeographicBoundaryType from './types/GeographicBoundaryType'
import IdType from './types/IdType'
import InstantMessaging from './types/InstantMessaging'
import IpAddress from './types/IpAddress'
import NameType from './types/NameType'
import Party from './types/Party'
import PartyClassification from './types/PartyClassification'
import PartyClassificationType from './types/PartyClassificationType'
import PartyContactMechanism from './types/PartyContactMechanism'
import PartyContactMechanismPurpose from './types/PartyContactMechanismPurpose'
import PartyContactMechanismPurposeType from './types/PartyContactMechanismPurposeType'
import PartyId from './types/PartyId'
import PartyName from './types/PartyName'
import PartyPostalAddress from './types/PartyPostalAddress'
import PartyRelationship from './types/PartyRelationship'
import PartyRelationshipStatusType from './types/PartyRelationshipStatusType'
import PartyRelationshipType from './types/PartyRelationshipType'
import PartyRole from './types/PartyRole'
import PartyRoleType from './types/PartyRoleType'
import PartyType from './types/PartyType'
import PostalAddress from './types/PostalAddress'
import PriorityType from './types/PriorityType'
import StatusType from './types/StatusType'
import TelecommunicationsNumber from './types/TelecommunicationsNumber'
import WebAddress from './types/WebAddress'

const scalars = `scalar Date`

export default [
	Case,
	CaseRole,
	CaseRoleType,
	CaseStatusType,
	ContactMechanismType,
	CommunicationEvent,
	CommunicationEventType,
	CommunicationEventPurpose,
	CommunicationEventPurposeType,
	CommunicationEventRole,
	CommunicationEventRoleType,
	CommunicationEventStatusType,
	CommunicationEventWorkEffort,
	ContactMechanism,
	ContactMechanismPurposeType,
	DateTimeRange,
	EmailAddress,
	Facility,
	FacilityContactMechanism,
	FacilityRole,
	FacilityRoleType,
	FacilityType,
	GeographicBoundary,
	GeographicBoundaryType,
	IdType,
	InputParty,
	InputPartyName,
	InstantMessaging,
	IpAddress,
	Mutation,
	NameType,
	Party,
	PartyClassification,
	PartyClassificationType,
	PartyContactMechanism,
	PartyContactMechanismPurpose,
	PartyContactMechanismPurposeType,
	PartyId,
	PartyName,
	PartyPostalAddress,
	PartyRelationship,
	PartyRelationshipStatusType,
	PartyRelationshipType,
	PartyRole,
	PartyRoleType,
	PartyType,
	PostalAddress,
	PriorityType,
	Query,
	scalars,
	SchemaDefinition,
	StatusType,
	TelecommunicationsNumber,
	WebAddress
]
