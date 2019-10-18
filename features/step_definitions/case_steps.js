import gql from 'graphql-tag'

var {
			defineSupportCode
		}=require('cucumber')


defineSupportCode(function ({
															Given,
															When,
															Then
														}) {
	Given('there are {int} cases with a type of {string} with a status of {string} in the database', async function (number_of_cases, case_type, case_status) {
		let case_type_id=await this.db.one('insert into case_type (description) values (${description}) returning id', {description: case_type})
		if ((this.case_status_type.id === '') || (this.case_status_type.description != case_status)) {
			let case_status_type_id=await this.db.one('insert into case_status_type (description) values (${description}) returning id', {description: case_status})
			this.case_status_type  ={id: case_status_type_id.id, description: case_status}
		}
		this.case_type={id: case_type_id.id, description: case_type}
		for (let i=0; i < number_of_cases; i++) {
			let description=`case number ${i}`
			let case_id    =await this.db.one('insert into "case" (description, case_type_id, case_status_type_id) values ( ${description}, ${case_type_id}, ${case_status_type_id} ) returning id', {
				description,
				case_type_id       : this.case_type.id,
				case_status_type_id: this.case_status_type.id
			})
			this.cases.push({
												id                 : case_id.id,
												description,
												case_type_id       : this.case_type.id,
												case_status_type_id: this.case_status_type.id
											})
		}
	})

	Given('a case description of {string}', function (case_description, callback) {
		this.case.description=case_description
		callback()
	})

	Given('a case status of {string}', async function (case_status_description) {
		let case_status_type_id      =await this.db.one('select id from case_status_type where description = ${case_status_description}', {case_status_description})
		this.case.case_status_type_id=case_status_type_id.id
	})

	Given('a case type of {string}', async function (case_type_description) {
		let case_type_id      =await this.db.one('select id, description from case_type where description = ${description}', {description: case_type_description})
		this.case.case_type_id=case_type_id.id
	})

	Given('the case is saved to the database', async function () {
		let case_id=await this.db.one('insert into "case" (description, case_type_id, case_status_type_id) values( ${description}, ${case_type_id}, ${case_status_type_id}) returning id', {
			description        : this.case.description,
			case_type_id       : this.case.case_type_id,
			case_status_type_id: this.case.case_status_type_id
		})
		this.case  =Object.assign({},

															this.case,
															{
																id: case_id.id
															})

	})

	When('I search for all cases', async function () {
		try {
			let result      =await this.client.query({
																								 query    : gql`query cases($start: Int!, $records: Int!) {cases(start: $start, records: $records){id description started_at case_type{id} status{id}}}`,
																								 variables: {
																									 'start'  : 0,
																									 'records': this.cases.length + 10
																								 }
																							 })
			this.result.data=result.data.cases
		} catch (error) {
			this.result.error=error
		}
	})

	When('I search for cases of type {string}', async function (case_type) {
		try {
			let result=await this.client.query({
																					 query    : gql`query cases_by_type($case_type: String!, $start: Int!, $records: Int!) {cases_by_type(case_type: $case_type, start: $start, records: $records){id description started_at case_type{id} status{id}}}`,
																					 variables: {
																						 'start'  : 0,
																						 'records': this.cases.length + 10,
																						 case_type
																					 }
																				 })

			this.result.data=result.data.cases_by_type
		} catch (error) {
			this.result.error=error
		}
	})

	When('I search for cases with a status of {string}', async function (case_status) {
		try {
			let result      =await this.client.query({
																								 query    : gql`query cases_by_status($case_status: String!, $start: Int!, $records: Int!) {cases_by_status(case_status: $case_status, start: $start, records: $records){id description started_at case_type{id} status{id}}}`,
																								 variables: {
																									 'start'  : 0,
																									 'records': this.cases.length + 10,
																									 case_status
																								 }
																							 })
			this.result.data=result.data.cases_by_status
		} catch (error) {
			this.result.error=error
		}
	})

	When('I search for the case by id', async function () {
		try {
			let result      =await this.client.query({
																								 query    : gql`query case_by_id($id: ID!) {case_by_id(id: $id){id description started_at case_type{id} status{id}}}`,
																								 variables: {
																									 id: this.case.id
																								 }
																							 })
			this.result.data=result.data.case_by_id
		} catch (error) {
			this.result.error=error
		}
	})

	When('I save the case', async function () {
		try {
			let inputCase={
				description        : this.case.description,
				case_type_id       : this.case.case_type_id,
				case_status_type_id: this.case.case_status_type_id,
				started_at         : this.case.started_at
			}
			if (this.case.roles && (this.case.roles.length > 0)) {
				inputCase.roles=this.case.roles

			}
			let result=await this.client.mutate({
																						mutation : gql`mutation case_create($inputCase: InputCase!){ case_create(new_case: $inputCase) {id description started_at case_type {id} status {id} roles{id type{id} party{id} from_date thru_date}}}`,
																						variables: {inputCase}
																					})

			this.result.data=result.data.case_create
		} catch (error) {
			this.result.error=error
		}
	})

	When('I update the case description to {string}', async function (case_description) {
		try {
			this.case.description=case_description
			let update_case      ={
				description        : case_description,
				case_type_id       : this.case.case_type_id,
				case_status_type_id: this.case.case_status_type_id,
				started_at         : this.case.started_at
			}
			let result           =await this.client.mutate({
																											 mutation : gql`mutation case_update($id: ID!, $update_case: InputCase!){ case_update(id: $id, update_case: $update_case) {id description started_at case_type {id} status {id}}}`,
																											 variables: {
																												 update_case,
																												 id: this.case.id
																											 }
																										 })
			this.result.data     =result.data.case_update
		} catch (error) {
			this.result.error=error
		}
	})

	When('I delete the case', async function () {
		try {
			let result=await this.client.mutate({
																						mutation : gql`mutation case_delete($id: ID!){ case_delete(id: $id) }`,
																						variables: {
																							id: this.case.id
																						}
																					})

			this.result.data=result.data.case_delete
		} catch (error) {
			this.result.error=error
		}
	})

	Then('{int} of them are cases of type {string}', async function (number_of_cases, case_type) {
		return this.db.one('select id, description, parent_id from case_type where description = ${case_type}', {case_type})
			.then(data => expect(this.result.data.filter(p => p.case_type.id === data.id).length).to.be.equal(number_of_cases))
	})

	Then('I get {int} cases', function (number_of_cases, callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.be.ok
		expect(this.result.data.length).to.be.equal(number_of_cases)
		callback()
	})

	Then('{int} of them are cases in status {string}', async function (expected_cases, case_status_description) {
		let case_status=await this.db.one('select id, description, parent_id from case_status_type where description = ${case_status_description}', {case_status_description})
		expect(this.result.data.filter(c => c.status.id === case_status.id).length).to.be.equal(expected_cases)
	})

	Then('I get the case back', function (callback) {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		expect(this.result.data.id).to.not.be.null
		callback()
	})

	Then('the case is in the database', async function () {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		let case_id=''
		if (this.case.id && this.case.id !== '') {
			case_id=this.case.id
		} else {
			case_id=this.result.data.id
		}

		let actual_case=await this.db.one('select id, description, started_at, case_type_id, case_status_type_id ' +
																				'from "case" ' +
																				'where id = ${case_id}', {case_id})
		if (this.case.description) {
			expect(actual_case.description).to.be.equal(this.case.description)
		} else {
			expect(actual_case.description).to.not.be.ok
		}
		expect(actual_case.case_type_id).to.be.equal(this.case.case_type_id)
		expect(actual_case.case_status_type_id).to.be.equal(this.case.case_status_type_id)
	})

	Then('the case is not in the database', async function () {
		expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
		expect(this.result.data).to.not.be.null
		let case_id=''
		if (this.case.id && this.case.id !== '') {
			case_id=this.case.id
		} else {
			case_id=this.result.data.id
		}

		try {
			let actual_case=await this.db.one('select id, description, started_at, case_type_id, case_status_type_id ' +
																					'from "case" ' +
																					'where id = ${case_id}', {case_id})
			expect(actual_case).to.be.null
		} catch (error) {
			expect(error.message).to.be.equal('No data returned from the query.')
		}
	})
})
