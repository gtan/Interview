public class Queue {
	Node first, last;

	void enqueue(Object item) {
		Node n = new Node(item);
		if (first == null) {
			first = n;
			last = first;
		} else {
			last.next = n;
			last = n;
		}
	}

	Object dequeue() {
		if (first == null) {
			return null;
		}
		Object item = first.data;
		first = first.next;
		if (first == null) last = null;
		return item;
	}
}