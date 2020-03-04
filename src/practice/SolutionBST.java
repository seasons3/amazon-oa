package practice;

import java.util.Arrays;
/**
 * 
先建bst，找lowest common ancestor， 然后找lca，node1，node2的level。
distance＝（level of node1 － level of lca） ＋（level of node2 － level of lca）
 * @author leen
 *
 */
public class SolutionBST {
	class TreeNode {
		int val;
		TreeNode left, right;

		TreeNode(int val) {
			this.val = val;
			left = right = null;
		}
	}

	private TreeNode buildBST(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = buildBST(nums, start, mid - 1);
		root.right = buildBST(nums, mid + 1, end);

		return root;
	}

	public int findBST(int[] nums, int n1, int n2) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		Arrays.sort(nums);
		TreeNode root = buildBST(nums, 0, nums.length - 1);
		System.out.println(root.val);

		return 0;

	}

	/**
	 * Find the lowest common ancestor of two nodes A and B given the root node
	 * 
	 */
	private TreeNode findLCA(TreeNode root, int n1, int n2) {
		if (root == null || root.val == n1 || root.val == n2) {
			return root;
		}
		TreeNode left = findLCA(root.left, n1, n2);
		TreeNode right = findLCA(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		}
		if (left == null) {
			return right;
		}
		return left;
	}

	private int findDistance(TreeNode root, int n1, int n2) {
		// find lca and distance to root, formula: dist1 + dist2 - 2*(root, lca node)
		int x = findLevel(root, n1, 1);// exclude it self
		int y = findLevel(root, n2, 1);
		if (x == 0 || y == 0) {
			return -1;
		}
		System.out.println("x" + x + "y" + y);
		int lca = findLevel(root, findLCABST(root, n1, n2).val, 1);

		return (x + y) - 2 * lca;
	}

	public static void main(String[] args) {
		SolutionBST st = new SolutionBST();
		int[] test = { 5, 6, 3, 1, 2, 4 };
		System.out.println(st.findBST(test, 2, 4));
	}
}
