import java.util.NoSuchElementException;
import java.util.Objects;


public class AQueue {

    private String[] data;
    private int head;
    private int size;

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: make an AQueue from an array, a head index and a size.
    Header: AQueue(String[] data, int head, int size)
    Example: new AQueue(new String[4], 0, 0) is an empty queue.
    Template: ... data ... head ... size ...
    */
    public AQueue(String[] data, int head, int size) {
        this.data = data;
        this.head = head;
        this.size = size;
    }

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: produce an empty queue that holds capacity elements.
    Header: static AQueue emptyQueue(int capacity)
    Example: emptyQueue(5).size() is 0.
    Template: ... capacity ...
    */
    public static AQueue emptyQueue(int capacity) {
        return new AQueue(new String[capacity], 0, 0);
    }

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: add s to the back; throw IllegalStateException if full.
    Header: void enqueue(String s)
    Example: enqueueing "a" then "b" makes "a" the front.
    Template: ... s ... this.data.length ... this.head ... this.size ...
    */
    public void enqueue(String s) {
        if (this.size == this.data.length) {
            throw new IllegalStateException("enqueue on full queue");
        }
        this.data[(this.head + this.size) % this.data.length] = s;
        this.size++;
    }

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: remove and return the front; throw NoSuchElementException if empty.
    Header: String dequeue()
    Example: dequeueing a queue of "a","b" returns "a".
    Template: ... this.size == 0 ... this.data[this.head] ...
    */
    public String dequeue() {
        if (this.size == 0) {
            throw new NoSuchElementException("dequeue on empty queue");
        }
        String result = this.data[this.head];
        this.data[this.head] = null;
        this.head = (this.head + 1) % this.data.length;
        this.size--;
        return result;
    }

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: return the front without removing it; throw NoSuchElementException if empty.
    Header: String peek()
    Example: peeking a queue of "a","b" returns "a".
    Template: ... this.size == 0 ... this.data[this.head] ...
    */
    public String peek() {
        if (this.size == 0) {
            throw new NoSuchElementException("peek on empty queue");
        }
        return this.data[this.head];
    }

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: return the number of elements.
    Header: int size()
    Example: a queue holding "a" and "b" has size 2.
    Template: ... this.size ...
    */
    public int size() {
        return this.size;
    }

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: return true when the queue has no elements.
    Header: boolean isEmpty()
    Example: an empty queue is empty, a queue holding "a" is not.
    Template: ... this.size == 0 ...
    */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /*
    Data definition: AQueue is a data array (String[]), a head index (int) and a size (int).
    Purpose: true when o is an AQueue with the same capacity and elements in the same order.
    Header: boolean equals(Object o)
    Example: "a","b" equals "a","b" but not "b","a".
    Template: ... other.size ... other.data.length ... this.data[(this.head + i) % this.data.length] ...
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AQueue)) {
            return false;
        }
        AQueue other = (AQueue) o;
        if (this.size != other.size || this.data.length != other.data.length) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            String a = this.data[(this.head + i) % this.data.length];
            String b = other.data[(other.head + i) % other.data.length];
            if (!Objects.equals(a, b)) {
                return false;
            }
        }
        return true;
    }
}