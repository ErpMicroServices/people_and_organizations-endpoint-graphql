import 'babel-polyfill'
import gql from 'graphql-tag'

const PQ = require('pg-promise').ParameterizedQuery

var {
	    defineSupportCode
    } = require('cucumber')

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {
	Given('there are {int} parties with a type of {string} in the database', async function (number_of_parties, party_type) {
		let db = this.db

		let party_type_id = await this.db.one('insert into party_type (description) values (${party_type}) returning id', {party_type})
		this.party_type   = {id: party_type_id.id, description: party_type}
		for (let i = 0; i < number_of_parties; i++) {
			let party_id = await db.one('insert into party (comment, party_type_id) values ( ${comment}, ${party_type_id} ) returning id', {
				comment      : `party number ${i}`,
				party_type_id: this.party_type.id
			})
			let party    = await db.one('select id, comment, party_type_id from party where id = ${id}', party_id)
			this.parties.push(party)
		}
	})


	Given('a comment of {string}', function (comment, callback) {
		this.party.comment = comment
		callback()
	})

	Given('no comment field', function (callback) {
		delete this.party.comment
		callback()
	})

	Given('a party with a comment of {string} is in the database', async function (comment) {
		this.party.comment = comment
		let party_id       = await this.db.one('insert into party (comment, party_type_id) values(${comment}, ${party_type_id}) returning id', {
			comment      : comment,
			party_type_id: this.party_type.id
		})
		this.party.id      = party_id.id
	})

	Given('a party of type {string} is in the database', async function (party_type) {
		let party_type_id  = await this.db.one('select id from party_type where description = ${party_type}', {party_type})
		let party_id       = await this.db.one('insert into party (party_type_id) values( ${party_type_id}) returning id', {
			party_type_id: party_type_id.id
		})
		this.party.id      = party_id.id
		this.party_type.id = party_type_id.id
	})

	When('I save the party', async function () {
		try {
			this.graphql_function = 'party_create'
			let inputParty        = {
				"inputParty": {
					'comment'      : this.party.comment,
					'party_type_id': this.party_type.id
				}
			}
			if (this.party.names && this.party.names.length > 0) {
				inputParty.inputParty.names = this.party.names
			}
			if (this.party.identifications && this.party.identifications.length > 0) {
				inputParty.inputParty.identifications = this.party.identifications
			}
			let response     = await this.client.mutate({
				mutation : gql`mutation party_create($inputParty: InputParty!) { party_create(new_party: $inputParty) { id comment identifications { id ident id_type { id description}} names { id name name_type { id description}} party_type { id description} }}`,
				variables: inputParty
			})
			this.result.data = response
		} catch (error) { this.result.error = error}
	})

	When('I update the party', async function () {
		try {
			this.graphql_function = 'party_update'
			let response          = await this.client.mutate({
				mutation : gql`mutation party_update($id: ID!, $comment: String, $party_type_id: ID!) {party_update(id: $id, comment: $comment, party_type_id: $party_type_id){id comment party_type{id}}}`,
				variables: {
					'id'           : this.party.id,
					'comment'      : this.party.comment,
					'party_type_id': this.party_type.id
				}
			})
			this.result.data      = response
		} catch (error) { this.result.error = error}
	})

	When('I search for all parties', async function () {
		try {
			this.graphql_function = 'parties'
			let response          = await this.client.query({
				query    : gql`query parties($start: Int!, $records: Int!) {parties(start: $start, records: $records){id comment party_type{id}}}`,
				variables: {
					'start'  : 0,
					'records': this.parties.length + 10
				}
			})
			this.result.data      = response
		} catch (error) {this.result.error = error}
	})

	When('I search for parties of type {string}', async function (party_type) {
		try {
			this.graphql_function = 'parties_by_type'
			let response          = await this.client.query({
				query    : gql`query parties_by_type($party_type: String!, $start: Int!, $records: Int!) {parties_by_type(party_type: $party_type, start: $start, records: $records){id comment party_type{id description}}}`,
				variables: {
					'start'     : 0,
					'records'   : this.parties.length + 10,
					'party_type': party_type
				}
			})

			this.result.data = response
		} catch (error) { this.result.error = error}
	})

	Given('I change the comment to {string}', function (new_comment, callback) {
		this.party.comment = new_comment
		callback()
	})

	When('I search for the party by id', async function () {
		try {
			this.graphql_function = 'party'
			let response          = await this.client.query({
				query    : gql`query party($id: ID!) { party(id: $id){id comment party_type{id description}}}`,
				variables: {
					'id': this.party.id
				}
			})
			this.result.data      = response
		} catch (error) { this.result.error = error}
	})

	When('I delete the party', async function () {
		try {
			this.graphql_function = 'party_delete'
			let response          = await this.client.mutate({
				mutation : gql`mutation party_delete($id: ID!) {party_delete(id: $id)}`,
				variables: {
					'id': this.party.id
				}
			})
			this.result.data      = response
		} catch (error) {this.result.error = error}
	})

	Then('I get {int} parties', function (number_of_parties, callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data[`${this.graphql_function}`].length).to.be.equal(number_of_parties)
		callback()
	})

	Then('{int} of them are type {string}', async function (count, type) {
		let party_type = await this.db.one('select id, description, parent_id from party_type where description = ${type}', {type})
		expect(this.result.data.data[`${this.graphql_function}`].filter(p => p.party_type.id === party_type.id).length).to.be.equal(count)
	})

	Then('I get the party back', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data[`${this.graphql_function}`].id).to.not.be.null
		if (this.party.comment) {
			expect(this.result.data.data[`${this.graphql_function}`].comment).to.be.equal(this.party.comment)
		} else {
			expect(this.result.data.data[`${this.graphql_function}`].comment).to.be.equal('')
		}
		expect(this.result.data.data[`${this.graphql_function}`].party_type.id).to.be.equal(this.party_type.id)
		callback()
	})

	Then('the party is in the database', async function () {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		let party_id = ''
		switch (this.graphql_function) {
			case 'party_id_update':
			case 'party_id_delete':
			case 'party_name_update':
			case 'party_name_delete':
				party_id = this.party.id
				break
			default:
				party_id = this.result.data.data[`${this.graphql_function}`].id
		}

		let party = await this.db.one('select id, comment, party_type_id from party where id = ${party_id}', {party_id})
		if (this.party.comment) {
			expect(party.comment).to.be.equal(this.party.comment)
		} else {
			expect(party.comment).to.not.be.ok
		}

		expect(party.party_type_id).to.be.equal(this.party_type.id)

	})

	Then('there is {int} party in the database', async function (party_count) {
		let actual_count = await this.db.one('select count(id) from party')
		expect(parseInt(actual_count.count)).to.be.equal(party_count)
	})

	Then('I get {string} back', function (string, callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data[`${this.graphql_function}`]).to.be.true
		callback()
	})

	Then('the party is not in the database', async function () {
		try {
			expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
			expect(this.result.data).to.not.be.null
			let party = await this.db.one('select id, comment, party_type_id from party where id = ${id}', this.party)

			fail('Party should be deleted: ', party)
		} catch (error) {
			expect(error.message).to.be.equal('No data returned from the query.')
		}
	})
})
