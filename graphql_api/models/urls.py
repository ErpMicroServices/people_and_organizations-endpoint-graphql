from django.urls import path
from django.conf.urls import url
from graphene_django.views import GraphQLView

from . import views

urlpatterns = [
    url(r'^graphql', GraphQLView.as_view(graphiql=True)),
    path('', views.index, name='index'),
]
