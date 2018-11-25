import {makeExecutableSchema} from 'graphql-tools'
import {GraphQLServer} from 'graphql-yoga'
import config from "./config"

import database from "./database"
import resolvers from "./resolvers"
import typeDefs from "./type_defs"

const schema = makeExecutableSchema({typeDefs, resolvers})

const server = new GraphQLServer({
	schema : schema,
	context: {
		database
	}
})

console.log("Starting server with config: ", config.server)
server.start(config.server)
	.then(data => console.log('%s is running at url: %s on port %d and the following end points: api: %s, subscriptions: %s, playground: %s, data: %s', config.server.name, config.server.url, config.server.port, config.server.endpoint, config.server.subscriptions, config.server.playground, data))
	.catch(error => console.log('Couldn\'t start server: ', error))



