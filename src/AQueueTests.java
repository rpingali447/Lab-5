import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AQueueTests {

    @Test
    void emptyQueueIsEmpty() {
        AQueue q = AQueue.emptyQueue(5);
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }

    @Test
    void emptyQueueEqualsAnotherEmptyQueueOfSameCapacity() {
        assertEquals(AQueue.emptyQueue(4), AQueue.emptyQueue(4));
        assertEquals(new AQueue(new String[4], 0, 0), AQueue.emptyQueue(4));
    }

    @Test
    void enqueueOntoEmpty() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        assertEquals(1, q.size());
        assertEquals("a", q.peek());
    }

    @Test
    void enqueueKeepsFirstAtFront() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        assertEquals(3, q.size());
        assertEquals("a", q.peek());
        assertEquals(new AQueue(new String[] {"a", "b", "c"}, 0, 3), q);
    }

    @Test
    void enqueueOnFullQueueThrows() {
        AQueue q = AQueue.emptyQueue(2);
        q.enqueue("a");
        q.enqueue("b");
        assertThrows(IllegalStateException.class, () -> q.enqueue("c"));
        assertEquals(2, q.size());
    }

    @Test
    void enqueueOnZeroCapacityQueueThrows() {
        AQueue q = AQueue.emptyQueue(0);
        assertThrows(IllegalStateException.class, () -> q.enqueue("a"));
    }

    @Test
    void enqueueWrapsAroundAfterDequeues() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.dequeue();
        q.dequeue();
        q.enqueue("d"); // wraps to index 0
        q.enqueue("e"); // wraps to index 1
        assertEquals(3, q.size());
        assertEquals("c", q.dequeue());
        assertEquals("d", q.dequeue());
        assertEquals("e", q.dequeue());
    }

    @Test
    void dequeueIsFirstInFirstOut() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        assertEquals("a", q.dequeue());
        assertEquals("b", q.dequeue());
        assertEquals("c", q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void dequeueFromFullThenEnqueueAgain() {
        AQueue q = AQueue.emptyQueue(2);
        q.enqueue("a");
        q.enqueue("b");
        assertEquals("a", q.dequeue());
        q.enqueue("c");
        assertEquals("b", q.dequeue());
        assertEquals("c", q.dequeue());
    }

    @Test
    void dequeueOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> AQueue.emptyQueue(3).dequeue());
    }

    @Test
    void dequeueTooManyTimesThrows() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        q.dequeue();
        assertThrows(NoSuchElementException.class, q::dequeue);
    }

    @Test
    void peekDoesNotRemove() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        q.enqueue("b");
        assertEquals("a", q.peek());
        assertEquals("a", q.peek());
        assertEquals(2, q.size());
    }

    @Test
    void peekAfterDequeueSeesNextElement() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        q.enqueue("b");
        q.dequeue();
        assertEquals("b", q.peek());
    }

    @Test
    void peekOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> AQueue.emptyQueue(3).peek());
    }

    @Test
    void peekAfterEmptiedThrows() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        q.dequeue();
        assertThrows(NoSuchElementException.class, q::peek);
    }

    @Test
    void sizeTracksEnqueuesAndDequeues() {
        AQueue q = AQueue.emptyQueue(5);
        assertEquals(0, q.size());
        q.enqueue("a");
        q.enqueue("b");
        assertEquals(2, q.size());
        q.dequeue();
        assertEquals(1, q.size());
    }

    @Test
    void sizeUnchangedByPeekAndFailedOperations() {
        AQueue q = AQueue.emptyQueue(1);
        assertThrows(NoSuchElementException.class, q::dequeue);
        assertEquals(0, q.size());
        q.enqueue("a");
        q.peek();
        assertThrows(IllegalStateException.class, () -> q.enqueue("b"));
        assertEquals(1, q.size());
    }

    @Test
    void isEmptyTrueForNewQueue() {
        assertTrue(AQueue.emptyQueue(3).isEmpty());
    }

    @Test
    void isEmptyFalseAfterEnqueueTrueAfterDequeueAll() {
        AQueue q = AQueue.emptyQueue(3);
        q.enqueue("a");
        assertFalse(q.isEmpty());
        q.dequeue();
        assertTrue(q.isEmpty());
    }

    @Test
    void equalsSameContentsEvenIfHeadDiffers() {
        AQueue a = AQueue.emptyQueue(3);
        AQueue b = AQueue.emptyQueue(3);
        a.enqueue("x");
        a.enqueue("y");
        b.enqueue("w");
        b.dequeue(); // moves b's head forward
        b.enqueue("x");
        b.enqueue("y");
        assertEquals(a, b);
    }

    @Test
    void notEqualsDifferentOrderSizeOrCapacity() {
        AQueue a = AQueue.emptyQueue(3);
        AQueue b = AQueue.emptyQueue(3);
        a.enqueue("x");
        a.enqueue("y");
        b.enqueue("y");
        b.enqueue("x");
        assertNotEquals(a, b);
        b.dequeue();
        assertNotEquals(a, b);
        assertNotEquals(AQueue.emptyQueue(3), AQueue.emptyQueue(4));
    }

    @Test
    void handlesMillionsOfElements() {
        int n = 3_000_000;
        AQueue q = AQueue.emptyQueue(n);
        for (int i = 0; i < n; i++) {
            q.enqueue("x");
        }
        assertEquals(n, q.size());
        for (int i = 0; i < n; i++) {
            q.dequeue();
        }
        assertTrue(q.isEmpty());
    }
}