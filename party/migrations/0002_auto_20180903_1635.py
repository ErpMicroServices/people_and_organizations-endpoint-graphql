# Generated by Django 2.1 on 2018-09-03 16:35

from django.db import migrations


class Migration(migrations.Migration):
	dependencies = [
		('party', '0001_initial'),
	]

	operations = [
		migrations.RemoveField(
			model_name='geographicboundary',
			name='level',
		),
		migrations.RemoveField(
			model_name='geographicboundary',
			name='lft',
		),
		migrations.RemoveField(
			model_name='geographicboundary',
			name='rght',
		),
		migrations.RemoveField(
			model_name='geographicboundary',
			name='tree_id',
		),
	]
