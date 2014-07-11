import java.util.*;

public class SubSets {

    public static <T> Set<Set<T>> subsets(Set<T> set) {

        if (set.size() == 0) {
            return Collections.singleton(set);
        }

        LinkedHashSet<T> tail = new LinkedHashSet<>(set);
        Iterator<T> iterator = tail.iterator();
        T e = iterator.next();
        iterator.remove();

        Set<Set<T>> subsets = subsets(tail);
        Set<Set<T>> res = new HashSet<>();
        for (Set<T> subset: subsets) {
            res.add(subset);
            HashSet<T> subset2 = new HashSet<>(subset);
            subset2.add(e);
            res.add(subset2);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(subsets(new HashSet<>()));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1))));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1, 2))));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1, 2, 3))));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1, 2, 3, 4))));
    }

}
