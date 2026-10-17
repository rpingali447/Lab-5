import java.util.ArrayList;
import java.util.NoSuchElementException;


public class LLQueue {

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

    private Node front;
    private Node rear;
    private int size;

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: make an LLQueue from a front list, a rear list and a size.
    Header: LLQueue(Node front, Node rear, int size)
    Example: new LLQueue(null, null, 0) is an empty queue.
    Template: ... front ... rear ... size ...
    */
    public LLQueue(Node front, Node rear, int size) {
        this.front = front;
        this.rear = rear;
        this.size = size;
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: produce an empty queue.
    Header: static LLQueue emptyQueue()
    Example: emptyQueue().size() is 0.
    Template: nothing to take apart.
    */
    public static LLQueue emptyQueue() {
        return new LLQueue(null, null, 0);
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: add s to the back of the queue.
    Header: void enqueue(String s)
    Example: enqueueing "a" then "b" makes "a" the front.
    Template: ... s ... this.rear ... this.size ...
    */
    public void enqueue(String s) {
        this.rear = new Node(s, this.rear);
        this.size++;
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: if front is empty, reverse rear (with a loop) into front.
    Header: void shiftIfNeeded()
    Example: front empty and rear "c","b","a" becomes front "a","b","c".
    Template: ... this.front == null ... cur.first ... cur.rest ...
    */
    private void shiftIfNeeded() {
        if (this.front == null) {
            Node reversed = null;
            Node cur = this.rear;
            while (cur != null) {
                Node next = cur.rest;
                cur.rest = reversed;
                reversed = cur;
                cur = next;
            }
            this.front = reversed;
            this.rear = null;
        }
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: remove and return the front; throw NoSuchElementException if empty.
    Header: String dequeue()
    Example: dequeueing a queue of "a","b" returns "a".
    Template: ... this.size == 0 ... this.front.first ... this.front.rest ...
    */
    public String dequeue() {
        if (this.size == 0) {
            throw new NoSuchElementException("dequeue on empty queue");
        }
        shiftIfNeeded();
        String result = this.front.first;
        this.front = this.front.rest;
        this.size--;
        return result;
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: return the front without removing it; throw NoSuchElementException if empty.
    Header: String peek()
    Example: peeking a queue of "a","b" returns "a".
    Template: ... this.size == 0 ... this.front.first ...
    */
    public String peek() {
        if (this.size == 0) {
            throw new NoSuchElementException("peek on empty queue");
        }
        shiftIfNeeded();
        return this.front.first;
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: return the number of elements.
    Header: int size()
    Example: a queue holding "a" and "b" has size 2.
    Template: ... this.size ...
    */
    public int size() {
        return this.size;
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: return true when the queue has no elements.
    Header: boolean isEmpty()
    Example: an empty queue is empty, a queue holding "a" is not.
    Template: ... this.size == 0 ...
    */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: the elements of the queue as a list from front to back.
    Header: ArrayList<String> toList()
    Example: front "a","b" and rear "d","c" gives "a","b","c","d".
    Template: ... n.first ... n.rest ... for the nodes of front and rear
    */
    private ArrayList<String> toList() {
        ArrayList<String> result = new ArrayList<>();
        for (Node n = this.front; n != null; n = n.rest) {
            result.add(n.first);
        }
        int start = result.size();
        for (Node n = this.rear; n != null; n = n.rest) {
            result.add(start, n.first); // rear is reversed, so insert in front of the rear part
        }
        return result;
    }

    /*
    Data definition: LLQueue is a front list, a rear list (in reverse order) and a size (int).
    Purpose: true when o is an LLQueue with the same elements in the same order.
    Header: boolean equals(Object o)
    Example: "a","b" equals "a","b" but not "b","a".
    Template: ... other.size ... this.toList() ... other.toList() ...
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LLQueue)) {
            return false;
        }
        LLQueue other = (LLQueue) o;
        return this.size == other.size && this.toList().equals(other.toList());
    }
}