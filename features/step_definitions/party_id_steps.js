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
	Given('party ids of', async function (dataTable) {
		let table = dataTable.rawTable
		for (let row_index in dataTable.rawTable[0]) {
			let row  = table[row_index]
			let sql  = 'select id, description from id_type where description = ${description} '
			let data = await this.db.one(sql, {description: row[0]})
			this.party.identifications.push({ident: row[1], id_type_id: data.id})
		}
	})

	Given('the id {string} of {string} type is in the party', async function (id_value, id_type) {
		let identity_type_id           = await this.db.one('select id from id_type where description = ${id_type}', {id_type})
		let data                       = await this.db.one("insert into party_id (ident, party_id, id_type_id) values (${ident}, ${party_id}, ${id_type_id}) returning id", {
			ident     : id_value,
			party_id  : this.party.id,
			id_type_id: identity_type_id.id
		})
		this.party_id.type.id          = identity_type_id.id
		this.party_id.type.description = id_type
		this.party_id.ident            = id_value
		this.party_id.id               = data.id
	})

	When('I add the {string} id of {string} to the party', async function (id_type, ident) {
		try {
			let id_type_id                 = await this.db.one('select id from id_type where description = ${id_type}', {id_type})
			this.party_id.ident            = ident
			this.party_id.type.id          = id_type_id.id
			this.party_id.type.description = id_type
			let response                   = await this.client
			.mutate({
				mutation : gql`mutation party_id_add_to_party($party_id: ID!, $ident:String!, $id_type_id: ID!)
        { party_id_add_to_party(party_id: $party_id, ident: $ident, id_type_id: $id_type_id) { id ident id_type {id description}}}`,
				variables: {
					party_id  : this.party.id,
					ident,
					id_type_id: id_type_id.id
				}
			})
			this.result.data               = response.data.party_id_add_to_party
		} catch (error) {
			this.result.error = error
		}
	})

	When('I change the id to {string}', async function (new_id) {
		try {
			this.party_id.ident = new_id
			let response        = await this.client.mutate({
				mutation : gql`mutation party_id_update($identity_id: ID!, $ident:String!) { party_id_update(identity_id: $identity_id, ident: $ident) { id ident id_type {id description}}}`,
				variables: {
					identity_id: this.party_id.id,
					ident      : new_id
				}
			})
			this.result.data    = response.data.party_id_update
		} catch (error) {
			this.result.error = error
		}
	})

	When('I delete the id', async function () {
		try {
			let response     = await this.client.mutate({
				mutation : gql`mutation party_id_delete($identity_id: ID!) { party_id_delete(identity_id: $identity_id) }`,
				variables: {
					identity_id: this.party_id.id
				}
			})
			this.result.data = response.data.party_id_delete
		} catch (error) {
			this.result.error = error
		}
	})

	Then('I get the party id back', function (callback) {
		expect(this.result.error, `error is: ${this.result.error}`).to.be.null
		expect(this.result.data).to.not.be.null
		let actual_id = this.result.data
		expect(actual_id.id).to.be.ok
		expect(actual_id.ident).to.be.equal(this.party_id.ident)
		expect(actual_id.id_type.description).to.be.equal(this.party_id.type.description)
		expect(actual_id.id_type.id).to.be.equal(this.party_id.type.id)
		callback()
	})

	Then('the party id type is present', function (callback) {
		expect(this.result.data.identifications.length).to.be.equal(this.party.identifications.length)
		callback()
	})

	Then('the party ids are present', function (callback) {
		this.party.identifications.forEach(p => {
			let found = this.result.data.identifications.find(n => p.ident === n.ident)
			expect(found).to.be.ok
			expect(found.id_type.id).to.be.equal(p.id_type_id)
			expect(found.id).to.be.ok
		})
		callback()
	})

	Then('the party id is in the database', async function () {
		let actual_id = this.result.data
		let party_id  = await this.db.one('select id, ident, from_date, thru_date, party_id, id_type_id from party_id where id = ${id}', actual_id)
		expect(party_id).to.not.be.null
		expect(party_id.name).to.be.equal(this.party_id.name)
		expect(party_id.party_id).to.be.equal(this.party.id)
		expect(party_id.id_type_id).to.be.equal(this.party_id.type.id)
	})

	Then('the party id is not in the database', function (callback) {
		expect(this.result.error, `error is: ${this.result.error}`).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data).to.be.true
		callback()
	})
})
