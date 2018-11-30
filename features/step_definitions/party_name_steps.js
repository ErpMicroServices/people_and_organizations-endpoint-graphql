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
	Given('a name type of {string} with a name of {string}', function (name_type, name, table) {
		let promises = []
		for (let row in table.rawTable) {
			promises.push(this.db.one('insert into name_type (description) values (${name}) returning id', {name: table.rawTable[row][0]})
				.then(data => {
					let new_name = {
						name        : table.rawTable[row][1],
						name_type_id: data.id
					}
					this.party.names.push(new_name)
				}))
		}
		return Promise.all(promises)

	})

	Then('the party name is present', function (callback) {
		expect(this.result.data.data[`${this.graphql_function}`].names.length).to.be.equal(this.party.names.length)
		callback()
	})

	Then('the party name type is present', function (callback) {
		expect(this.result.data.data[`${this.graphql_function}`].names.length).to.be.equal(this.party.names.length)
		callback()
	})

	Then('the party names are present', function (callback) {
		this.party.names.forEach(p => {
			let found = this.result.data.data[`${this.graphql_function}`].names.find(n => p.name === n.name)
			expect(found).to.be.ok
			expect(found.name_type.id).to.be.equal(p.name_type_id)
			expect(found.id).to.be.ok
		})
		callback()
	})
})
