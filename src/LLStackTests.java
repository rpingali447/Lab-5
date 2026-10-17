import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LLStackTests {

    @Test
    void emptyStackIsEmpty() {
        LLStack s = LLStack.emptyStack();
        assertEquals(0, s.size());
        assertTrue(s.isEmpty());
    }

    @Test
    void emptyStackEqualsAnotherEmptyStack() {
        assertEquals(LLStack.emptyStack(), LLStack.emptyStack());
        assertEquals(new LLStack(null, 0), LLStack.emptyStack());
    }

    @Test
    void pushOntoEmpty() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        assertEquals(1, s.size());
        assertEquals("a", s.peek());
    }

    @Test
    void pushSeveralKeepsLastOnTop() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        s.push("b");
        s.push("c");
        assertEquals(3, s.size());
        assertEquals("c", s.peek());
        LLStack expected = new LLStack(
                new LLStack.Node("c", new LLStack.Node("b", new LLStack.Node("a", null))), 3);
        assertEquals(expected, s);
    }

    @Test
    void popReturnsTopAndShrinks() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        s.push("b");
        assertEquals("b", s.pop());
        assertEquals(1, s.size());
        assertEquals("a", s.peek());
    }

    @Test
    void popLastElementLeavesEmpty() {
        LLStack s = LLStack.emptyStack();
        s.push("only");
        assertEquals("only", s.pop());
        assertTrue(s.isEmpty());
        assertEquals(LLStack.emptyStack(), s);
    }

    @Test
    void popOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> LLStack.emptyStack().pop());
    }

    @Test
    void popTooManyTimesThrows() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        s.pop();
        assertThrows(NoSuchElementException.class, s::pop);
    }

    @Test
    void peekDoesNotRemove() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        s.push("b");
        assertEquals("b", s.peek());
        assertEquals("b", s.peek());
        assertEquals(2, s.size());
    }

    @Test
    void peekAfterPopSeesNextElement() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        s.push("b");
        s.pop();
        assertEquals("a", s.peek());
    }

    @Test
    void peekOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> LLStack.emptyStack().peek());
    }

    @Test
    void peekAfterEmptiedThrows() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        s.pop();
        assertThrows(NoSuchElementException.class, s::peek);
    }

    @Test
    void sizeTracksPushesAndPops() {
        LLStack s = LLStack.emptyStack();
        assertEquals(0, s.size());
        s.push("a");
        s.push("b");
        assertEquals(2, s.size());
        s.pop();
        assertEquals(1, s.size());
    }

    @Test
    void sizeUnchangedByPeekAndFailedPop() {
        LLStack s = LLStack.emptyStack();
        assertThrows(NoSuchElementException.class, s::pop);
        assertEquals(0, s.size());
        s.push("a");
        s.peek();
        assertEquals(1, s.size());
    }

    @Test
    void isEmptyTrueForNewStack() {
        assertTrue(LLStack.emptyStack().isEmpty());
    }

    @Test
    void isEmptyFalseAfterPushTrueAfterPopAll() {
        LLStack s = LLStack.emptyStack();
        s.push("a");
        assertFalse(s.isEmpty());
        s.pop();
        assertTrue(s.isEmpty());
    }

    @Test
    void equalsSameContents() {
        LLStack a = LLStack.emptyStack();
        LLStack b = LLStack.emptyStack();
        a.push("x");
        a.push("y");
        b.push("x");
        b.push("y");
        assertEquals(a, b);
    }

    @Test
    void notEqualsDifferentOrderOrSize() {
        LLStack a = LLStack.emptyStack();
        LLStack b = LLStack.emptyStack();
        a.push("x");
        a.push("y");
        b.push("y");
        b.push("x");
        assertNotEquals(a, b);
        b.pop();
        assertNotEquals(a, b);
    }
}