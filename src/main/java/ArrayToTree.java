import java.util.LinkedList;

import static java.lang.Math.max;

public class ArrayToTree {

	private static class Tree<T> {

		public T data;
		public Tree<T> left;
		public Tree<T> right;

	}

	public static <T> Tree<T> arrayToTree(T[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException();
		}
		LinkedList<Tree<T>> nodes = new LinkedList<>();
		Tree<T> root = new Tree<>();
		nodes.add(root);
		root.data = array[0];
		boolean skip = true;
		for (T item : array) {
			if (skip) {
				skip = false;
				continue;
			}
			Tree<T> node = nodes.getFirst();
			if (node.left == null) {
				node.left = new Tree<>();
				node.left.data = item;
				nodes.add(node.left);
			} else {
				node.right = new Tree<>();
				node.right.data = item;
				nodes.add(node.right);
				nodes.removeFirst();
			}
		}

		return root;
	}

	public static int height(Tree t) {
		int leftHeight = t.left == null ? 0 : height(t.left);
		int rightHeight = t.right == null ? 0 : height(t.right);
		return max(leftHeight, rightHeight) + 1;
	}

	public static void main(String[] args) {
		for (int i = 1; i < 1000; i++) {
			Integer[] arr = new Integer[i];
			Tree<Integer> tree = arrayToTree(arr);

			assert height(tree) <= Math.log(i) / Math.log(2) + 1;
		}
	}

}