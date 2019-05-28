/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 24, 2019
  *Assignment:	Personal Study, tests the LinkedList class
  *Bugs:
  *Sources:
  */
package DataStructure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	/**
	 * a linked list for testing
	 */
	private LinkedList l;
	
	@Before
	/**
	 * sets up a simple Linked List for testing
	 */
	public void setupLinkedList() {
		//create and populate a new linked list
		l=new LinkedList();
		l.add("a");
		l.add("b");
		l.add("c");
	}
	
	@Test
	/**
	 * make sure that the LinkedList can be 
	 * traversed backwards
	 */
	public void testLinkedListReverseTraverse() {
		//go through the list backwards
		//building up a traversal string
		LinkedListNode n=l.getTail();
		String list=n.getVal();
		while(n.getPrev()!=null) {
			n=n.getPrev();
			list+=n.getVal();
		}
		//assert that the traversal string is correct
		assertEquals("cba",list);
	}
	
	@Test
	/**
	 * asserts that a LinkedList can be traversed forwards
	 */
	public void testLinkedListForwardTraverse() {
		//go through the list forwards
		//building up a traversal string
		LinkedListNode n=l.getHead();
		String list=n.getVal();
		while(n.getNext()!=null) {
			n=n.getNext();
			list+=n.getVal();
		}
		//assert that the traversal string is correct
		assertEquals("abc",list);
	}
	
	@Test
	/**
	 * Asserts that the list's to string is correct
	 */
	public void testToString() {
		//test the linked list to string
		assertEquals("a -->b -->c",l.toString());
	}
	
	@Test
	/**
	 * Asserts that two elements of the list can be switched
	 */
	public void testSwitchNodes() {
		//ensure list structure before swap
		assertEquals("a -->b -->c",l.toString());
		//store the tail in the linked list
		LinkedListNode tail=l.getTail();
		//store the node behind the tail
		LinkedListNode prev=tail.getPrev();
		//switch the two nodes
		l.switchNodes(prev,tail);
		//check the list structure using tostring to make sure swap happened
		assertEquals("a -->c -->b",l.toString());
	}
}
