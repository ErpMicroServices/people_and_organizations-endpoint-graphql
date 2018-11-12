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

	Given('I have provided an organization name of {string}', function (organization_name, callback) {
		this.party.name = organization_name
		callback()
	})

	Given('I have provided a government id of {string}', function (government_id, callback) {
		this.party.government_id = government_id
		callback()
	})

	Given('the organization is in the database', function () {
		this.party.party_type_id = this.party_type_id("Organization")
		return this.db.one("insert into party (name, government_id, comment, party_type_id) values(${name}, ${government_id}, ${comment}, ${party_type_id}) returning id", this.party)
			.then((data) => this.party.id = data.id)
	})

	When('I delete the organization', function () {
		return this.client
		.mutate({
			mutation : gql`mutation delete_organization($id: ID!) {delete_organization(id: $id)}`,
			variables: {
				"id": this.party.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the organization is no longer in the database', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data.data).to.exist
		return this.db.one("select id from party where id=$1", [this.party.id])
			.then((data) => expect(data).to.not.exist)
			.catch((error) => expect(error.message).to.be.equal("No data returned from the query."))
	})

	When('I update the name of the organization to {string}', function (new_name) {
		this.organization_new_name = new_name
		return this.client.mutate({
			mutation : gql`mutation update_organization($id: ID!, $name: String, $government_id: String, $comment: String) {update_organization(id: $id, name: $name, government_id: $government_id, comment: $comment) {id, name}}`,
			variables: {
				"id"           : this.party.id,
				"name"         : this.organization_new_name,
				"government_id": this.party.government_id,
				"comment"      : this.party.comment
			}
		})
		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	Then('the organization name has been updated', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data.update_organization).to.not.be.null
		expect(this.result.data.data.update_organization.id).to.not.be.null
		expect(this.result.data.data.update_organization.name).to.not.be.null
		expect(this.result.data.data.update_organization.name).to.be.equal(this.organization_new_name)
		return this.db.one("select id, name, government_id from party where id = ${id}", this.party)
			.then(data => {
				expect(data.name).to.be.equal(this.organization_new_name)
			})
			.catch(error => fail(error))
	})

	When('I search for all the organizations', function () {
		return this.client
		.query({
			query    : gql`query organizations( $start: Int!, $records: Int!) {
        organizations(start: $start, records: $records) {
          id
          name
          government_id
          comment
        }
      }`,
			variables: {
				"start"  : 0,
				"records": 100
			}
		})
		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	Then('I find the organization in the list', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data.data).to.exist
		let data = this.result.data.data.organizations[0]
		expect(data.name).to.be.equal(this.party.name)
		expect(data.government_id).to.be.equal(this.party.government_id)
		expect(data.comment).to.be.equal(this.party.comment)
		callback()
	})

	When('I search by the organizations id', function () {
		return this.client
		.query({
			query    : gql`query organziation_by_id( $id: ID!) {
        organization_by_id(id: $id) {
          id
          name
          government_id
          comment
        }
      }`,
			variables: {
				"id": this.party.id
			}
		})
		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	Then('I find the organization', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data.data).to.exist
		expect(this.result.data.data.organization_by_id).to.exist
		let data = this.result.data.data.organization_by_id
		expect(data.name).to.be.equal(this.party.name)
		expect(data.government_id).to.be.equal(this.party.government_id)
		expect(data.comment).to.be.equal(this.party.comment)
		callback()
	})

	When('I save the organization', function () {
		return this.client.mutate({
			mutation : gql`mutation create_organization($name: String, $government_id: String, $comment: String) {create_organization(name: $name, government_id: $government_id, comment: $comment) {id name government_id comment}}`,
			variables: {
				"name"         : this.party.name,
				"government_id": this.party.government_id,
				"comment"      : this.party.comment
			}
		})
		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	Given('I have not provided an organization name', function (callback) {
		this.party.name = ''
		callback()
	})

	Then('I get an error indicating that a name is required', function (callback) {
		console.log("this.result.error: ", this.result.error)
		console.log("this.result.error.networkError.result.errors: ", this.result.error.networkError.result.errors)
		expect(this.result.data).to.be.null
		expect(this.result.error).to.exist
		expect(this.result.error.networkError.statusCode).to.be.equal(400)
		expect(this.result.error.networkError.result.errors.length).to.be.equal(1)
		expect(this.result.error.networkError.result.errors[0].message).to.be.equal('Variable "$name" of type "String" used in position expecting type "String!".')
		callback()
	})
})
