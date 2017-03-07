import {
    buildSchema
} from 'graphql';

import moment from "moment";

import database from "../database";

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
    people : [Person]
    person(id: ID!) : Person
    organization(id: ID!) : Organization
  }

  type Mutation {
    create_person(first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, comment: String): Person
  }
`);

var root = {
    people: () => {
        return database.any('select id, first_name, last_name, title, nickname, date_of_birth, comment from person');
    },
    person: function({
        id
    }) {
        return database.one('select id, first_name, last_name, title, nickname, date_of_birth, comment from person where id=$1', [id]);
    },
    organization: function({
        id
    }) {
        return database.one('select id, name from organization where id=$1', [id]);
    },
    create_person: function({
        first_name,
        last_name,
        title,
        nickname,
        date_of_birth,
        comment
    }) {
        return database.one("insert into person (first_name, last_name, title, nickname, date_of_birth, comment) values($1, $2, $3, $4, $5, $6) returning id", [first_name, last_name, title, nickname, date_of_birth, comment])
    }
};

export {
    schema,
    root
};
