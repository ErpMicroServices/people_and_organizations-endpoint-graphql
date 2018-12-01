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
			.catch(error => console.log('Error: ', error))
	})

	Given('a type of {string}', function (type, callback) {
		this.party_type.type = convert_to_table_name(type)
		callback()
	})

	Given('a type with a description of {string}', function (description, callback) {
		this.party_type.description = description
		callback()
	})

	Given('the following types:', async function (dataTable) {
		let table = dataTable.rawTable
		for (let row_index in table) {
			let row    = table[row_index]
			let sql    = `insert into ${convert_to_table_name(row[0])}_type (description) values ($2) returning id`
			let result = await this.db.one(sql, row)
		}
	})

	When('I add a child type with a description of {string}', function (description) {
		this.child_party_type_description = description
		this.graphql_function             = `add_${this.party_type.type}_type_child`
		return this.client
			.mutate({
				mutation : gql`mutation add_type_child($description: String!, $parent_id: ID!) {${this.graphql_function}(description: $description, parent_id: $parent_id) {id description parent_id}}`,
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
				mutation : gql`mutation create_type( $description: String!) { ${this.graphql_function}( description: $description) {id description parent_id}}`,
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
		let query             = `query type_by_description( $description: String!) { ${this.graphql_function}(description: $description) {id description parent_id}}`
		return this.client
			.query({
				query    : gql(query),
				variables: {
					'description': description
				}
			})

			.then((response) => this.result.data = response)
			.catch(error => this.result.error = error)
	})

	When('I search for the parent type', function () {
		this.graphql_function = `${this.party_type.type}_type_by_description`
		let query             = `query type_by_description( $description: String!) { ${this.graphql_function}(description: $description) {id description parent_id children {id description}}}`
		return this.client
			.query({
				query    : gql(query),
				variables: {
					'description': this.party_type.description
				}
			})

			.then((response) => this.result.data = response)
			.catch(error => this.result.error = error)
	})

	Then('the type description has been updated', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null

		let result = this.result.data.data[this.graphql_function]
		expect(result.description).to.be.equal(this.party_type.description)
		callback()
	})

	Then('I find the type', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
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
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
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
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		let result = this.result.data.data[this.graphql_function]
		expect(result).to.be.true
		callback()
	})

	Then('I can find the parent of the child  of the type', function () {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		let sql    = `select id, description from ${this.party_type.type}_type where id =` + '${parent_id}'
		let result = this.result.data.data[this.graphql_function]
		return this.db.one(sql, {
				parent_id: result.parent_id
			})
			.then(parent => {
				expect(parent).to.not.be.empty
				expect(result.parent_id).to.be.equal(parent.id)
			})
	})

	Then('I find the child type', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		let result = this.result.data.data[this.graphql_function][0]
		expect(result.children.length).to.be.equal(1)
		expect(result.children[0].description).to.be.equal(this.child_party_type_description)
		callback()
	})
})
