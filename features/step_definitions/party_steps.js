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

	When('I search for all parties', function () {
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

	Then('I get {int} parties', function (number_of_parties, callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.parties.length).to.be.equal(number_of_parties)
		callback()
	})

	Then('{int} of them are type {string}', function (count, type) {
		return this.db.one('select id, description, parent_id from party_type where description = ${type}', {type})
			.then(data => expect(this.result.data.data.parties.filter(p => p.party_type.id === data.id).length).to.be.equal(count))
	})
})
