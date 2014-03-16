public class TreeDepth {

	public static class Tree {

		private final Tree leftChild;
		private final Tree rightChild;

		public Tree(Tree leftChild, Tree rightChild) {
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		public boolean isBalanced() {
			int leftDepth = leftChild != null
					? leftChild.maxDepth()
					: 0;
			int rightDepth = rightChild != null
					? rightChild.maxDepth()
					: 0;
			return Math.abs(leftDepth - rightDepth) <= 1;
		}

		private int maxDepth() {
			int leftDepth = leftChild != null
					? leftChild.maxDepth()
					: 0;
			int rightDepth = rightChild != null
					? rightChild.maxDepth()
					: 0;
			return Math.max(leftDepth, rightDepth) + 1;
		}

	}

	public static void main(String[] args) {
		Tree emptyTree = new Tree(null, null);
		assert emptyTree.isBalanced();

		assert new Tree(emptyTree, emptyTree).isBalanced();

		assert new Tree(emptyTree, null).isBalanced();

		assert new Tree(null, emptyTree).isBalanced();

		assert !new Tree(null,
				new Tree(null, emptyTree)).isBalanced();

		assert !new Tree(new Tree(null, emptyTree),
				null).isBalanced();

		assert new Tree(new Tree(null, emptyTree),
				new Tree(null, emptyTree)).isBalanced();
	}

}
