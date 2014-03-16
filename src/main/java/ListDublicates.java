public class ListDublicates {

    private static final class Node<T> {

        public T data;
        public Node<T> next;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public static <T> Node<T> removeDublicates(Node<T> head) {
        Node<T> node = head;
        while (node != null) {
            removeEntries(node);
            node = node.next;
        }
        return head;
    }

    public static <T> void removeEntries(Node<T> head) {
        T data = head.data;
        Node<T> prev = head;
        Node<T> node = head.next;
        while (node != null) {
            if (data != null && data.equals(node.data) || (data == null && node.data == null)) {
                prev.next = node.next;
                node = node.next;
            } else {
                prev = node;
                node = node.next;
            }
        }
    }

    private static void print(Node head) {
        System.out.println("List: ");
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
        System.out.println();
    }

    private static <T> Node<T> build (T ... values) {
        if (values.length == 0) {
            return null;
        }
        Node<T> node = new Node<T>(values[0], null);
        Node<T> head = node;
        for (int i = 1; i < values.length; i++) {
            node.next = new Node<T>(values[i], null);
            node = node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        print(removeDublicates(build(new Integer[]{null})));
        print(removeDublicates(build(1)));
        print(removeDublicates(build(1, 1)));
        print(removeDublicates(build(1, 1, 1)));
        print(removeDublicates(build(1, 2, 1, 2, 1, 2)));
        print(removeDublicates(build(1, 2, 2, 2, 1)));
        print(removeDublicates(build(1, 1, 1, 1, 2, 2, 2, 2)));
        print(removeDublicates(build(1, 1, 1, 1, 1, 2, 2, 2, 2, 2)));
        print(removeDublicates(build(1, 2, 3, 4, 5)));
        print(removeDublicates(build(1, 2, 3, 4, 5, 5)));
        print(removeDublicates(build(1, 2, 3, 3, 4, 5, 5)));
        print(removeDublicates(build(1, 1, 2, 3, 3, 4, 5, 5)));
    }

}
