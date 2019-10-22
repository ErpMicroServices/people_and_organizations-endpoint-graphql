import {Given, Then, When}     from 'cucumber'
import gql                     from 'graphql-tag'
import {convert_to_table_name} from '../util'


Given('a type of {string} with a description of {string} is in the database', async function (type, description) {
	this.party_type.description=description
	this.type.table_name       =convert_to_table_name(type)
	let sql                    =`insert into ${this.type.table_name}_type (description) values ($1) returning id`
	let new_type               =await this.db.one(sql, [description])
	this.party_type.id         =new_type.id
})

Given('a type of {string}', function (type, callback) {
	this.type.table_name=convert_to_table_name(type)
	callback()
})

Given('a type with a description of {string}', function (description, callback) {
	this.party_type.description=description
	callback()
})

Given('the following types:', async function (dataTable) {
	let table=dataTable.rawTable
	for (let row_index in table) {
		let row=table[row_index]
		let sql=`insert into ${convert_to_table_name(row[0])}_type (description) values ($2) returning id`
		await this.db.one(sql, row)
	}
})

When('I add a child type with a description of {string}', async function (description) {
	try {
		this.child_party_type_description=description
		let graphql_function             =`${this.type.table_name}_type_add_child`
		let response                     =await this.client.mutate({
																																 mutation : gql`mutation add_type_child($description: String!, $parent_id: ID!) {${graphql_function}(description: $description, parent_id: $parent_id) {id description parent_id}}`,
																																 variables: {
																																	 description: description,
																																	 parent_id  : this.party_type.id
																																 }
																															 })
		this.result.data                 =response.data[graphql_function]
	} catch (error) {
		this.result.error=error
	}
})

When('I save the type', async function () {
	try {
		let graphql_function=`${this.type.table_name}_type_create`
		let response        =await this.client.mutate({
																										mutation : gql`mutation create_type( $description: String!) { ${graphql_function}( description: $description) {id description parent_id}}`,
																										variables: {
																											description: this.party_type.description
																										}
																									})
		this.result.data    =response.data[graphql_function]
	} catch (error) {
		this.result.error=error
	}
})

When('I delete the type', async function () {
	try {
		let graphql_function=`${this.type.table_name}_type_delete`
		let response        =await this.client.mutate({
																										mutation : gql`mutation delete_type( $id: ID!) { ${graphql_function}(id: $id) }`,
																										variables: {
																											id: this.party_type.id
																										}
																									})
		this.result.data    =response.data[graphql_function]
	} catch (error) {
		this.result.error=error
	}
})

When('I update the description of the type to {string}', async function (updated_description) {
	try {
		this.party_type.description=updated_description
		let graphql_function       =`${this.type.table_name}_type_update`
		let response               =await this.client.mutate({
																													 mutation : gql`mutation update_type( $id: ID!, $description: String!) { ${graphql_function}(id: $id, description: $description) {id description parent_id}}`,
																													 variables: {
																														 id         : this.party_type.id,
																														 description: this.party_type.description
																													 }
																												 })
		this.result.data           =response.data[graphql_function]
	} catch (error) {
		this.result.error=error
	}
})

When('I search for the type by the description {string}', async function (description) {
	try {
		let graphql_function=`${this.type.table_name}_type_by_description`
		let query           =`query type_by_description( $description: String!) { ${graphql_function}(description: $description) {id description parent_id}}`
		let response        =await this.client.query({
																									 query    : gql(query),
																									 variables: {
																										 'description': description
																									 }
																								 })
		this.result.data    =response.data[graphql_function]
	} catch (error) {
		this.result.error=error
	}
})

When('I search for the parent type', async function () {
	try {
		let graphql_function=`${this.type.table_name}_type_by_description`
		let query           =`query type_by_description( $description: String!) { ${graphql_function}(description: $description) {id description parent_id children {id description}}}`
		let response        =await this.client
			.query({
							 query    : gql(query),
							 variables: {
								 'description': this.party_type.description
							 }
						 })

		this.result.data=response.data[graphql_function]
	} catch (error) {
		this.result.error=error
	}
})

Then('the type description has been updated', function (callback) {
	expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
	expect(this.result.data).to.not.be.null

	let result=this.result.data
	expect(result.description).to.be.equal(this.party_type.description)
	callback()
})

Then('I find the type', function (callback) {
	expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
	expect(this.result.data).to.not.be.null
	let result=this.result.data
	if (Array.isArray(result)) {
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
	let result=this.result.data

	if (Array.isArray(result)) {
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
	let result=this.result.data
	expect(result).to.be.true
	callback()
})

Then('I can find the parent of the child  of the type', async function () {
	expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
	expect(this.result.data).to.not.be.null
	let sql   =`select id, description from ${this.type.table_name}_type where id =` + '${parent_id}'
	let result=this.result.data
	let parent=await this.db.one(sql, {
		parent_id: result.parent_id
	})

	expect(parent).to.not.be.empty
	expect(result.parent_id).to.be.equal(parent.id)

})

Then('I find the child type', function (callback) {
	expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
	expect(this.result.data).to.not.be.null
	let result=this.result.data[0]
	expect(result.children.length).to.be.equal(1)
	expect(result.children[0].description).to.be.equal(this.child_party_type_description)
	callback()
})
