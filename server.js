import restify from 'restify';
import config from "./config";
import db from "./database";
import {user_login} from './schema';
import jwt from "jsonwebtoken";
import {} from "./handlers";

var server = restify.createServer();

server.name = config.server.name;
server.version = config.server.version;

server.use(restify.requestLogger());
server.use(restify.acceptParser(server.acceptable));
server.use(restify.queryParser());
server.use(restify.bodyParser());

server.get('/', function(req, res){res.send(200, "hello");});

server.listen(config.server.port, function() {
    console.log('%s listening at %s', config.server.name, config.server.url);
});
