package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.NoSuchElementException;

public class StackTest {
	Stack<String> stringStack;
	
	@BeforeEach
	public void setUp() {
		stringStack = new Stack<>();
	}
	
	// =============== CONSTRUCTOR TESTS ===============
	@Test
	public void constructor_default_creates_stack() {
		Stack<String> stack = new Stack<>();
		assertNotNull(stack);
		// Verify it works by pushing and popping
		stack.push("test");
		assertEquals("test", stack.pop());
	}
	
	@Test
	public void constructor_positive_capacity_creates_stack() {
		Stack<String> stack = new Stack<>(5);
		assertNotNull(stack);
		stack.push("test");
		assertEquals("test", stack.pop());
	}
	
	@Test
	public void constructor_zero_capacity_defaults_to_10() {
		Stack<String> stack = new Stack<>(0);
		// Verify default capacity works by pushing 10+ items
		for (int i = 0; i < 15; i++) {
			stack.push("item" + i);
		}
		// Pop all items to verify all were stored
		for (int i = 14; i >= 0; i--) {
			assertEquals("item" + i, stack.pop());
		}
	}
	
	@Test
	public void constructor_negative_capacity_defaults_to_10() {
		Stack<String> stack = new Stack<>(-5);
		// Verify default capacity works by pushing 10+ items
		for (int i = 0; i < 15; i++) {
			stack.push("item" + i);
		}
		// Pop all items to verify all were stored
		for (int i = 14; i >= 0; i--) {
			assertEquals("item" + i, stack.pop());
		}
	}
	
	// =============== PUSH METHOD TESTS ===============
	@Test
	public void push_single_element() {
		stringStack.push("A");
		assertEquals("A", stringStack.pop());
	}
	
	@Test
	public void push_null_value() {
		// Stack should accept null values (ArrayList allows it)
		stringStack.push(null);
		assertNull(stringStack.pop());
	}
	
	@Test
	public void push_multiple_elements() {
		stringStack.push("A");
		stringStack.push("B");
		stringStack.push("C");
		
		assertEquals("C", stringStack.pop());
		assertEquals("B", stringStack.pop());
		assertEquals("A", stringStack.pop());
	}
	
	@Test
	public void push_many_elements_exceeds_initial_capacity() {
		// Push more than default capacity (10)
		for (int i = 0; i < 20; i++) {
			stringStack.push("item" + i);
		}
		
		// Verify all elements are present (LIFO order)
		for (int i = 19; i >= 0; i--) {
			assertEquals("item" + i, stringStack.pop());
		}
	}
	
	@Test
	public void push_duplicate_values() {
		stringStack.push("A");
		stringStack.push("A");
		stringStack.push("A");
		
		assertEquals("A", stringStack.pop());
		assertEquals("A", stringStack.pop());
		assertEquals("A", stringStack.pop());
	}
	
	@Test
	public void push_empty_string() {
		stringStack.push("");
		assertEquals("", stringStack.pop());
	}
	
	@Test
	public void push_mixed_strings() {
		stringStack.push("first");
		stringStack.push("");
		stringStack.push(null);
		stringStack.push("last");
		
		assertEquals("last", stringStack.pop());
		assertNull(stringStack.pop());
		assertEquals("", stringStack.pop());
		assertEquals("first", stringStack.pop());
	}
	
	// =============== POP METHOD TESTS ===============
	@Test
	public void push_push_pop() {
	    stringStack.push("Z");
	    stringStack.push("A");

	    assertEquals("A", stringStack.pop());
	}
	
	@Test
	public void pop_empty_stack() {
	    NoSuchElementException thrown =
	            assertThrows(
	                    NoSuchElementException.class,
	                    () -> stringStack.pop()
	            );

	    assertEquals("Stack is empty, cannot pop", thrown.getMessage());
	}
	
	@Test
	public void elements_are_popped_in_reverse_order() {
	    stringStack.push("A");
	    stringStack.push("B");
	    stringStack.push("C");

	    assertEquals("C", stringStack.pop());
	    assertEquals("B", stringStack.pop());
	    assertEquals("A", stringStack.pop());
	}
	
	@Test
	public void pop_single_element_leaves_empty_stack() {
		stringStack.push("A");
		stringStack.pop();
		
		// Verify stack is empty
		assertThrows(NoSuchElementException.class, () -> stringStack.pop());
	}
	
	@Test
	public void pop_returns_correct_element() {
		stringStack.push("A");
		String result = stringStack.pop();
		assertEquals("A", result);
	}
	
	@Test
	public void pop_all_elements_in_order() {
		stringStack.push("first");
		stringStack.push("second");
		stringStack.push("third");
		
		String third = stringStack.pop();
		String second = stringStack.pop();
		String first = stringStack.pop();
		
		assertEquals("third", third);
		assertEquals("second", second);
		assertEquals("first", first);
	}
	
	@Test
	public void pop_after_pop_empty_stack_throws() {
		stringStack.push("A");
		stringStack.pop();
		
		NoSuchElementException thrown =
				assertThrows(
						NoSuchElementException.class,
						() -> stringStack.pop()
				);
		assertEquals("Stack is empty, cannot pop", thrown.getMessage());
	}
	
	@Test
	public void pop_null_element() {
		stringStack.push(null);
		assertNull(stringStack.pop());
	}
	
	// =============== STATE MANAGEMENT TESTS ===============
	@Test
	public void push_pop_push_pop_sequence() {
		// Test state transitions
		stringStack.push("A");
		assertEquals("A", stringStack.pop());
		
		// Stack should be usable after being emptied
		stringStack.push("B");
		assertEquals("B", stringStack.pop());
	}
	
	@Test
	public void push_pop_push_multiple_sequence() {
		stringStack.push("A");
		stringStack.push("B");
		assertEquals("B", stringStack.pop());
		
		// Push more after partial pop
		stringStack.push("C");
		stringStack.push("D");
		
		assertEquals("D", stringStack.pop());
		assertEquals("C", stringStack.pop());
		assertEquals("A", stringStack.pop());
	}
	
	@Test
	public void multiple_push_operations_maintain_lifo() {
		stringStack.push("first");
		stringStack.push("second");
		
		assertEquals("second", stringStack.pop());
		
		stringStack.push("third");
		stringStack.push("fourth");
		
		assertEquals("fourth", stringStack.pop());
		assertEquals("third", stringStack.pop());
		assertEquals("first", stringStack.pop());
	}
	
	// =============== GENERIC TYPE TESTS ===============
	@Test
	public void stack_with_integers() {
		Stack<Integer> intStack = new Stack<>();
		intStack.push(1);
		intStack.push(2);
		intStack.push(3);
		
		assertEquals(3, intStack.pop());
		assertEquals(2, intStack.pop());
		assertEquals(1, intStack.pop());
	}
	
	@Test
	public void stack_with_objects() {
		Stack<Object> objStack = new Stack<>();
		objStack.push("string");
		objStack.push(42);
		objStack.push(3.14);
		
		assertEquals(3.14, objStack.pop());
		assertEquals(42, objStack.pop());
		assertEquals("string", objStack.pop());
	}
	}

