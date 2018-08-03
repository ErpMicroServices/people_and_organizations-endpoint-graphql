from django.db import models
import uuid

class ClassificationType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)
    def __str__(self):
        return self.description

class PartyType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self', on_delete=models.CASCADE, blank=True, null=True)
    def __str__(self):
        return self.description

class PriorityType(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    description = models.CharField(blank=False, null=False, unique=True, max_length=255)
    parent = models.ForeignKey('self',  blank=True, null=True, on_delete=models.CASCADE)
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
    required_from_role_type = models.ForeignKey(RoleType, on_delete=models.PROTECT, related_name='required_from_role_type')
    required_to_role_type = models.ForeignKey(RoleType, on_delete=models.PROTECT, related_name='required_to_role_type')
    def __str__(self):
        return self.description

class Party(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    government_id = models.CharField(max_length=255, blank=True, null=True)
    first_name = models.CharField(max_length=255, blank=True, null=True)
    last_name = models.CharField(max_length=255, blank=True, null=True)
    name = models.CharField( max_length=255, blank=True, null=True)
    title = models.CharField( max_length=255, blank=True, null=True)
    nickname = models.CharField( max_length=255, blank=True, null=True)
    date_of_birth = models.DateField(blank=True, null=True)
    comment = models.TextField( blank=True, null=True)
    party_type = models.ForeignKey(PartyType, on_delete=models.PROTECT)
    classifications = models.ManyToManyField(ClassificationType, through='PartyClassification')
    roles = models.ManyToManyField(RoleType, through='PartyRole')
    def full_name(self):
        if (self.first_name is not None) or (self.last_name is not None):
            return '%s %s' % (self.last_name, self.first_name)
        else:
            return self.name

class PartyClassification(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    value = models.TextField(blank=False)
    from_date = models.DateTimeField(auto_now_add=True, blank=False, null=False)
    thru_date = models.DateTimeField( blank=True, null=True)
    type = models.ForeignKey(ClassificationType, on_delete=models.PROTECT)
    party = models.ForeignKey(Party, on_delete=models.CASCADE)

class PartyRole(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    value = models.TextField(blank=True, null=True)
    from_date = models.DateTimeField(auto_now_add=True, blank=False, null=False)
    thru_date = models.DateTimeField( blank=True, null=True)
    type = models.ForeignKey(RoleType, on_delete=models.PROTECT)
    party = models.ForeignKey(Party, on_delete=models.CASCADE)
    def __str__(self):
        return "%s:%s" %(self.type.description, self.party.full_name())

class PartyRelationship(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    from_date = models.DateTimeField(auto_now_add=True, blank=False, null=False)
    thru_date = models.DateTimeField( blank=True, null=True)
    comment = models.TextField( blank=True, null=True)
    from_party = models.ForeignKey(PartyRole, on_delete=models.PROTECT, related_name='from_party')
    to_party = models.ForeignKey(PartyRole, on_delete=models.PROTECT, related_name='to_party')
    type = models.ForeignKey(RelationshipType, on_delete=models.PROTECT)
    status = models.ForeignKey(RelationshipStatusType, on_delete= models.PROTECT)
    priority = models.ForeignKey(PriorityType, on_delete = models.PROTECT)
