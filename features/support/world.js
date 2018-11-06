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

	this.party = {
		party_type: ''
	}

	this.party_role_type = {
		id         : '',
		description: ''
	}
	this.person          = {
		id           : '',
		first_name   : '',
		last_name    : '',
		title        : '',
		nickname     : '',
		date_of_birth: '',
		comment      : '',
		email_address: ''
	}

	this.party_type = {
		id         : '',
		description: '',
		parent_id  : ''
	}

	this.contact_mechanism_types = []
	this.party_types             = []

	this.email_id = () => this.contact_mechanism_types.find((cm) => cm.description === 'Email Address').id

	this.party_type_id = (party_type) => this.party_types.find(pt => pt.description === party_type).id

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
