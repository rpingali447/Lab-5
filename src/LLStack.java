import java.util.NoSuchElementException;
import java.util.Objects;

public class LLStack {

    /*
    Data definition: Node is a first (String) and a rest (Node or null).
    Example: Node("a", null) is a list holding "a".
    Template: ... this.first ... this.rest ...
    */
    static class Node {
        String first;
        Node rest;

        /*
        Data definition: Node is a first (String) and a rest (Node or null).
        Purpose: make a Node from a String and the rest of the list.
        Header: Node(String first, Node rest)
        Example: new Node("a", null) holds "a".
        Template: ... first ... rest ...
        */
        Node(String first, Node rest) {
            this.first = first;
            this.rest = rest;
        }
    }

    private Node top;
    private int size;

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: make an LLStack from a top node and a size.
    Header: LLStack(Node top, int size)
    Example: new LLStack(null, 0) is an empty stack.
    Template: ... top ... size ...
    */
    public LLStack(Node top, int size) {
        this.top = top;
        this.size = size;
    }

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: produce an empty stack.
    Header: static LLStack emptyStack()
    Example: emptyStack().size() is 0.
    Template: nothing to take apart.
    */
    public static LLStack emptyStack() {
        return new LLStack(null, 0);
    }

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: push s onto the top of the stack.
    Header: void push(String s)
    Example: pushing "a" on an empty stack makes "a" the top.
    Template: ... s ... this.top ... this.size ...
    */
    public void push(String s) {
        this.top = new Node(s, this.top);
        this.size++;
    }

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: remove and return the top; throw NoSuchElementException if empty.
    Header: String pop()
    Example: popping a stack with "b" on top of "a" returns "b".
    Template: ... this.top == null ... this.top.first ... this.top.rest ...
    */
    public String pop() {
        if (this.top == null) {
            throw new NoSuchElementException("pop on empty stack");
        }
        String result = this.top.first;
        this.top = this.top.rest;
        this.size--;
        return result;
    }

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: return the top without removing it; throw NoSuchElementException if empty.
    Header: String peek()
    Example: peeking a stack with "b" on top of "a" returns "b".
    Template: ... this.top == null ... this.top.first ...
    */
    public String peek() {
        if (this.top == null) {
            throw new NoSuchElementException("peek on empty stack");
        }
        return this.top.first;
    }

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: return the number of elements.
    Header: int size()
    Example: a stack holding "a" and "b" has size 2.
    Template: ... this.size ...
    */
    public int size() {
        return this.size;
    }

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: return true when the stack has no elements.
    Header: boolean isEmpty()
    Example: an empty stack is empty, a stack holding "a" is not.
    Template: ... this.top == null ...
    */
    public boolean isEmpty() {
        return this.top == null;
    }

    /*
    Data definition: LLStack is a top (Node or null) and a size (int).
    Purpose: true when o is an LLStack with the same elements in the same order.
    Header: boolean equals(Object o)
    Example: "a","b" equals "a","b" but not "b","a".
    Template: ... other.size ... other.top ... first ... rest ...
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LLStack)) {
            return false;
        }
        LLStack other = (LLStack) o;
        if (this.size != other.size) {
            return false;
        }
        Node a = this.top;
        Node b = other.top;
        while (a != null && b != null) {
            if (!Objects.equals(a.first, b.first)) {
                return false;
            }
            a = a.rest;
            b = b.rest;
        }
        return a == null && b == null;
    }
}