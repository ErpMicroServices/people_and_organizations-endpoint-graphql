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
	Given('a name type of {string} with a name of {string}', function (name_type, name) {
		return this.db.one('insert into name_type (description) values (${name}) returning id', {name})
			.then(data => {
				let new_name = {
					name        : name,
					name_type_id: data.id
				}
				this.party.names.push(new_name)

			})
			.catch(error => console.log('Error: ', error))
	})

	Then('the party name is present', function (callback) {
		expect(this.result.data.data[`${this.graphql_function}`].names.length).to.be.equal(this.party.names.length)
		callback()
	})

	Then('the party name type is present', function (callback) {
		expect(this.result.data.data[`${this.graphql_function}`].names.length).to.be.equal(this.party.names.length)
		callback()
	})
})
