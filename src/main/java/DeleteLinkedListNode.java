import java.util.Objects;

public class DeleteLinkedListNode {

	private static class Node {

		public Node next;

		public Node(Node next) {
			this.next = next;
		}

	}

	public static Node delete(Node head, Node toDelete) {

		Objects.requireNonNull(head);

		if (head.equals(toDelete)) {
			return head.next;
		}

		Node prev = head;
		while (prev.next != null) {
			if (prev.next.equals(toDelete)) {
				prev.next = prev.next.next;
				break;
			}
			prev = prev.next;
		}

		return head;
	}

	public static void main(String[] args) {
		Node d = new Node(null);
		Node c = new Node(d);
		Node b = new Node(c);
		Node a = new Node(b);

		delete(a, d);
		delete(a, b);
		Node head = delete(a, a);
		head = delete(head, c);
		assert head == null;
	}

}
