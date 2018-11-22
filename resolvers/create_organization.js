export default function (obj, args, context, graphql) {
	let org           = args
	org.party_type_id = context.party_types.get('Organization').id
	return context.database
		.one('insert into party (name, government_id, comment, party_type_id) values(${name}, ${government_id}, ${comment}, ${party_type_id}) returning id', org)
}