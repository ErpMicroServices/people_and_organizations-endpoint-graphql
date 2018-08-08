from django.conf.urls import url
from django.urls import path
from graphene_django.views import GraphQLView

from . import schema, views

urlpatterns = [
    url(r'^graphql', GraphQLView.as_view(graphiql=True, schema=schema)),
    path('', views.index, name='index'),
]
