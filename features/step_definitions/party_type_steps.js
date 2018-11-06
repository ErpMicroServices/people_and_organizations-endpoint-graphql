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

	Given('a party type with a description of {string} has been saved to the database', function (description) {
		this.party_type.description = description
		return this.db.one("insert into party_type (description) values ($1) returning id", [description])
			.then(data => this.party_type.id = data.id)

	})


	When('I add a child party type with a description of {string}', function (description) {
		this.child_party_type_description = description
		return this.client
		.mutate({
			mutation : gql`mutation add_party_type_child($description: String!, $parent_id: ID!) {add_party_type_child(description: $description, parent_id: $parent_id) {id}}`,
			variables: {
				description: description,
				parent_id  : this.party_type.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('I can find the parent of the child', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.add_party_type_child).to.not.be.null
		expect(this.result.data.data.add_party_type_child.id).to.not.be.null
		return this.db.one("select id, description, parent_id from party_type where id = ${id}", this.result.data.data.add_party_type_child)
			.then(data => {
				expect(data.parent_id).to.be.equal(this.party_type.id)
				expect(data.description).to.be.equal(this.child_party_type_description)
			})
	})

	When('I delete the party type', function () {
		return this.client
		.mutate({
			mutation : gql`mutation delete_party_type( $id: ID!) { delete_party_type(id: $id) }`,
			variables: {
				id: this.party_type.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the party type is not in the database', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data).to.not.be.null
		expect(this.result.data.data.result).to.not.be.equal("success")
		callback()
	})

	When('I update the description to {string}', function (string) {
		this.party_type.description = string
		return this.client
		.mutate({
			mutation : gql`mutation update_party_type( $id: ID!, $description: String!) { update_party_type(id: $id, description: $description) {id description parent_id}}`,
			variables: {
				id         : this.party_type.id,
				description: this.party_type.description
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})


	When('I search by the description', function () {
		return this.db.one("select id, description, parent_id from party_type where description = ${description}", this.party_type)
			.then(data => this.result.data = data)
			.catch(error => this.result.error = error)
	})

	Then('I find the party type', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		if ((this.result.data.data) && (this.result.data.data.update_party_type)) {
			expect(this.result.data.data.update_party_type.description).to.be.equal(this.party_type.description)
		} else {
			expect(this.result.data.description).to.be.equal(this.party_type.description)
		}
		callback()
	})

	Given('a party type description of {string}', function (description, callback) {
		this.party_type.description = description
		callback()
	})

	When('I save the party type', function () {
		return this.client
		.mutate({
			mutation : gql`mutation create_party_type( $description: String!) { create_party_type( description: $description) {id description }}`,
			variables: {
				description: this.party_type.description
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('The party type is in the database', function () {
		console.log("This result: ", this.result)
		return this.db.one("select id, description, parent_id from party_type where description = ${description}", this.result.data.data.create_party_type)
			.then(data => console.log("data: ", data))
			.catch(error => fail(error))
	})
})
