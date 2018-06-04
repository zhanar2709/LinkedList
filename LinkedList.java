package exercise35;

import exercise35.Node;

/**
 * Class LinkedList implements an ordered list as a single linked list. Data
 * objects can be added to the end of the list or inserted or deleted by index.
 * Data objects can be any type of object.
 * 
 * To clients of a LinkedList, data objects are added, inserted, and deleted from
 * a list. However, within the class, a LinkedList is implemented as a list of
 * Node objects, where each Node contains a reference to a data object. When a
 * data object is added to a list, a new Node object with a reference to the
 * data object is created, then the Node is added to the list. Deleting an
 * object from the list means deleting the Node that contains a reference to the
 * data object.
 */
public class LinkedList {
	private Node head;  // reference to the first node in the list
	private Node tail;  // reference to the last node in the list
	private int  size;  // the number of nodes in the list
	
	/** Returns the size of this list. */
	public int size() {
		return size;
	}
	
	/** Deletes all objects from this list. */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/** Adds the given data object to the end of this list. */
	public void add(Object data) {
		Node newNode = new Node(null, data);
		if (size == 0) {
			head = newNode;
			tail = newNode;
			size = 1;
		} else {
			tail.next = newNode;
			tail = newNode;
			size++;
		}
	}
	
	/**
	 * Inserts the given object at the given index. If there is an object at the
	 * given index, then it and all subsequent objects are shifted to the right,
	 * thus increasing their index by one.
	 * 
	 * If the given index is less than 0 or greater than or equal to the size of
	 * the list, then an IndexOutOfBoundsException is thrown.
	 */
	public void insert(Object data, int index) {
		
		// if index out of range, throw exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		
		// index in range, create Node for new data
		Node newNode = new Node(null, data);
		
		// if index == 0, insert at front of list
		if (index == 0) {
			if (size == 0) {
				head = newNode;
				tail = newNode;
				size = 1;
			} else {
				newNode.next = head;
				head = newNode;
				size++;
			}
		}
		
		// else index > 0, insert in middle
		else {	
			// find the node that is previous to the node with the given index
			Node prev = head;
			for (int i = 1;	i < index; i++) {
				prev = prev.next;
			}
			
			// insert newNode after prev
			newNode.next = prev.next;
			prev.next = newNode;
			
			size++;
		}
	}
	
	/**
	 * Deletes the object at the given index. If any objects exist to the right
	 * of the deleted object then it and all subsequent objects are shifted to
	 * the left, thus decreasing their index by one.
	 * 
	 * If the given index is less than 0 or greater than or equal to the size of
	 * the list, then an IndexOutOfBoundsException is thrown.
	 */
	public void delete(int index) {
		
		// if index out of range, throw exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		
		// if index == 0, delete first node
		if (index == 0) {
			head = head.next;
			size--;
		}
		
		// else index > 0, delete from middle or end
		else {	
			// find the node that is previous to the node with the given index
			Node prev = head;
			for (int i = 1;	i < index; i++) {
				prev = prev.next;
			}
			
			// delete node after prev
			if (prev.next == tail) {
				prev.next = null;            // delete last node
				tail = prev;                 // set new tail
			} else {
				prev.next = prev.next.next;  // delete node from middle
			}
			size--;
		}
		
		// if size is 0, reset head and tail
		if (size == 0) {
			head = null;
			tail = null;
		}
	}
	
	/**
	 * Returns the object at the given index. Throws IndexOutOfBoundsException if
	 * the given index is less than 0 or greater than or equal to the size of this
	 * list.
	 */
	Object get(int index) {
		
		// if index out of range, throw exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}

		// find the node with the given index
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		
		// return the data for the found node
		return node.data;
	}
	
	/** 
	 * Returns a string description of this list showing the size of the list
	 * on the first line, then showing each object in this list on a new line.
	 */
	public String toString() {
		String s = "LinkedList: size = " + size + "\n";
		for (Node node = head; node != null; node = node.next) {
			s += node.data + "\n";
		}
		return s;
	}
}