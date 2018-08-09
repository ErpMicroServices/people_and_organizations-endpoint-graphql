# Register your models here.
from django.contrib import admin

from .models.communication import *

admin.site.register(CommunicationEventPurposeType)
admin.site.register(CommunicationEventRoleType)
admin.site.register(CommunicationEventStatusType)
admin.site.register(CaseStatusType)
admin.site.register(CaseRoleType)
admin.site.register(CommunicationEventType)
admin.site.register(CommunicationEventPurpose)
admin.site.register(ValidContactMechanismRole)
admin.site.register(Case)
admin.site.register(CaseRole)
admin.site.register(CommunicationEvent)
admin.site.register(CommunicationEventRole)
