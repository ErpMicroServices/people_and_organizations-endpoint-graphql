from django.contrib import admin

from .models import *

admin.site.register(FacilityType)
admin.site.register(FacilityRoleType)
admin.site.register(Facility)
admin.site.register(FacilityRole)
admin.site.register(FacilityContactMechanism)
