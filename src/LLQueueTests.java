import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LLQueueTests {

    @Test
    void emptyQueueIsEmpty() {
        LLQueue q = LLQueue.emptyQueue();
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }

    @Test
    void emptyQueueEqualsAnotherEmptyQueue() {
        assertEquals(LLQueue.emptyQueue(), LLQueue.emptyQueue());
        assertEquals(new LLQueue(null, null, 0), LLQueue.emptyQueue());
    }

    @Test
    void enqueueOntoEmpty() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        assertEquals(1, q.size());
        assertEquals("a", q.peek());
    }

    @Test
    void enqueueKeepsFirstAtFront() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        assertEquals(3, q.size());
        assertEquals("a", q.peek());
        LLQueue expected = new LLQueue(new LLQueue.Node("a", new LLQueue.Node("b", new LLQueue.Node("c", null))),
                null, 3);
        assertEquals(expected, q);
    }

    @Test
    void dequeueIsFirstInFirstOut() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        assertEquals("a", q.dequeue());
        assertEquals("b", q.dequeue());
        assertEquals("c", q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void dequeueInterleavedWithEnqueue() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        q.enqueue("b");
        assertEquals("a", q.dequeue());
        q.enqueue("c");
        assertEquals("b", q.dequeue());
        assertEquals("c", q.dequeue());
        assertEquals(0, q.size());
    }

    @Test
    void dequeueOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> LLQueue.emptyQueue().dequeue());
    }

    @Test
    void dequeueTooManyTimesThrows() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        q.dequeue();
        assertThrows(NoSuchElementException.class, q::dequeue);
    }

    @Test
    void peekDoesNotRemove() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        q.enqueue("b");
        assertEquals("a", q.peek());
        assertEquals("a", q.peek());
        assertEquals(2, q.size());
    }

    @Test
    void peekAfterDequeueSeesNextElement() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        q.enqueue("b");
        q.dequeue();
        assertEquals("b", q.peek());
    }

    @Test
    void peekOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> LLQueue.emptyQueue().peek());
    }

    @Test
    void peekAfterEmptiedThrows() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        q.dequeue();
        assertThrows(NoSuchElementException.class, q::peek);
    }

    @Test
    void sizeTracksEnqueuesAndDequeues() {
        LLQueue q = LLQueue.emptyQueue();
        assertEquals(0, q.size());
        q.enqueue("a");
        q.enqueue("b");
        assertEquals(2, q.size());
        q.dequeue();
        assertEquals(1, q.size());
    }

    @Test
    void sizeUnchangedByPeekAndFailedDequeue() {
        LLQueue q = LLQueue.emptyQueue();
        assertThrows(NoSuchElementException.class, q::dequeue);
        assertEquals(0, q.size());
        q.enqueue("a");
        q.peek();
        assertEquals(1, q.size());
    }

    @Test
    void isEmptyTrueForNewQueue() {
        assertTrue(LLQueue.emptyQueue().isEmpty());
    }

    @Test
    void isEmptyFalseAfterEnqueueTrueAfterDequeueAll() {
        LLQueue q = LLQueue.emptyQueue();
        q.enqueue("a");
        assertFalse(q.isEmpty());
        q.dequeue();
        assertTrue(q.isEmpty());
    }

    @Test
    void equalsSameContentsEvenIfSplitDifferently() {
        LLQueue a = LLQueue.emptyQueue();
        LLQueue b = LLQueue.emptyQueue();
        a.enqueue("x");
        a.enqueue("y");
        a.enqueue("z");
        b.enqueue("x");
        b.enqueue("y");
        b.enqueue("z");
        b.peek(); // forces the reversal, moving everything into front
        assertEquals(a, b);
    }

    @Test
    void notEqualsDifferentOrderOrSize() {
        LLQueue a = LLQueue.emptyQueue();
        LLQueue b = LLQueue.emptyQueue();
        a.enqueue("x");
        a.enqueue("y");
        b.enqueue("y");
        b.enqueue("x");
        assertNotEquals(a, b);
        b.dequeue();
        assertNotEquals(a, b);
    }

    @Test
    void handlesMillionsOfElements() {
        LLQueue q = LLQueue.emptyQueue();
        int n = 3_000_000;
        for (int i = 0; i < n; i++) {
            q.enqueue("x");
        }
        assertEquals(n, q.size());
        assertEquals("x", q.dequeue()); // triggers reversal of 3 million nodes
        assertEquals(n - 1, q.size());
        for (int i = 1; i < n; i++) {
            q.dequeue();
        }
        assertTrue(q.isEmpty());
    }
}