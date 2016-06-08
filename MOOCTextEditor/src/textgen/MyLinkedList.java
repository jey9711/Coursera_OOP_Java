package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;
		
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("Invalid element input!-add");
		}
		
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> last = tail.prev;
		
		last.next = newNode;
		newNode.prev = last;
		newNode.next = tail;
		tail.prev = newNode;
		size++;
		
		return (tail.prev == newNode);
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Invalid Index Input!-get");
		}
		
		LLNode<E> curr = head;
		
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		
		return curr.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if ((index < 0 || index > size - 1) && (index != 0 || size != 0)) {
			throw new IndexOutOfBoundsException("Invalid index input! -addIndex");
		}
		
		if (element == null) {
			throw new NullPointerException("Invalid element input!-addIndex");
		}
		
		LLNode<E> newNode = new LLNode<E>(element);
		
		LLNode<E> curr = head;
		
		
		for(int i = 0; i <= index; i++){
			curr = curr.next;
		}
		
		LLNode<E> prev = curr.prev;
		
		prev.next = newNode;
		newNode.next = curr;
		newNode.prev = prev;
		curr.prev = newNode;
		size++;
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Invalid index input!!");
		}
		
		LLNode<E> curr = head;
		
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		
		LLNode<E> removedNode = curr;
		LLNode<E> prev = removedNode.prev;
		LLNode<E> next = removedNode.next;
		
		prev.next = next;
		next.prev = prev;
		
		size--;
		
		return removedNode.data;
		
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		
		if(index > 0 && index < size){
			throw new IndexOutOfBoundsException("Index must be at least 0 and less than the size of the linked list");
		}
		
		if (element == null || index < 0) {
			throw new NullPointerException("Invalid element input!!");
		}
		
		LLNode<E> curr = head;
		
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		
		curr.data = element;

		return curr.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
