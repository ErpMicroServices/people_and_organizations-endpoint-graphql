import {
    buildSchema
} from 'graphql';

// Maps id to User object
const personDatabase = [{
        id: 'a',
        first_name: 'alice',
        last_name: 'alice',
    },
    {
        id: 'b',
        first_name: 'bob',
        last_name: 'bob',
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
        return personDatabase.find((item) => item.id === id);
    },
    organization: function({
        id
    }) {
        return organizationDatabase.find((item) => item.id === id);
    }
};

export {
    schema,
    root
};
