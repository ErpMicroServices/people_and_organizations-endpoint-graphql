import {InMemoryCache} from 'apollo-cache-inmemory'
import {ApolloClient} from 'apollo-client'
import {HttpLink} from 'apollo-link-http'
import fetch from 'node-fetch'
import config from './config'
import database from './database'

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

	this.case = {
		id                  : '',
		description         : '',
		started_at          : '',
		case_type_id        : '',
		case_status_type_id : '',
		roles               : [],
		communication_events: []
	}

	this.cases = []

	this.case_role = {
		id       : '',
		case_role:
			{
				id         : '',
				description: ''
			},
		party    : {
			id     : '',
			comment:
				''
		}
	}

	this.case_status_type = {
		id         : '',
		description: ''
	}

	this.case_type = {
		id         : '',
		description: ''
	}

	this.party = {
		comment        : '',
		identifications: [],
		party_type_id  : '',
		names          : []
	}

	this.party_id = {
		id   : '',
		ident: '',
		type : {
			ident: '',
			id   : ''
		}
	}

	this.party_name = {
		id  : '',
		name: '',
		type: {
			description: '',
			id         : ''
		}
	}

	this.party_role_type = {
		id         : '',
		description: ''
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
