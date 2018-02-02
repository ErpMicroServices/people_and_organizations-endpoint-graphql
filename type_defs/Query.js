export default `
type Query {
	
	organizations( start: Int!, records: Int!) : [Organization]!
	parties( start: Int!, records: Int!) : [Party]!
	people(start: Int!, records: Int!) : [Person]!
}
`;