import gql from 'graphql-tag'

var {
	    defineSupportCode
    } = require('cucumber')

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {

	Given('I have provided an organization name of {string}', function (organization_name, callback) {
		this.party.name = organization_name
		callback()
	})

	Given('I have provided a government id of {string}', function (government_id, callback) {
		this.party.government_id = government_id
		callback()
	})

	Given('the organization is in the database', function () {
		this.party.party_type_id = this.party_type_id("Person")
		return this.db.one("insert into party (name, government_id, comment, party_type_id) values(${name}, ${government_id}, ${comment}, ${party_type_id}) returning id", this.party)
			.then((data) => this.party.id = data.id)
	})

	When('I delete the organization', function () {
		return this.client
		.mutate({
			mutation : gql`mutation delete_organization($id: ID!) {delete_organization(id: $id)}`,
			variables: {
				"id": this.party.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the organization is no longer in the database', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data.data).to.exist
		return this.db.one("select id from party where id=$1", [this.party.id])
			.then((data) => expect(data).to.not.exist)
			.catch((error) => expect(error.message).to.be.equal("No data returned from the query."))
	})
})
