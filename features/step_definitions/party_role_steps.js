import {fail} from 'assert'
import gql from 'graphql-tag'

var {
	    defineSupportCode
    } = require('cucumber')

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {

	Given('a party role type with a description of {string} is in the database', function (description) {
		this.party_role_type.description = description
		return this.db.one("insert into party_role_type (description) values (${description}) returning id", this.party_role_type)
			.then(data => this.party_role_type.id = data.id)
	})

	Given('a party role type with a description of {string}', function (description, callback) {
		this.party_role_type = {
			description
		}
		callback()
	})

	When('I save the party role type', function () {
		return this.client.mutate({
			mutation : gql`mutation create_party_role_type( $description: String!) { create_party_role_type( description: $description) {id description }}`,
			variables: {
				description: this.party_role_type.description
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the party role type is in the database', function () {
		expect(this.result.error).to.be.null
		return this.db.one("select id, description, parent_id from party_role_type where description = ${description}", this.result.data.data.create_party_role_type)
			.then(data => {
				expect(data.id).to.not.be.empty
				expect(data.description).to.be.equal(this.party_role_type.description)
			})
			.catch(error => fail(error))
	})
})