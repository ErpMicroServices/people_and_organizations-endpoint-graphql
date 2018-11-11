import gql from 'graphql-tag'

var {
	    defineSupportCode
    } = require('cucumber')

defineSupportCode(function ({
	                            Given,
	                            When,
	                            Then
                            }) {
	When('I save the party', function () {
		return this.client
		.mutate({
			mutation : gql`mutation create_person(
      $first_name: String
      $last_name: String
      $title: String
      $nickname: String
      $date_of_birth: String
      $comment: String
      $email: String
      ) {
        create_person(
          first_name: $first_name
          last_name: $last_name
          title: $title
          nickname: $nickname
          date_of_birth: $date_of_birth
          comment: $comment
          email: $email
        ) {
          id
        }
      }`,
			variables: {
				"first_name"   : this.party.first_name,
				"last_name"    : this.party.last_name,
				"title"        : this.party.title,
				"nickname"     : this.party.nickname,
				"date_of_birth": this.party.date_of_birth ? this.party.date_of_birth.toJSON() : null,
				"comment"      : this.party.comment,
				"email"        : this.party.email
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)

	})

	Then('the party data will be in the database', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data.data).to.exist
		expect(this.result.data.data.create_person).to.exist
		expect(this.result.data.data.create_person.id).to.exist
		return this.db.one("select id, first_name, last_name, title, nickname, date_of_birth, comment from party where id=$1", [this.result.data.data.create_person.id])
			.then(data => {
				expect(data.id).to.be.equal(this.result.data.data.create_person.id)
				expect(data.first_name).to.be.equal(this.party.first_name)
				expect(data.last_name).to.be.equal(this.party.last_name)
				expect(data.title).to.be.equal(this.party.title)
				expect(data.nickname).to.be.equal(this.party.nickname)
				if (data.date_of_birth && this.party.date_of_birth) {
					expect(data.date_of_birth.toJSON()).to.be.equal(this.party.date_of_birth.toJSON())
				}
				expect(data.comment).to.be.equal(this.party.comment)
			})

	})

	Given('the party is in the database', function () {
		return this.db.one("insert into party (first_name, last_name, title, nickname, date_of_birth, comment, party_type_id) values($1, $2, $3, $4, $5, $6, $7) returning id",
			[this.party.first_name, this.party.last_name, this.party.title, this.party.nickname, this.party.date_of_birth, this.party.comment, this.party_type_id("Person")])
			.then((data) => this.party.id = data.id)
	})

	When('I search by the party\'s id', function () {
		return this.client
		.query({
			query    : gql`query person_by_id( $id: ID!) {
        person_by_id(id: $id) {
          first_name,
          last_name,
          title,
          nickname,
          date_of_birth,
          comment
        }
      }`,
			variables: {
				"id": this.party.id
			}
		})

		.then((response) => this.result.data = response)
		.catch(error => this.result.error = error)
	})

	Then('I find the party', function (callback) {
		expect(this.result.error).to.be.null
		expect(this.result.data.data).to.exist
		expect(this.result.data.data.person_by_id).to.exist
		let data = this.result.data.data.person_by_id
		expect(data.first_name).to.be.equal(this.party.first_name)
		expect(data.last_name).to.be.equal(this.party.last_name)
		expect(data.title).to.be.equal(this.party.title)
		expect(data.nickname).to.be.equal(this.party.nickname)
		let expected_date = moment(this.party.date_of_birth, "MM/DD/YYYY")
		let actual_date   = moment(data.date_of_birth, "YYYY-MM-DD")
		expect(actual_date.toJSON()).to.be.equal(expected_date.toJSON())
		expect(data.comment).to.be.equal(this.party.comment)
		callback()
	})

	When('I delete the party', function () {
		return this.client
		.mutate({
			mutation : gql`mutation delete_person($id: ID!) {delete_person(id: $id)}`,
			variables: {
				"id": this.party.id
			}
		})
		.then(results => this.result.data = results)
		.catch(error => this.result.error = error)
	})

	Then('the party is no longer in the database', function () {
		expect(this.result.error).to.be.null
		expect(this.result.data.data).to.exist
		return this.db.one("select id from party where id=$1", [this.party.id])
			.then((data) => expect(data).to.not.exist)
			.catch((error) => expect(error.message).to.be.equal("No data returned from the query."))
	})
})


