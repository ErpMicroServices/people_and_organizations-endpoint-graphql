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
    update_person(id: ID!,first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, comment: String): Person
  }
`);

var root = {
    create_person: function({
        first_name,
        last_name,
        title,
        nickname,
        date_of_birth,
        comment
    }) {
        return database.one("insert into person (first_name, last_name, title, nickname, date_of_birth, comment) values($1, $2, $3, $4, $5, $6) returning id", [first_name, last_name, title, nickname, date_of_birth, comment])
    },
    organization: function({
        id
    }) {
        return database.one('select id, name from organization where id=$1', [id]);
    },
    people: () => {
        return database.any('select id, first_name, last_name, title, nickname, date_of_birth, comment from person');
    },
    person: function({
        id
    }) {
        return database.one('select id, first_name, last_name, title, nickname, date_of_birth, comment from person where id=$1', [id]);
    },
    update_person: function({
        id,
        first_name,
        last_name,
        title,
        nickname,
        date_of_birth,
        comment
    }) {
        return database
            .one("update person set first_name=$1, last_name=$2, title=$3, nickname=$4, date_of_birth=$5, comment=$6 where id=$7 returning id, first_name, last_name, title, nickname, date_of_birth, comment", [first_name, last_name, title, nickname, date_of_birth, comment, id]);
    }

};

export {
    schema,
    root
};
