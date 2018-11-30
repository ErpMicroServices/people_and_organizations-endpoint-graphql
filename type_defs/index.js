import Case from './Case'
import CaseRole from './CaseRole'
import CaseRoleType from './CaseRoleType'
import CaseStatusType from './CaseStatusType'
import CommunicationEvent from './CommunicationEvent'
import CommunicationEventPurpose from './CommunicationEventPurpose'
import CommunicationEventPurposeType from './CommunicationEventPurposeType'
import CommunicationEventRole from './CommunicationEventRole'
import CommunicationEventRoleType from './CommunicationEventRoleType'
import CommunicationEventStatusType from './CommunicationEventStatusType'
import CommunicationEventType from './CommunicationEventType'
import CommunicationEventWorkEffort from './CommunicationEventWorkEffort'
import ContactMechanism from './ContactMechanism'
import ContactMechanismPurposeType from './ContactMechanismPurposeType'
import ContactMechanismType from './ContactMechanismType'
import DateTimeRange from './DateTimeRange'
import EmailAddress from './EmailAddress'
import Facility from './Facility'
import FacilityContactMechanism from './FacilityContactMechanism'
import FacilityRole from './FacilityRole'
import FacilityRoleType from './FacilityRoleType'
import FacilityType from './FacilityType'
import GeographicBoundary from './GeographicBoundary'
import GeographicBoundaryType from './GeographicBoundaryType'
import InputParty from './InputParty'
import InputPartyName from './InputPartyName'
import InstantMessaging from './InstantMessaging'
import IpAddress from './IpAddress'
import Mutation from './Mutation'
import NameType from './NameType'
import Party from './Party'
import PartyClassification from './PartyClassification'
import PartyClassificationType from './PartyClassificationType'
import PartyContactMechanism from './PartyContactMechanism'
import PartyContactMechanismPurpose from './PartyContactMechanismPurpose'
import PartyContactMechanismPurposeType from './PartyContactMechanismPurposeType'
import PartyName from './PartyName'
import PartyPostalAddress from './PartyPostalAddress'
import PartyRelationship from './PartyRelationship'
import PartyRelationshipStatusType from './PartyRelationshipStatusType'
import PartyRelationshipType from './PartyRelationshipType'
import PartyRole from './PartyRole'
import PartyRoleType from './PartyRoleType'
import PartyType from './PartyType'
import PostalAddress from './PostalAddress'
import PriorityType from './PriorityType'
import Query from './Query'
import SchemaDefinition from './SchemaDefinition'
import StatusType from './StatusType'
import TelecommunicationsNumber from './TelecommunicationsNumber'
import WebAddress from './WebAddress'

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
