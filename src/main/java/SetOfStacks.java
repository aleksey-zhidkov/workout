import java.util.ArrayList;
import java.util.LinkedList;

public class SetOfStacks {

    private final LinkedList<ArrayList<Integer>> stacks = new LinkedList<>();
    private final int stackLimit;

    public SetOfStacks(int stackLimit) {
        if (stackLimit <= 0) {
            throw new IllegalArgumentException("Stack limit is less than one: " + stackLimit);
        }
        this.stackLimit = stackLimit;
    }

    public void push(Integer x) {
        if (stacks.size() == 0 || stacks.getLast().size() == stackLimit) {
            stacks.add(new ArrayList<Integer>(stackLimit));
        }
        stacks.getLast().add(x);
    }

    public Integer pop() {
        if (stacks.size() == 0) {
            return null;
        }
        ArrayList<Integer> stack = stacks.getLast();
        Integer res = stack.remove(stack.size() - 1);
        if (stacks.getLast().size() == 0) {
            stacks.removeLast();
        }
        return res;
    }

    public Integer popAt(int idx) {
        if (idx < 0 || idx >= stacks.size()) {
            throw new IndexOutOfBoundsException("idx = " + idx);
        }
        if (stacks.size() == 0) {
            return null;
        }
        ArrayList<Integer> stack = stacks.get(idx);
        Integer res = stack.remove(stack.size() - 1);
        if (stack.size() == 0) {
            assert idx == stacks.size() - 1 : "Middle stack is empty!";

            stacks.remove(idx);
        } else {
            normalize(idx);
        }
        return res;
    }

    private void normalize(int idx) {
        for (int i = idx; i < stacks.size() - 1; i++) {
            stacks.get(i).add(stacks.get(i + 1).remove(0));
        }
        if (stacks.getLast().size() == 0) {
            stacks.removeLast();
        }
    }

    public static void main(String[] args) {
        try {
            new SetOfStacks(0);
            assert false : "IllegalArgumentException expected";
        } catch (IllegalArgumentException expected) {}

        SetOfStacks stacks = new SetOfStacks(2);
        assert stacks.pop() == null;
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);

        assert stacks.stacks.size() == 2;
        assert stacks.pop() == 4 && stacks.pop() == 3 && stacks.pop() == 2 && stacks.pop() == 1;

        stacks = new SetOfStacks(2);
        assert stacks.pop() == null;

        try {
            stacks.popAt(-1);
            assert false : "IndexOutOfBoundsException expected";
        } catch (IndexOutOfBoundsException expected) {}
        try {
            stacks.popAt(1);
            assert false : "IndexOutOfBoundsException expected";
        } catch (IndexOutOfBoundsException expected) {}

        stacks.push(1);
        assert stacks.popAt(0) == 1;

        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);

        assert stacks.popAt(0) == 2;
        assert stacks.popAt(0) == 3 && stacks.popAt(0) == 4 && stacks.popAt(0) == 1 && stacks.pop() == null;

        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);

        assert stacks.popAt(0) == 2;
        assert stacks.pop() == 4 && stacks.pop() == 3 && stacks.pop() == 1 && stacks.pop() == null;
    }

}