import {
    buildSchema
} from 'graphql';

import database from "../database";

const organizationDatabase = [{
        id: 'a',
        name: 'alice'
    },
    {
        id: 'b',
        name: 'bob',
        contactMechanisms: {
            email_address_list: [{
                id: 'a',
                address: "chester@tester.com"
            }],
            phone_numbers: [{
                id: 'a',
                phone_number: "666-666-7777"
            }]
        }
    }
];

var schema = buildSchema(`
  type Email {
    id: ID!,
    address: String
  }
  type ContactMechanisms {
    email_address_list: [Email]
  }

  type Person {
    id: ID!,
    first_name: String,
    last_name: String,
    title: String,
    nickname: String,
    date_of_birth: String,
    comment: String,
    contactMechanisms: ContactMechanisms
  }

  type Organization {
    id: ID!,
    name: String,
    contactMechanisms: ContactMechanisms
  }
  type Query {
    person(id: ID!) : Person
    organization(id: ID!) : Organization
  }
`);

var root = {
    person: function({
        id
    }) {
        return database.one('select id, first_name, last_name, title, nickname, date_of_birth, comment from person where id=$1',[id]);
    },
    organization: function({
        id
    }) {
        return database.one('select id, name from organization where id=$1',[id]);
    }
};

export {
    schema,
    root
};
