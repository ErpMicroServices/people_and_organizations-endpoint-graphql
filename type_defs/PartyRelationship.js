export default `
type PartyRelationship {
    id: ID!,
    comment: String
    priority: PriorityType!
    relationshipFrom: PartyRole!
    relationshipTo: PartyRole!
    status: StatusType!
    dateTimeRange: DateTimeRange!
 }
`