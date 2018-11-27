import {InMemoryCache} from 'apollo-cache-inmemory'
import {ApolloClient} from 'apollo-client'
import {HttpLink} from 'apollo-link-http'
import fetch from 'node-fetch'
import config from "./config"
import database from "./database"

var {
	    defineSupportCode
    } = require('cucumber')

function CustomWorld() {
	this.config = config
	this.db     = database
	this.client = new ApolloClient({
		link : new HttpLink({uri: config.url, fetch}),
		cache: new InMemoryCache()
	})


	this.graphql_function = ''

	this.party_role_type = {
		id         : '',
		description: ''
	}

	this.party   = {
		comment      : '',
		party_type_id: ''
	}
	this.parties = []

	this.party_type = {
		id         : '',
		type       : '',
		description: '',
		parent_id  : '',
		children   : []
	}

	this.result = {
		error: null,
		data : null
	}
}

defineSupportCode(function ({
	                            setWorldConstructor
                            }) {
	setWorldConstructor(CustomWorld)
})
