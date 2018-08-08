import uuid

from django.db import models
from mptt.models import MPTTModel, TreeForeignKey


class CommunicationEventPurposeType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class CommunicationEventRoleType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class CommunicationEventStatusType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class CaseStatusType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class CaseRoleType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class CommunicationEventType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description
