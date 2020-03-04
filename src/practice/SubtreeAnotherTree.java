package practice;

/**
 * if a binary tree A is subtree of another B, it must be one of these: A equals
 * B (same tree), A’s left is subtree of B (recursive check) or A’s right is
 * subtree of B (recursive check). Space complexity : O(n). The depth of the
 * recursion tree can go upto nn. nn refers to the number of nodes in ss.
 * 
 * @author leen
 *
 */

public class SubtreeAnotherTree {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (t == null) {
			return true;
		}
		if (s == null) {
			return false;
		}
		if (isSameTree(s, t)) {
			return true;
		}
		return isSubtree(s.left, t) || isSubtree(s.right, t);

	}

	private boolean isSameTree(TreeNode s, TreeNode t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null || t == null) {
			return false;
		}
		if (s.val != t.val) {
			return false;
		}
		return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);

	}

}
