import {graphiqlExpress, graphqlExpress} from 'apollo-server-express';
import bodyParser from 'body-parser';
import express from 'express';
import {makeExecutableSchema} from 'graphql-tools';
import config from "./config";

import database from "./database";
import resolvers from "./resolvers";
import typeDefs from "./type_defs";

const schema = makeExecutableSchema({typeDefs, resolvers});

const app = express();

app.use(bodyParser.json());

const party_types             = new Map();
const contact_mechanism_types = new Map();
const person_type_id          = () => party_types.get('Person').id;

database.task(t => t.any("select id, description from contact_mechanism_type order by description")
		.then(data => data.forEach(cmt => contact_mechanism_types.set(cmt.description, cmt)))
		.then(() => t.any("select id, description, parent_id from party_type order by description"))
		.then(data => data.forEach(party_type => party_types.set(party_type.description, party_type)))
).then(() => {
			app.use('/graphql', graphqlExpress({
				schema,
				context: {
					contact_mechanism_types,
					database,
					party_types,
					person_type_id
				}
			}));
		})
		.catch(error => console.log("Error: ", error));


if (config.graphql.graphiql) {
	app.use("/graphiql", graphiqlExpress({endpointURL: config.graphql.endpointURL}));
}
app.listen(config.server.port, () => console.log('%s listening at %s', config.server.name, config.server.url));

