import gql from 'graphql-tag';

var {
	    defineSupportCode
    } = require('cucumber');

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {

	Given('a party type with a description of {string} has been saved to the database', function (string) {
		return this.db.one("insert into party_type (description) values ($1) returning id", [string])
				.then(data => this.party_type.id = data.id);

	});


	When('I add a child party type with a description of {string}', function (string) {
		this.child_party_type_description = string;
		return this.client
				.mutate({
					mutation : gql `mutation add_party_type_child( $description: String!, $parent_id: ID!) { add_party_type_child(description: $description, parent_id: $parent_id) }`,
					variables: {
						description: string,
						parent_id  : this.party_type.id
					}
				})
				.then(results => this.result.data = results)
				.catch(error => this.result.error = error);
	});

	Then('I can find the parent of the child', function () {
		expect(this.result.error).to.be.null;
		expect(this.result.data).to.not.be.null;
		expect(this.result.data.data).to.not.be.null;
		expect(this.result.data.data.id).to.not.be.null;
		return this.db.one("select id, description, parent_id from party_type where id = ${add_party_type_child}", this.result.data.data)
				.then(data => {
					expect(data.parent_id).to.be.equal(this.party_type.id);
					expect(data.description).to.be.equal(this.child_party_type_description);
				});
	});

});
