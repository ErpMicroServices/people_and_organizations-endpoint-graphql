import uuid

from django.db import models
from mptt.models import MPTTModel, TreeForeignKey
from party.models.party import ContactMechanism, Party


class FacilityType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class FacilityRoleType(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.description


class Facility(MPTTModel):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.TextField()
    square_footage = models.IntegerField(blank=True, null=True)
    part_of = TreeForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)
    type = models.ForeignKey(FacilityType, on_delete=models.CASCADE)


class FacilityRole(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    type = models.ForeignKey(FacilityRoleType, on_delete=models.PROTECT)
    party = models.ForeignKey(Party, on_delete=models.PROTECT)


class FacilityContactMechanism(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    facility = models.ForeignKey(Facility, on_delete=models.CASCADE)
    contact_mechanism = models.ForeignKey(ContactMechanism, on_delete=models.PROTECT)
