import gql from 'graphql-tag'

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
	Given('a type of {string} with a description of {string} is in the database', function (type, description) {
		this.party_type.description = description
		this.party_type.type        = convert_to_table_name(type)
		let sql                     = `insert into ${this.party_type.type}_type (description) values ($1) returning id`
		return this.db.one(sql, [description])
			.then(data => this.party_type.id = data.id)
			.catch(error => console.log("Error: ", error))
	})

	Given('a type of {string}', function (type, callback) {
		this.party_type.type = convert_to_table_name(type)
		callback()
	})

	Given('a type with a description of {string}', function (description, callback) {
		this.party_type.description = description
		callback()
	})

	Given('I add a child type with a description of {string}', function (description) {
		this.child_party_type_description = description
		return this.client
		.mutate({
			mutation : gql`mutation add_type_child($description: String!, $parent_id: ID!) {add_${this.party_type.type}_type_child(description: $description, parent_id: $parent_id) {id}}`,
			variables: {
				description: description,
				parent_id  : this.party_type.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	When('I save the type', function () {
		this.graphql_function = `create_${this.party_type.type}_type`
		return this.client.mutate({
			mutation : gql`mutation create_type( $description: String!) { ${this.graphql_function}( description: $description) {id description }}`,
			variables: {
				description: this.party_type.description
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	When('I delete the type', function () {
		this.graphql_function = `delete_${this.party_type.type}_type`
		return this.client
		.mutate({
			mutation : gql`mutation delete_type( $id: ID!) { ${this.graphql_function}(id: $id) }`,
			variables: {
				id: this.party_type.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	When('I update the description of the type to {string}', function (updated_description) {
		this.party_type.description = updated_description
		this.graphql_function       = `update_${this.party_type.type}_type`
		return this.client
		.mutate({
			mutation : gql`mutation update_type( $id: ID!, $description: String!) { ${this.graphql_function}(id: $id, description: $description) {id description parent_id}}`,
			variables: {
				id         : this.party_type.id,
				description: this.party_type.description
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	When('I search for the type by the description {string}', function (description) {
		this.graphql_function = `${this.party_type.type}_type_by_description`
		let query             = `query type_by_description( $description: String!) { ${this.graphql_function}(description: $description) {id description}}`
		return this.client
			.query({
				query    : gql(query),
				variables: {
					"description": description
				}
			})

			.then((response) => this.result.data = response)
			.catch(error => this.result.error = error)
	})

	When('I search for the parent type', function (callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})


	Then('the type description has been updated', function (callback) {
		if (this.result.error) {
			console.log("this.result.error.networkError.result: ", this.result.error.networkError.result)
		}
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null

		let result = this.result.data.data[this.graphql_function]
		expect(result.description).to.be.equal(this.party_type.description)
		callback()
	})

	Then('I find the type', function (callback) {
		if (this.result.error) {
			console.log("this.result.error.networkError.result: ", this.result.error.networkError.result)
		}
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		let result = this.result.data.data[this.graphql_function]
		if (this.graphql_function.endsWith('_type_by_description')) {
			expect(result.length).to.be.equal(1)
			expect(result[0].description).to.be.equal(this.party_type.description)
		} else {
			expect(result.description).to.be.equal(this.party_type.description)
		}
		callback()
	})

	Then('the type is in the database', function (callback) {
		if (this.result.error) {
			console.log("this.result.error.networkError.result: ", this.result.error.networkError.result)
		}
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		let result = this.result.data.data[this.graphql_function]
		if (this.graphql_function.startsWith('query')) {
			expect(result.length).to.be.equal(1)
			expect(result[0].description).to.be.equal(this.party_type.description)
		} else {
			expect(result.description).to.be.equal(this.party_type.description)
		}

		callback()
	})

	Then('the type is not in the database', function (callback) {
		if (this.result.error) {
			console.log("this.result.error.networkError.result: ", this.result.error.networkError.result)
		}
		expect(this.result.error).to.be.null
		expect(this.result.data).to.not.be.null
		let result = this.result.data.data[this.graphql_function]
		expect(result).to.be.true
		callback()
	})

	Then('I can find the parent of the child  of the type', function (callback) {
		callback(null, 'pending')
	})

	Then('I find the child type', function (callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})
})
