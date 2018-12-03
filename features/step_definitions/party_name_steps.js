import 'babel-polyfill'
import gql from 'graphql-tag'

var {
	    defineSupportCode
    } = require('cucumber')

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {

	Given('party names of', async function (dataTable) {
		let table = dataTable.rawTable
		for (let row_index in dataTable.rawTable[0]) {
			let row  = table[row_index]
			let sql  = 'select id, description from name_type where description = ${description} '
			let data = await this.db.one(sql, {description: row[0]})
			this.party.names.push({name: row[1], name_type_id: data.id})
		}
	})

	When('I add the {string} of {string} to the party', async function (name_type, name) {
		try {
			let name_type_id                 = await this.db.one('select id from name_type where description = ${name_type}', {name_type})
			this.party_name.name             = name
			this.party_name.type.id          = name_type_id.id
			this.party_name.type.description = name_type
			let response                     = await this.client
				.mutate({
					mutation : gql`mutation add_name_to_party($party_id: ID!, $name:String!, $name_type_id: ID!) 
				{ add_name_to_party(party_id: $party_id, name: $name, name_type_id: $name_type_id) { id name name_type {id description}}}`,
					variables: {
						party_id    : this.party.id,
						name,
						name_type_id: name_type_id.id
					}
				})
			this.result.data                 = response
		} catch (error) {
			this.result.error = error
		}
	})

	Then('I get the party name back', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		let actual_name = this.result.data.data.add_name_to_party
		expect(actual_name.id).to.be.ok
		expect(actual_name.name).to.be.equal(this.party_name.name)
		expect(actual_name.name_type.description).to.be.equal(this.party_name.type.description)
		expect(actual_name.name_type.id).to.be.equal(this.party_name.type.id)
		callback()
	})

	Then('the party name is present', function (callback) {
		expect(this.result.data.data[`${this.graphql_function}`].names.length).to.be.equal(this.party.names.length)
		callback()
	})

	Then('the party name is in the database', async function () {
		let actual_name = this.result.data.data.add_name_to_party
		let party_name  = await this.db.one('select id, name, from_date, thru_date, party_id, name_type_id from party_name where id = ${id}', actual_name)
		expect(party_name).to.not.be.null
		expect(party_name.name).to.be.equal(this.party_name.name)
		expect(party_name.party_id).to.be.equal(this.party.id)
		expect(party_name.name_type_id).to.be.equal(this.party_name.type.id)
	})

	Then('the party name type is present', function (callback) {
		expect(this.result.data.data[`${this.graphql_function}`].names.length).to.be.equal(this.party.names.length)
		callback()
	})

	Then('the party names are present', function (callback) {
		this.party.names.forEach(p => {
			let found = this.result.data.data[`${this.graphql_function}`].names.find(n => p.name === n.name)
			expect(found).to.be.ok
			expect(found.name_type.id).to.be.equal(p.name_type_id)
			expect(found.id).to.be.ok
		})
		callback()
	})
})
