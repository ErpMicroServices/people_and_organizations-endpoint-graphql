/**
 * Created by JimBarrows on 10/22/19.
 */

import {Given}            from 'cucumber'
import moment             from 'moment'
import CommunicationEvent from '../support/CommunicationEvent'
import ErpType            from '../support/ErpType'

Given('a communication event with a note of {string}', function (note) {
	if (this.communication_event) {
		this.communication_event.note=note
	} else {
		d
		this.communication_event=new CommunicationEvent({note})
	}
})

Given('a communication event is for a relationship between party {int} and party {int}', function (from_party_index, to_party_index, done) {
	if (this.communication_event) {
		this.communication_event.party_relationship_id=this.party_relationship.id
	} else {
		this.communication_event=new CommunicationEvent({party_relationship_id: this.party_relationship.id})
	}
	done()
})

Given('a communication event status is {string}', async function (communication_event_status_description) {
	this.communication_event_status=new ErpType(await this.db.one('select id, description, parent_id from communication_event_status_type where description = ${communication_event_status_description}', {communication_event_status_description}))
	if (this.communication_event) {
		this.communication_event.communication_event_status_type_id=this.communication_event_status.id
	} else {
		this.communication_event=new CommunicationEvent({communication_event_status_type_id: this.communication_event_status.id})
	}
})

Given('a communication event contact mechanism type is {string}', async function (contact_mechanism_type_description) {
	this.contact_mechanism_type=new ErpType(await this.db.one('select id, description, parent_id from contact_mechanism_type where description = ${contact_mechanism_type_description}', {contact_mechanism_type_description}))
	if (this.communication_event) {
		this.communication_event.contact_mechanism_type_id=this.contact_mechanism_type.id
	} else {
		this.communication_event=new CommunicationEvent({contact_mechanism_type_id: this.contact_mechanism_type.id})
	}
})

Given('a communication event has a type of {string}', async function (communication_event_type_description) {
	this.communication_event_type=new ErpType(await this.db.one('select id, description, parent_id from communication_event_type where description = ${communication_event_type_description}', {communication_event_type_description}))
	if (this.communication_event) {
		this.communication_event.communication_event_type_id=this.communication_event_type.id
	} else {
		this.communication_event=new CommunicationEvent({communication_event_type_id: this.communication_event_type.id})
	}

})

Given('the communication event is in the database', async function () {
	this.communication_event_list.push(
		await this.db.one('insert into communication_event(note, contact_mechanism_type_id, party_relationship_id, communication_event_status_type_id, communication_event_type_id, started, ended) values( ${note: , contact_mechanism_type_id: , party_relationship_id: , communication_event_status_type_id: , communication_event_type_id: , started: , ended}) returning id, started, ended, note, contact_mechanism_type_id, party_relationship_id, communication_event_status_type_id, case_id, communication_event_type_id', this.communication_event))
})

Given('communication events:', async function (dataTable) {
	let table=dataTable.rawTable
	for (let row_index in table) {
		const data_row                                   =table[row_index]
		const note                                       =data_row[0]
		const party_1_index                              =data_row[1]
		const party_2_index                              =data_row[2]
		const communication_event_status_type_description=data_row[3]
		const contact_mechanism_type_description         =data_row[4]
		const communication_event_type_description       =data_row[5]
		const started                                    =moment(data_row[6])
		const ended                                      =null
		const party_1                                    =this.parties[party_1_index - 1]
		const party_2                                    =this.parties[party_2_index - 1]
		const party_relationships                        =await this.db.any('select id, from_date, thru_date, comment, from_party_role_id, to_party_role_id, party_relationship_type_id, party_relationship_status_type_id from party_relationship where (from_party_role_id = (select id from party_role where party_id = ${party_1_id})and to_party_role_id = (select id from party_role where party_id = ${party_2_id}))or (to_party_role_id = (select id from party_role where party_id = ${party_1_id}) and from_party_role_id =(select id from party_role where party_id = ${party_2_id}))', {
			party_1_id: party_1.id,
			party_2_id: party_2.id
		})
		this.contact_mechanism_type                      = await this.db.one('select id, description, parent_id from contact_mechanism_type where description=${contact_mechanism_type_description}',
																																				 {contact_mechanism_type_description})
		this.communication_event_type                    = await this.db.one('select id, description, parent_id from communication_event_type where description = ${communication_event_type_description}',
																																				 {communication_event_type_description})
		this.communication_event_status_type             = await this.db.one('select id, description, parent_id from communication_event_status_type where description = ${communication_event_status_type_description}',
																																				 {communication_event_status_type_description})
		this.communication_event                         ={
			note                              : note,
			contact_mechanism_type_id         : this.contact_mechanism_type.id,
			party_relationship_id             : party_relationships[0].id,
			communication_event_status_type_id: this.communication_event_status_type.id,
			communication_event_type_id       : this.communication_event_type.id,
			started,
			ended
		}
		// let sql                                          ='insert into communication_event(note, contact_mechanism_type_id, party_relationship_id, communication_event_status_type_id, communication_event_type_id, started, ended) values(${note}, ${contact_mechanism_type_id}, ${party_relationship_id}, ${communication_event_status_type_id}, ${communication_event_type_id}, ${started}, ${ended}) returning id, started, ended, note, contact_mechanism_type_id, party_relationship_id, communication_event_status_type_id, case_id, communication_event_type_id'
		// this.communication_event = await this.db.one(sql, communication_event)
	}

})

