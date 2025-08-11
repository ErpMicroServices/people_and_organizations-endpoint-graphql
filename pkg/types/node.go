package types

// Node interface for GraphQL Node interface
type Node interface {
	IsNode()
	GetID() string
}

// BaseNode provides a basic implementation of Node
type BaseNode struct {
	ID string `json:"id"`
}

func (n BaseNode) IsNode() {}

func (n BaseNode) GetID() string {
	return n.ID
}
