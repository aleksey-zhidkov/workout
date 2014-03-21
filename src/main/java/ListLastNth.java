public class ListLastNth {

    private static class Node {

        public final Node next;
        public final int data;

        private Node(Node next, int data) {
            this.next = next;
            this.data = data;
        }
    }

    public static Node getLast(Node list, int n) {
        assert list != null && n >= 0;

        Node node = list;
        Node res = list;
        for (int i = 0; i < n; i++) {
            if (node.next == null) {
                return null;
            }
            node = node.next;
        }

        while (node.next != null) {
            node = node.next;
            res = res.next;
        }

        return res;
    }

    private static int i = 0;

    public static Node getLastRec(Node list, int n) {
        i = 0;
        return getLastRecImpl(list, n);
    }

    public static Node getLastRecImpl(Node list, int n) {
        if (list.next == null) {
            if (n == 0) {
                return list;
            } else {
                return null;
            }
        } else {
            Node res = getLastRecImpl(list.next, n);

            if (res != null) {
                return res;
            } else if (++i == n) {
                return list;
            } else {
                return null;
            }
        }
    }

    @SuppressWarnings("AssertWithSideEffects")
    public static void main(String[] args) {
        Node n = new Node(null, 0);
        assert getLast(n, 0).data == 0;
        assert getLastRec(n, 1) == null;

        n = new Node(
                new Node(
                        new Node(
                                new Node(null, 3), 2
                        ), 1
                ), 0
        );
        assert getLastRec(n, 0).data == 3;
        assert getLastRec(n, 1).data == 2;
        assert getLastRec(n, 2).data == 1;
        assert getLastRec(n, 3).data == 0;

        assert getLastRec(n, 4) == null;
    }

}
