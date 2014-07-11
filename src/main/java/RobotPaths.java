import java.awt.*;
import java.util.*;
import java.util.List;

public class RobotPaths {

    public static Set<List<Point>> getPaths(int n) {
        return getPathsImpl(n, new Point(), new LinkedList<Point>());
    }

    public static Set<List<Point>> getPathsImpl(int n, Point pos, List<Point> prefix) {
        List<Point> newPrefix = new LinkedList<>(prefix);
        newPrefix.add(pos);

        if (pos.x == n - 1 && pos.y == n - 1) {
            return Collections.singleton(newPrefix);
        }

        Set<List<Point>> res = new HashSet<>();
        if (pos.x < n - 1) {
            res.addAll(getPathsImpl(n, new Point(pos.x + 1, pos.y), newPrefix));
        }
        if (pos.y < n - 1) {
            res.addAll(getPathsImpl(n, new Point(pos.x, pos.y + 1), newPrefix));
        }

        if (res.size() == 0) {
            res.add(prefix);
        }

        return res;
    }

    public static void main(String[] args) {
        assert getPaths(1).size() == 1;
        assert getPaths(2).size() == 2 : getPaths(2);
        assert getPaths(3).size() == 6 : getPaths(3);
        assert getPaths(4).size() == 20 : getPaths(4);
    }

}