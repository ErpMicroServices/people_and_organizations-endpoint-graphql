import 'babel-polyfill'

var {
	    defineSupportCode
    } = require('cucumber')


defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {
	Given('{int} parties have a case role type {string}', async function (number_of_parties, case_role_type) {
		let case_role = await this.db.one('select id, description, parent_id from case_role_type where description = ${case_role_type}', {case_role_type})
		for (let i = 0; i < number_of_parties; i++) {
			this.case.roles.push({
				case_role_type_id: case_role.id,
				party_id         : this.parties[i].id
			})
		}
	})

	Given('the case has been saved to the database', function (callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})

	Given('party with case role {string} has been added to the case', function (string, callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})

	When('I add a party with case role {string} to the case', function (case_role_type_description, callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})

	When('I change the party in the role', function (callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})

	When('I delete the case role', function (callback) {
		// Write code here that turns the phrase above into concrete actions
		callback(null, 'pending')
	})

	Then('the case has {int} roles', function (expected_number_of_roles, callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		expect(this.result.data.roles).to.be.ok
		expect(this.result.data.roles.length).to.be.equal(expected_number_of_roles)
		callback()
	})

	Then('the {int} roles have type {string}', async function (expected_number_of_role, case_role_type_description) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		expect(this.result.data.roles.length).to.be.equal(expected_number_of_roles)
		let expected_case_role = await this.db.one('select id from case_role_type where description = ${case_role_type_description}', {case_role_type_description})
		this.result.data.roles.forEach(role => expect(role.role_type.id).to.be.equal(expected_case_role.id))
	})

	Then('the role includes the party', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		expect(this.result.data.id).to.be.equal(this.party.id)
		callback()
	})

	Then('the roles include the parties', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		let expected_party_ids = this.parties.map(p => p.id)
		this.result.data.roles.each(r => expect(r.party.id).to.be.in(expected_party_ids))
		callback()
	})


})
