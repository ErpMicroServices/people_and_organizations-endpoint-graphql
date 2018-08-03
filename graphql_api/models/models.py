from django.db import models
import uuid

# Create your models here.

class PartyType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE)

class Party(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    government_id = models.CharField(max_length=255)
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    name = models.CharField( max_length=255)
    title = models.CharField( max_length=255)
    nickname = models.CharField( max_length=255)
    date_of_birth = models.DateField()
    comment = models.TextField()
    party_type = models.ForeignKey(PartyType, on_delete=models.PROTECT)

class ClassificationType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE)

class PartyClassification(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    value = models.TextField(blank=False)
    from_date = models.DateTimeField(auto_now_add=True, blank=False, null=False)
    thru_date = models.DateTimeField( blank=True, null=True)
    type = models.ForeignKey(ClassificationType, on_delete=models.PROTECT)

class RoleType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE)

class PartyRole(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    value = models.TextField(blank=False)
    from_date = models.DateTimeField(auto_now_add=True, blank=False, null=False)
    thru_date = models.DateTimeField( blank=True, null=True)
    type = models.ForeignKey(RoleType, on_delete=models.PROTECT)
    party = models.ForeignKey(Party, on_delete=models.CASCADE)

class RelationshipType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE)
    from_role_type = models.ForeignKey(RoleType, on_delete=models.PROTECT, related_name='from_role_type')
    to_role_type = models.ForeignKey(RoleType, on_delete=models.PROTECT, related_name='to_role_type')

class PriorityType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE)

class RelationshipStatusType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE)

class PartyRelationship(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    from_date = models.DateTimeField(auto_now_add=True, blank=False, null=False)
    thru_date = models.DateTimeField( blank=True, null=True)
    comment = models.TextField()
    from_party = models.ForeignKey(Party, on_delete=models.PROTECT, related_name='from_party')
    to_party = models.ForeignKey(Party, on_delete=models.PROTECT, related_name='to_party')
    type = models.ForeignKey(RelationshipType, on_delete=models.PROTECT)
    status = models.ForeignKey(RelationshipStatusType, on_delete= models.PROTECT)
    priority = models.ForeignKey(PriorityType, on_delete = models.PROTECT)
