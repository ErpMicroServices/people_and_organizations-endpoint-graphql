import gql                      from 'graphql-tag'
import {create_parties_of_type} from './utils'

var {
	    defineSupportCode
    } = require('cucumber')


defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {
	Given('{int} parties of type {string} have a case role type {string}', async function (number_of_parties, party_type_description, case_role_type_description) {
		this.parties       = await create_parties_of_type(this.db, party_type_description, number_of_parties)
		let case_role_type = await this.db.one('select id, description, parent_id from case_role_type where description = ${case_role_type_description}', {case_role_type_description})

		for (let i = 0; i < number_of_parties; i++) {
			this.case.roles.push({
														 case_role_type_id: case_role_type.id,
				party_id                              : this.parties[i].id
			})
		}
	})

	Given('party with case role {string} has been added to the case', async function (case_role_type_description) {
		let case_role_type = await this.db.one('select id, description, parent_id from case_role_type where description = ${case_role_type_description}', {case_role_type_description})
		let case_role      = await this.db.one('insert into case_role (case_id, case_role_type_id, party_id) values (${case_id}, ${case_role_type_id}, ${party_id}) returning id', {
			case_id          : this.case.id,
			case_role_type_id: case_role_type.id,
			party_id         : this.party.id
		})
		this.case_role     = {
			id   : case_role.id,
			type :
				{
					id         : case_role_type.id,
					description: ''
				},
			party: {
				id     : this.party.id,
				comment:
					''
			}
		}
	})

	When('I add a party with case role {string} to the case', async function (case_role_type_description) {
		try {
			let case_role_type = await this.db.one('select id, description, parent_id from case_role_type where description = ${case_role_type_description}', {case_role_type_description})
			let inputCaseRole  = {
				case_role_type_id: case_role_type.id,
				party_id         : this.party.id
			}
			let result         = await this.client.mutate({
				mutation : gql`mutation case_role_add($case_id: ID!, $input_case_role: InputCaseRole!){ case_role_add(case_id: $case_id, input_case_role: $input_case_role) {id description status{id} roles{id type {id} party{id}} started_at, case_type{id}}}`,
				variables: {
					case_id        : this.case.id,
					input_case_role: inputCaseRole
				}
			})

			this.result.data = result.data.case_role_add
		} catch (error) {
			this.result.error = error
		}
	})

	When('I change the party in the role', async function () {
		let new_party_result = await this.db.one('insert into party (comment, party_type_id) values (${comment}, ${party_type_id}) returning id', {
			comment      : 'new party',
			party_type_id: this.party_type.id
		})
		this.party           = Object.assign({
			id             : '',
			comment        : '',
			identifications: [],
			party_type_id  : '',
			names          : []
		}, {
			id           : new_party_result.id,
			comment      : 'new party',
			party_type_id: this.party_type.id
		})
		try {
			let inputCaseRole = {
				case_role_type_id: this.case_role.type.id,
				party_id         : this.party.id
			}
			let result        = await this.client.mutate({
				mutation : gql`mutation case_role_update($case_role_id: ID!, $input_case_role: InputCaseRole!){ case_role_update(case_role_id: $case_role_id, input_case_role: $input_case_role) {id description status{id} roles{id type {id} party{id}} started_at, case_type{id}}}`,
				variables: {
					case_role_id   : this.case_role.id,
					input_case_role: inputCaseRole
				}
			})

			this.result.data = result.data.case_role_update
		} catch (error) {
			this.result.error = error
		}
	})

	When('I delete the case role', async function () {
		try {
			let result = await this.client.mutate({
				mutation : gql`mutation case_role_delete($case_role_id: ID!){ case_role_delete(case_role_id: $case_role_id) {id description status{id} roles{id type {id} party{id}} started_at, case_type{id}}}`,
				variables: {
					case_role_id: this.case_role.id
				}
			})

			this.result.data = result.data.case_role_delete
		} catch (error) {
			this.result.error = error
		}
	})

	Then('the case has {int} roles', function (expected_number_of_roles, callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		expect(this.result.data.roles).to.be.ok
		expect(this.result.data.roles.length).to.be.equal(expected_number_of_roles)
		callback()
	})

	Then('the {int} roles have type {string}', async function (expected_number_of_roles, case_role_type_description) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		expect(this.result.data.roles.length).to.be.equal(expected_number_of_roles)
		let expected_case_role = await this.db.one('select id from case_role_type where description = ${case_role_type_description}', {case_role_type_description})
		this.result.data.roles.forEach(role => expect(role.type.id).to.be.equal(expected_case_role.id))
	})

	Then('the role includes the party', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		expect(this.result.data.roles[0].party.id).to.be.equal(this.party.id)
		callback()
	})

	Then('the roles include the parties', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		let expected_party_ids = this.parties.map(p => p.id)
		this.result.data.roles.forEach(r => expect(r.party.id).to.be.oneOf(expected_party_ids))
		callback()
	})


})
