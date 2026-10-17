import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;


public class AStack {

    private String[] data;
    private int size;

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: make an AStack from an array and a size.
    Header: AStack(String[] data, int size)
    Example: new AStack(new String[3], 0) is an empty stack.
    Template: ... data ... size ...
    */
    public AStack(String[] data, int size) {
        this.data = data;
        this.size = size;
    }

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: produce an empty stack.
    Header: static AStack emptyStack()
    Example: emptyStack().size() is 0.
    Template: nothing to take apart.
    */
    public static AStack emptyStack() {
        return new AStack(new String[10], 0);
    }

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: push s onto the top, doubling the array when it is full.
    Header: void push(String s)
    Example: pushing "a" on an empty stack makes "a" the top.
    Template: ... s ... this.data.length ... this.size ...
    */
    public void push(String s) {
        if (this.size == this.data.length) {
            this.data = Arrays.copyOf(this.data, Math.max(1, this.data.length * 2));
        }
        this.data[this.size] = s;
        this.size++;
    }

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: remove and return the top; throw NoSuchElementException if empty.
    Header: String pop()
    Example: popping a stack with "b" on top of "a" returns "b".
    Template: ... this.size == 0 ... this.data[this.size - 1] ...
    */
    public String pop() {
        if (this.size == 0) {
            throw new NoSuchElementException("pop on empty stack");
        }
        this.size--;
        String result = this.data[this.size];
        this.data[this.size] = null; // don't hold on to removed element
        return result;
    }

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: return the top without removing it; throw NoSuchElementException if empty.
    Header: String peek()
    Example: peeking a stack with "b" on top of "a" returns "b".
    Template: ... this.size == 0 ... this.data[this.size - 1] ...
    */
    public String peek() {
        if (this.size == 0) {
            throw new NoSuchElementException("peek on empty stack");
        }
        return this.data[this.size - 1];
    }

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: return the number of elements.
    Header: int size()
    Example: a stack holding "a" and "b" has size 2.
    Template: ... this.size ...
    */
    public int size() {
        return this.size;
    }

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: return true when the stack has no elements.
    Header: boolean isEmpty()
    Example: an empty stack is empty, a stack holding "a" is not.
    Template: ... this.size == 0 ...
    */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /*
    Data definition: AStack is a data array (String[]) and a size (int).
    Purpose: true when o is an AStack with the same elements in the same order.
    Header: boolean equals(Object o)
    Example: "a","b" equals "a","b" but not "b","a".
    Template: ... other.size ... this.data[i] ... other.data[i] ...
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AStack)) {
            return false;
        }
        AStack other = (AStack) o;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (!Objects.equals(this.data[i], other.data[i])) {
                return false;
            }
        }
        return true;
    }
}