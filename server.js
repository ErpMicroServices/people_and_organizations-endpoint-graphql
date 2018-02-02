import {makeExecutableSchema} from 'graphql-tools'
import {GraphQLServer} from 'graphql-yoga'
import config from "./config"

import database from "./database"
import resolvers from "./resolvers"
import typeDefs from "./type_defs"

const schema = makeExecutableSchema({typeDefs, resolvers})

const party_types = new Map()
const contact_mechanism_types = new Map()
const person_type_id = () => party_types.get('Person').id
const organization_type_id = () => party_types.get('Organization').id


database.task(t => t.any("select id, description from contact_mechanism_type order by description")
.then(data => data.forEach(cmt => contact_mechanism_types.set(cmt.description, cmt)))
.then(() => t.any("select id, description, parent_id from party_type order by description"))
.then(data => data.forEach(party_type => party_types.set(party_type.description, party_type))))
.then(() => {
  const server = new GraphQLServer({
                                     schema: schema,
                                     context: {
                                       contact_mechanism_types,
                                       database,
                                       organization_type_id,
                                       party_types,
                                       person_type_id
                                     }
                                   })
  const options = {
    cors: config.server.cors,
    tracing: config.server.tracing,
    port: config.server.port,
    endpoint: config.server.endpoint,
    subscriptions: config.server.subscriptions,
    playground: config.server.playground,
    uploads: config.server.uploads
  }
  return server.start(options)
})
.then(() => console.log('%s is running at url: %s on port %d and the following end points: api: %s, subscriptions: %s, playground: %s', config.server.name, config.server.url, config.server.port, config.server.endpoint, config.server.subscriptions, config.server.playground))
.catch(error => console.log("Error: ", error))
