import {graphiqlExpress, graphqlExpress} from 'apollo-server-express';
import bodyParser from 'body-parser';
import express from 'express';
import {makeExecutableSchema} from 'graphql-tools';

import config from "./config";
import resolvers from "./resolvers";

import typeDefs from "./type_defs";

const schema = makeExecutableSchema({typeDefs, resolvers});

const app = express();

app.use(bodyParser.json());

app.use('/graphql', graphqlExpress({
	schema,
	context: {
	}
}));

if (config.graphql.graphiql) {
	app.use("/graphiql", graphiqlExpress({endpointURL: config.graphql.endpointURL}));
}
app.listen(config.server.port, () => console.log('%s listening at %s', config.server.name, config.server.url));

