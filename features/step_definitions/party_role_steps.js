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

	When('I add a child party role type with a description of {string}', function (description) {
		this.child_party_role_type_description = description
		this.party_role_type_child             = {
			parent_id: this.party_role_type.id,
			description
		}
		return this.client.mutate({
			mutation : gql`mutation add_party_role_type_child( $description: String!, $parent_id: ID!) { add_party_role_type_child( description: $description, parent_id: $parent_id) {id description parent_id}}`,
			variables: {
				description: this.party_role_type_child.description,
				parent_id  : this.party_role_type_child.parent_id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('I can find the parent of the child  of the party role type', function () {
		console.log("this.result: ", this.result)
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.add_party_role_type_child).to.not.be.null
		expect(this.result.data.data.add_party_role_type_child.id).to.not.be.null
		return this.db.one("select id, description, parent_id from party_role_type where id = ${id}", this.result.data.data.add_party_role_type_child)
			.then(data => {
				expect(data.parent_id).to.be.equal(this.party_role_type.id)
				expect(data.description).to.be.equal(this.child_party_role_type_description)
			})
	})
})