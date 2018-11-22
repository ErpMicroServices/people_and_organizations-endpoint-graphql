import {fail} from 'assert'
import gql from 'graphql-tag'
import moment from "moment"

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
		return this.db.one('insert into party_role_type (description) values (${description}) returning id', this.party_role_type)
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
		return this.db.one('select id, description, parent_id from party_role_type where description = ${description}', this.result.data.data.create_party_role_type)
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
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.add_party_role_type_child).to.not.be.null
		expect(this.result.data.data.add_party_role_type_child.id).to.not.be.null
		return this.db.one('select id, description, parent_id from party_role_type where id = ${id}', this.result.data.data.add_party_role_type_child)
			.then(data => {
				expect(data.parent_id).to.be.equal(this.party_role_type.id)
				expect(data.description).to.be.equal(this.child_party_role_type_description)
			})
	})

	When('I delete the party role type', function () {
		return this.client.mutate({
			mutation : gql`mutation delete_party_role_type($id: ID!) {delete_party_role_type(id: $id)}`,
			variables: {
				id: this.party_role_type.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the party role type is not in the database', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.delete_party_role_type).to.not.be.null
		expect(this.result.data.data.delete_party_role_type.id).to.not.be.null
		return this.db.one('select id, description, parent_id from party_role_type where id = ${id}', this.party_role_type)
			.then(data => {
				fail(data, null, 'No data should be left')
			})
			.catch(error => expect(error).to.not.be.null)
	})

	When('I update the description of the party role type to {string}', function (new_description) {
		this.update_party_role_type_description = new_description
		return this.client.mutate({
			mutation : gql`mutation update_party_role_type($id: ID!, $description: String!) {update_party_role_type(id: $id, description: $description){ id description parent_id}}`,
			variables: {
				id         : this.party_role_type.id,
				description: new_description
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the party role type description has been updated', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.update_party_role_type).to.not.be.null
		expect(this.result.data.data.update_party_role_type.id).to.not.be.null
		expect(this.result.data.data.update_party_role_type.description).to.not.be.null
		expect(this.result.data.data.update_party_role_type.description).to.be.equal(this.update_party_role_type_description)
		return this.db.one('select id, description, parent_id from party_role_type where id = ${id}', this.party_role_type)
			.then(data => {
				expect(data.description).to.be.equal(this.update_party_role_type_description)
			})
			.catch(error => fail(error))
	})

	When('I search for a party role type by the description {string}', function (description) {
		return this.client.query({
			query    : gql`query party_role_type($description: String!) {party_role_type( description: $description){ id description parent_id}}`,
			variables: {
				description: this.party_role_type.description
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('I find the party role type', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.party_role_type).to.not.be.null
		expect(this.result.data.data.party_role_type.id).to.not.be.null
		expect(this.result.data.data.party_role_type.description).to.not.be.null
		expect(this.result.data.data.party_role_type.description).to.be.equal(this.party_role_type.description)
		callback()
	})

	Given('a person with a first name of {string}, a last name of {string} is in the database', function (first_name, last_name) {
		this.party.first_name    = first_name
		this.party.last_name     = last_name
		this.party.party_type_id = this.party_type_id('Person')
		return this.db.one('insert into party (first_name, last_name, party_type_id) values(${first_name}, ${last_name}, ${party_type_id}) returning id', this.party)
			.then((data) => this.party.id = data.id)
	})

	Given('the person has a party role of {string}', function (description) {

		return this.db.one('select id, description from party_role_type where description = ${description}', {description})
			.then(data => this.party_role_type = data)
			.then(() => this.db.one('insert into party_role (party_role_type_id, party_id) values (${party_role_type_id}, ${party_id}) returning id', {
				party_role_type_id: this.party_role_type.id,
				party_id          : this.party.id
			}))
			.then(data =>
				this.party.roles.push({
					id         : data.id,
					description: this.party_role_type.description
				}))
	})

	When('I expire the party role {string}', function (description) {
		let role       = this.party.roles.find(role => role.description === description)
		role.thru_date = moment().subtract(1, 'd')
		return this.client.mutate({
			mutation : gql`mutation update_party_role($id: ID!, $from_date: Date, $thru_date: Date){update_party_role(id: $id, from_date: $from_date, thru_date: $thru_date) {id type fromDate thruDate}}`,
			variables: {
				id       : role.id,
				from_date: role.from_date,
				thru_date: role.thru_date
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the party role {string} is in the database', function (string, callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})

	Then('the party role does not show up for the person', function (callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})

	Then('the the thru date for party role {string} is set to today', function (description, callback) {
		return this.db.one('select id, description, thru_date from party_role where party_role_type_id = ( select id from party_role_type where description = ${description})', {description})
			.then(data => {
				expect(data.thru_date).to.be.equal(this.party.party_roles[0].thru_date)
			})
	})
})
