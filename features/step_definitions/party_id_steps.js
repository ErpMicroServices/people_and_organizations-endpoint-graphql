import 'babel-polyfill'

var {
	    defineSupportCode
    } = require('cucumber')


defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {
	Given('party ids of', async function (dataTable) {
		let table = dataTable.rawTable
		for (let row_index in dataTable.rawTable[0]) {
			let row  = table[row_index]
			let sql  = 'select id, description from id_type where description = ${description} '
			let data = await this.db.one(sql, {description: row[0]})
			this.party.identifications.push({ident: row[1], id_type_id: data.id})
		}
	})

	Then('the party id type is present', function (callback) {
		expect(this.result.data.data[`${this.graphql_function}`].identifications.length).to.be.equal(this.party.identifications.length)
		callback()
	})
})
