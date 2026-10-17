import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AStackTests {

    @Test
    void emptyStackIsEmpty() {
        AStack s = AStack.emptyStack();
        assertEquals(0, s.size());
        assertTrue(s.isEmpty());
    }

    @Test
    void emptyStackEqualsAnotherEmptyStack() {
        assertEquals(AStack.emptyStack(), AStack.emptyStack());
        assertEquals(new AStack(new String[3], 0), AStack.emptyStack());
    }

    @Test
    void pushOntoEmpty() {
        AStack s = AStack.emptyStack();
        s.push("a");
        assertEquals(1, s.size());
        assertEquals("a", s.peek());
    }

    @Test
    void pushSeveralKeepsLastOnTop() {
        AStack s = AStack.emptyStack();
        s.push("a");
        s.push("b");
        s.push("c");
        assertEquals(3, s.size());
        assertEquals("c", s.peek());
        assertEquals(new AStack(new String[] {"a", "b", "c"}, 3), s);
    }

    @Test
    void pushBeyondInitialCapacityGrowsArray() {
        AStack s = AStack.emptyStack();
        for (int i = 0; i < 100; i++) {
            s.push("s" + i);
        }
        assertEquals(100, s.size());
        assertEquals("s99", s.peek());
        for (int i = 99; i >= 0; i--) {
            assertEquals("s" + i, s.pop());
        }
        assertTrue(s.isEmpty());
    }

    @Test
    void pushOntoZeroCapacityArrayGrows() {
        AStack s = new AStack(new String[0], 0);
        s.push("a");
        s.push("b");
        assertEquals("b", s.peek());
        assertEquals(2, s.size());
    }

    @Test
    void popReturnsTopAndShrinks() {
        AStack s = AStack.emptyStack();
        s.push("a");
        s.push("b");
        assertEquals("b", s.pop());
        assertEquals(1, s.size());
        assertEquals("a", s.peek());
    }

    @Test
    void popLastElementLeavesEmpty() {
        AStack s = AStack.emptyStack();
        s.push("only");
        assertEquals("only", s.pop());
        assertTrue(s.isEmpty());
        assertEquals(AStack.emptyStack(), s);
    }

    @Test
    void popOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> AStack.emptyStack().pop());
    }

    @Test
    void popTooManyTimesThrows() {
        AStack s = AStack.emptyStack();
        s.push("a");
        s.pop();
        assertThrows(NoSuchElementException.class, s::pop);
    }

    @Test
    void peekDoesNotRemove() {
        AStack s = AStack.emptyStack();
        s.push("a");
        s.push("b");
        assertEquals("b", s.peek());
        assertEquals("b", s.peek());
        assertEquals(2, s.size());
    }

    @Test
    void peekAfterPopSeesNextElement() {
        AStack s = AStack.emptyStack();
        s.push("a");
        s.push("b");
        s.pop();
        assertEquals("a", s.peek());
    }

    @Test
    void peekOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> AStack.emptyStack().peek());
    }

    @Test
    void peekAfterEmptiedThrows() {
        AStack s = AStack.emptyStack();
        s.push("a");
        s.pop();
        assertThrows(NoSuchElementException.class, s::peek);
    }

    @Test
    void sizeTracksPushesAndPops() {
        AStack s = AStack.emptyStack();
        assertEquals(0, s.size());
        s.push("a");
        s.push("b");
        assertEquals(2, s.size());
        s.pop();
        assertEquals(1, s.size());
    }

    @Test
    void sizeUnchangedByPeekAndFailedPop() {
        AStack s = AStack.emptyStack();
        assertThrows(NoSuchElementException.class, s::pop);
        assertEquals(0, s.size());
        s.push("a");
        s.peek();
        assertEquals(1, s.size());
    }

    @Test
    void isEmptyTrueForNewStack() {
        assertTrue(AStack.emptyStack().isEmpty());
    }

    @Test
    void isEmptyFalseAfterPushTrueAfterPopAll() {
        AStack s = AStack.emptyStack();
        s.push("a");
        assertFalse(s.isEmpty());
        s.pop();
        assertTrue(s.isEmpty());
    }

    @Test
    void equalsSameContentsDifferentCapacity() {
        AStack a = AStack.emptyStack();
        AStack b = new AStack(new String[50], 0);
        a.push("x");
        a.push("y");
        b.push("x");
        b.push("y");
        assertEquals(a, b);
    }

    @Test
    void notEqualsDifferentOrderOrSize() {
        AStack a = AStack.emptyStack();
        AStack b = AStack.emptyStack();
        a.push("x");
        a.push("y");
        b.push("y");
        b.push("x");
        assertNotEquals(a, b);
        b.pop();
        assertNotEquals(a, b);
    }
}