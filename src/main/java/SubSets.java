import java.util.*;

public class SubSets {

    public static <T> Set<Set<T>> subsets(Set<T> set) {

        Set<Set<T>> subsets = new HashSet<>();
        subsets.add(set);

        for (T entry: set) {
            Set<T> subset = new HashSet<>(set);
            subset.remove(entry);
            subsets.addAll(subsets(subset));
        }

        return subsets;
    }

    public static void main(String[] args) {
        System.out.println(subsets(new HashSet<>()));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1))));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1, 2))));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1, 2, 3))));
        System.out.println(subsets(new HashSet<>(Arrays.asList(1, 2, 3, 4))));
    }

}
