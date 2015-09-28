public class Node {
	int data;
	Node next = null;

	public Node(int d) {
		data = d;
	}

	public void appendToTail(int d) {
		Node end = new Node(d);
		Node pointer = this;
		while (pointer.next != null) {
			pointer = pointer.next;
		}
		pointer.next = end;
	}

	public Node deleteNode(Node head, int d) {
		Node n = head; 
		if (n.data == d) {
			head = head.next;
		}
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
				return head;
			}
			n = n.next;
		}
		return head;
	}
}