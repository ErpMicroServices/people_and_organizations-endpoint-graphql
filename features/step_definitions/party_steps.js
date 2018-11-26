import gql from 'graphql-tag'

const PQ = require('pg-promise').ParameterizedQuery

var {
	    defineSupportCode
    } = require('cucumber')

function convert_to_table_name(type) {
	return type.replace(/\s+/g, '_').toLowerCase()
}

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {
	Given('there are {int} parties with a type of {string} in the database', function (number_of_parties, party_type) {
		let db = this.db

		return this.db.one('insert into party_type (description) values (${party_type}) returning id', {party_type})
			.then(data => this.party_type = {id: data.id, description: party_type})
			.then(() => {
				let sql_statements = []
				for (let i = 0; i < number_of_parties; i++) {
					sql_statements.push(db.one('insert into party (comment, party_type_id) values ( ${comment}, ${party_type_id} ) returning id', {
						comment      : `party number ${i}`,
						party_type_id: this.party_type.id
					}))
				}
				return Promise.all(sql_statements)
			})
			.then(data => {
				let sql_statements = []
				for (let i = 0; i < data.length; i++) {
					sql_statements.push(db.one('select id, comment, party_type_id from party where id = ${id}', data[i]))
				}
				return Promise.all(sql_statements)
			})
			.then(data => this.parties = this.parties.concat(data))
			.catch(error => {
				console.log("Couldn't create parties: ", error)
			})
	})

	Given('a comment of {string}', function (comment, callback) {
		this.party.comment = comment
		callback()
	})

	Given('a party with a comment of {string} is in the database', function (comment) {
		this.party.comment = comment
		return this.db.one('insert into party (comment, party_type_id) values(${comment}, ${party_type_id}) returning id', {
				comment      : comment,
				party_type_id: this.party_type.id
			})
			.then(data => this.party.id = data.id)
	})

	When('I save the party', function () {
		this.graphql_function = 'create_party'
		return this.client
		.mutate({
			mutation : gql`mutation create_party($comment: String, $party_type_id: ID!) {create_party(comment: $comment, party_type_id: $party_type_id){id comment party_type{id}}}`,
			variables: {
				"comment"      : this.party.comment,
				"party_type_id": this.party_type.id
			}
		})
		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	When('I update the party', function () {
		this.graphql_function = 'update_party'
		return this.client
		.mutate({
			mutation : gql`mutation update_party($id: ID!, $comment: String, $party_type_id: ID!) {update_party(id: $id, comment: $comment, party_type_id: $party_type_id){id comment party_type{id}}}`,
			variables: {
				"id"           : this.party.id,
				"comment"      : this.party.comment,
				"party_type_id": this.party_type.id
			}
		})
		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	When('I search for all parties', function () {
		this.graphql_function = 'parties'
		return this.client
		.query({
			query    : gql`query parties($start: Int!, $records: Int!) {parties(start: $start, records: $records){id comment party_type{id}}}`,
			variables: {
				"start"  : 0,
				"records": this.parties.length + 10
			}
		})

		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	When('I search for parties of type {string}', function (party_type) {
		this.graphql_function = 'parties_by_type'
		return this.client
		.query({
			query    : gql`query parties_by_type($party_type: String!, $start: Int!, $records: Int!) {parties_by_type(party_type: $party_type, start: $start, records: $records){id comment party_type{id description}}}`,
			variables: {
				"start"     : 0,
				"records"   : this.parties.length + 10,
				"party_type": party_type
			}
		})

		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	Given('I change the comment to {string}', function (new_comment, callback) {
		this.party.comment = new_comment
		callback()
	})

	When('I search for the party by id', function () {
		this.graphql_function = 'party'
		return this.client
		.query({
			query    : gql`query party($id: ID!) { party(id: $id){id comment party_type{id description}}}`,
			variables: {
				"id": this.party.id
			}
		})
		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	Then('I get {int} parties', function (number_of_parties, callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data[`${this.graphql_function}`].length).to.be.equal(number_of_parties)
		callback()
	})

	Then('{int} of them are type {string}', function (count, type) {
		return this.db.one('select id, description, parent_id from party_type where description = ${type}', {type})
			.then(data => expect(this.result.data.data[`${this.graphql_function}`].filter(p => p.party_type.id === data.id).length).to.be.equal(count))
	})

	Then('I get the party back', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data[`${this.graphql_function}`].id).to.not.be.null
		expect(this.result.data.data[`${this.graphql_function}`].comment).to.be.equal(this.party.comment)
		expect(this.result.data.data[`${this.graphql_function}`].party_type.id).to.be.equal(this.party_type.id)
		callback()
	})

	Then('the party is in the database', function () {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		return this.db.one('select id, comment, party_type_id from party where id = ${id}', this.result.data.data[`${this.graphql_function}`])
			.then(data => {
				expect(data.comment).to.be.equal(this.party.comment)
				expect(data.party_type_id).to.be.equal(this.party_type.id)
			})
	})

	Then('there is {int} party in the database', function (party_count) {
		return this.db.one('select count(id) from party')
			.then(data => {
				expect(parseInt(data.count)).to.be.equal(party_count)
			})
	})
})
