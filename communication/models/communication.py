from party.models.party import Party, PartyRelationship
from party.models.types import ContactMechanismType

from .types import *


class CommunicationEventPurpose(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.TextField()
    type = models.ForeignKey(CommunicationEventPurposeType, on_delete=models.PROTECT)


class ValidContactMechanismRole(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    contact_mechanism_type = models.ForeignKey(ContactMechanismType, on_delete=models.PROTECT)
    communication_event_role_type = models.ForeignKey(CommunicationEventRoleType, on_delete=models.PROTECT)


class Case(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.TextField()
    started_at = models.DateTimeField()
    status = models.ForeignKey(CaseStatusType, on_delete=models.PROTECT)


class CaseRole(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    case = models.ForeignKey(Case, on_delete=models.CASCADE)
    party = models.ForeignKey(Party, on_delete=models.PROTECT)


class CommunicationEvent(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    started = models.DateTimeField()
    ended = models.DateTimeField(blank=True, null=True)
    note = models.TextField(blank=True)
    contact_mechanism_type = models.ForeignKey(ContactMechanismType, on_delete=models.PROTECT)
    party_relationship = models.ForeignKey(PartyRelationship, on_delete=models.PROTECT)
    status = models.ForeignKey(CommunicationEventStatusType, on_delete=models.PROTECT)
    case = models.ForeignKey(Case, on_delete=models.CASCADE, null=True)


class CommunicationEventRole(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    communication_event = models.ForeignKey(CommunicationEvent, on_delete=models.CASCADE)
    type = models.ForeignKey(CommunicationEventRoleType, on_delete=models.PROTECT)
    party = models.ForeignKey(Party, on_delete=models.PROTECT)
