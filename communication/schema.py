import graphene


class Mutation(graphene.ObjectType):
    pass


class Query(graphene.ObjectType):
    pass


chema = graphene.Schema(mutation=Mutation, query=Query, types=[])
