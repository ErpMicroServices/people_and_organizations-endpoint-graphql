import 'babel-polyfill'

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

	Given('party names of', async function (dataTable) {
		let table = dataTable.rawTable
		for (let row_index in dataTable.rawTable[0]) {
			let row  = table[row_index]
			let sql  = 'select id, description from name_type where description = ${description} '
			let data = await this.db.one(sql, {description: row[0]})
			this.party.names.push({name: row[1], name_type_id: data.id})
		}
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
