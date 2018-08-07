import uuid

from django.db import models
from mptt.models import MPTTModel, TreeForeignKey


class ClassificationType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class ContactMechanismType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class PartyType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class PriorityType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', blank=True, null=True, on_delete=models.CASCADE)

    def __str__(self):
        return self.description


class RelationshipStatusType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', blank=True, null=True, on_delete=models.CASCADE)

    def __str__(self):
        return self.description


class RoleType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', null=True, blank=True, on_delete=models.CASCADE)

    def __str__(self):
        return self.description


class RelationshipType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE, null=True, blank=True)
    required_from_role_type = models.ForeignKey(RoleType, on_delete=models.PROTECT,
                                                related_name='required_from_role_type')
    required_to_role_type = models.ForeignKey(RoleType, on_delete=models.PROTECT, related_name='required_to_role_type')

    def __str__(self):
        return self.description
