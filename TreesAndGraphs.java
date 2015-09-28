public class TreesAndGraphs {
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		int heightDiff = height(root.right) - height(root.left)
		if (Math.abs(heightDiff) > 1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right)
	}

	public int height(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(height(root.left), height(root.right))
	}

	// A more efficient algorithm
	public boolean isBalanced2(TreeNode root) {
		if (checkHeight(root) == -1) {
			return false;
		}
		return true;
	}

	public static int checkHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = checkHeight(root.left);
		if (leftHeight == -1) {
			return -1;
		}
		int rightHeight = checkHeight(root.right);
		if (rightHeight == -1) {
			return -1;
		}
		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1) {
			return -1;
		} else {
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}

	public boolean search(Graph g, Node start, Node end) {
		LinkedList<Node> queue = new LinkedList<Node>();
		start.visited = true;
		queue.add(start);
		while (queue.size() > 0) {
			Node u = queue.getFirst();
			for (Node v : u.neighbors) {
				if (not v.visited) {
					if (equals(end, v)) {
						return true;
					}
					v.visited = true;
					queue.addLast(v);
				}
			}	
		}
		return false;
	}

	public TreeNode createBST(int [] a) {
		return createBSTHelper(a, 0, a.length - 1);
	}

	public TreeNode createBSTHelper(int[] a, int start, int end) {
		if (start > end) {
			return null; 
		}
		int middle = (end + start) / 2; 
		TreeNode result = new TreeNode(a[middle]);
		result.left = createBSTHelper(a, start, middle - 1);
		result.right = createBSTHelper(a, middle + 1, end);
		return result;
	}

	public ArrayList<LinkedList<TreeNode>> sortByDepth(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<Node>>();
		sortByDepthHelper(root, result, 0);
		return result;
	}

	public void sortByDepthHelper(TreeNode root, ArrayList<LinkedList<Node>> result, int depth) {
		if (root != null) {
			LinkedList<TreeNode> lst = null;
			if (result.length() == depth) {
				lst = new LinkedList<TreeNode>();
				result.add(lst);
			} else {
				lst = result.get(depth-1);
			}
			lst.add(root);
			sortByDepthHelper(root.left, result, depth+1)
			sortByDepthHelper(root.right, result, depth+1)
		}
	}

	public boolean isBST(TreeNode root) {
		if (root == null) {
			return true;
		} else {
			boolean leftLess = true;
			boolean rightMore = true;
			if (root.left != null) {
				leftLess = root.left.data <= root.data;
			}
			if (root.right != null) {
				rightMore = root.right.data > root.data;
			}
			return leftLess && rightMore && isBST(root.left) && isBST(root.right)
		}
	}
}