import {
    buildSchema
} from 'graphql';

import moment from "moment";

import database from "../database";

let contact_mechanism_types = [];
let party_types = [];

database.any("select id, description from party_type order by description")
    .then((data) => party_types = data)
    .then(() => database.any("select id, description from contact_mechanism_type order by description"))
    .then((data) => contact_mechanism_types = data);

const email_id = () => contact_mechanism_types.find((cm) => cm.description === 'Email Address').id;
const party_type_id = (party_type) => party_types.find(pt => pt.description === party_type).id;
const person_type_id = () => party_type_id("Person");

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
    create_person(first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, comment: String, email: String): Person
    update_person(id: ID!,first_name: String, last_name: String, title: String, nickname: String, date_of_birth: String, comment: String): Person
    delete_person(id: ID!): String
  }
`);

var root = {
    create_person: function({
        first_name,
        last_name,
        title,
        nickname,
        date_of_birth,
        comment,
        email
    }) {
        return database
            .one("insert into party (first_name, last_name, title, nickname, date_of_birth, comment, party_type_id) values($1, $2, $3, $4, $5, $6, $7) returning id",
                [first_name, last_name, title, nickname, date_of_birth || null, comment, person_type_id()])
            .then((party_id) => {
                if (email) {
                    return database
                        .one("INSERT INTO contact_mechanism(end_point, contact_mechanism_type_id) VALUES ( $1, $2) returning id", [email, email_id()])
                        .then((data) => database.one("INSERT INTO party_contact_mechanism(party_id, contact_mechanism_id)VALUES ( $1, $2) returning party_id as id", [party_id.id, data.id]));
                } else {
                  return party_id;
                }
            })
    },
    delete_person: function({
        id
    }) {
        return database.none("delete from party where id = $1", [id]);
    },
    organization: function({
        id
    }) {
        return database.one('select id, name from organization where id=$1', [id]);
    },
    people: () => {
        return database.any('select id, first_name, last_name, title, nickname, date_of_birth, comment from party where party_type_id=$1', [person_type_id()]);
    },
    person: function({
        id
    }) {
        return database.one('select id, first_name, last_name, title, nickname, date_of_birth, comment from party where id=$1', [id]);
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
            .one("update party set first_name=$1, last_name=$2, title=$3, nickname=$4, date_of_birth=$5, comment=$6 where id=$7 returning id, first_name, last_name, title, nickname, date_of_birth, comment", [first_name, last_name, title, nickname, date_of_birth, comment, id]);
    }

};

export {
    schema,
    root
};
