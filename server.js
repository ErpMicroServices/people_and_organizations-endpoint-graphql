var express = require('express');
import jwt from "jsonwebtoken";
import graphqlHttp from "express-graphql";

import config from "./config";
import db from "./database";
import {
    user_login
} from './validation-schema';
import {} from "./handlers";
import {
    schema,
    root
} from "./graph-schema";

const app = express();

app.use('/', graphqlHttp({
    schema: schema,
    rootValue: root,
    graphiql: true,
}));

app.listen(config.server.port);

console.log('%s listening at %s', config.server.name, config.server.url);
