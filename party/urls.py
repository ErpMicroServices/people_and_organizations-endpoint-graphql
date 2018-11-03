from django.conf.urls import url
from django.urls import path
from graphene_django.views import GraphQLView

from .schema import schema
from .views import index

urlpatterns = [
    url(r'^graphql', GraphQLView.as_view(graphiql=True, schema=schema)),
    path('', index, name='index'),
]
