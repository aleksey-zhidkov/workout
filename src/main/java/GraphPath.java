import java.util.*;

public class GraphPath {

    public static class GraphNode {
    
        public final List<GraphNode> links = new ArrayList<GraphNode>();

        public void addNodes(GraphNode ... nodes) {
            links.addAll(Arrays.asList(nodes));
        }

        public Collection<GraphNode> getLinks() {
            return new ArrayList<>(links);
        }

    }

    public static boolean hasPath(GraphNode from, GraphNode to) {
        Objects.requireNonNull(from, "From node should not be null");
        Objects.requireNonNull(to, "To node should not be null");

        LinkedList<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();

        queue.add(from);

        while (queue.size() > 0) {
            GraphNode n = queue.removeFirst();
            if (n.equals(to)) {
                return true;
            }           

            visited.add(n);
            for (GraphNode neig : n.getLinks()) {
                if (!visited.contains(neig)) {
                    queue.addLast(neig);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphNode n1 = new GraphNode();
        GraphNode n2 = new GraphNode();
        GraphNode n3 = new GraphNode();
        GraphNode n4 = new GraphNode();
        GraphNode n5 = new GraphNode();

        assert hasPath(n1, n1);        
        assert !hasPath(n1, n2);

        n1.addNodes(n2);
        assert hasPath(n1, n2);
        assert !hasPath(n2, n1);

        n2.addNodes(n3);
        assert hasPath(n1, n3);
        assert !hasPath(n3, n1);

        n3.addNodes(n2, n4);

        assert hasPath(n1, n4);
        assert !hasPath(n4, n1);

        n4.addNodes(n5);

        assert hasPath(n1, n5);
        assert !hasPath(n5, n1);

        n4.addNodes(n2);

        assert hasPath(n1, n5);
        assert !hasPath(n5, n1);

        n5.addNodes(n1);

        assert hasPath(n2, n1) && hasPath(n3, n1) && hasPath(n4, n1) && hasPath(n5, n1) && hasPath(n4, n3);

    }

}