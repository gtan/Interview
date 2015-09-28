import java.util.HashSet;

public class LinkedLists {
	public void removeDuplicates(Node head) {
		HashSet<Integer> seen = new HashSet<Integer>();
		seen.add(head.data);
		while (head.next != null) {
			if (seen.contains(head.next.data)) {
				head.next = head.next.next;
			} else {
				seen.add(head.next.data);
				head = head.next;
			}
		}
	}

	public Node kthToLast(Node head, int k) {
		if (k <= 0) {
			return null;
		} 
		Node runner = head;
		for (int i = 0; i < k - 1; i++) {
			if (runner.next == null) {
				return null;
			}
			runner = runner.next;
		}
		if (runner.next == null) return null;
		while (runner.next != null) {
			runner = runner.next;
			head = head.next;
		}
		return head; 
	}

	public boolean deleteMiddle(Node p) {
		if (p == null || p.next == null) {
			return false;
		} else {
			p.data = p.next.data;
			p.next = p.next.next;
			return true;
		}
	}

	public void partition(Node head, int x) {
		Node rightStart = null;
		Node rightEnd = null;
		Node leftStart = null;
		Node leftEnd = null;

		while (head != null) {
			Node next = node.next;
			head.next = null;
			if (head.data < x) {
				if (rightStart == null) {
					rightStart = head
					rightEnd = rightStart;
				} else {
					rightEnd.next = head;
					rightEnd = head;
				}
			} else {
				if (left == null) {
					leftStart = head;
					leftEnd = leftStart;
				} else {
					leftEnd.next = head;
					leftEnd = head;
				}
			}
			head = next;
		}

		if (rightStart == null) {
			return leftStart;
		}

		rightEnd.next = leftStart;
		return rightStart;
	}

	public boolean isPalindrome(Node head) {
		Stack<Integer> reversed = new Stack<Integer>();
		
		Node runner = head;
		while (runner != null && runner.next != null) {
			reversed.push(head.data);
			head = head.next;
			runner = runner.next.next;
		}
		if (runner == null) {
			head = head.next;
		}
		while (head != null) {
			int top = reversed.pop()
			if (head.data != top) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

}