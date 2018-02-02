export default `
type Query {
	
	organizations( start: Int!, records: Int!) : [Organization]!
	people(start: Int!, records: Int!) : [Person]!
}
`;