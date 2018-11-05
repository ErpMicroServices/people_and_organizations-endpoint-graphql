import gql from 'graphql-tag'

var {
	    defineSupportCode
    } = require('cucumber')

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {

	Given('a party type with a description of {string} has been saved to the database', function (string) {
		return this.db.one("insert into party_type (description) values ($1) returning id", [string])
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

	// When('I delete the party type', function () {
	// 	return this.client
	// 			.mutate({
	// 				mutation : gql `mutation delete_party_type( $id: ID!) { delete_party_type(id: $id) }`,
	// 				variables: {
	// 					id: this.party_type.id
	// 				}
	// 			})
	// 			.then(results => this.result.data = results)
	// 			.catch(error => this.result.error = error);
	// });

	Then('the party type is not in the database', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.data).to.not.be.null
		expect(this.result.data.data.result).to.not.be.equal("success")
		callback()
	})

	// When('I update the description to {string}', function (string) {
	// 	this.party_type.description = string;
	// 	return this.client
	// 			.mutate({
	// 				mutation : gql `mutation update_party_type( $id: ID!, $description: String!) { update_party_type(id: $id, description: $description) {id description parent_id}}`,
	// 				variables: {
	// 					id         : this.party_type.id,
	// 					description: this.party_type.description
	// 				}
	// 			})
	// 			.then(results => this.result.data = results)
	// 			.catch(error => this.result.error = error);
	// });

	Then('the organization description has been updated', function () {
		return this.db.one("select id, description, parent_id from party_type where id = ${id}", this.party_type)
			.then(data => expect(data.description).to.be.equal(this.party_type.description))
	})
})
