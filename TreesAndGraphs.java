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
		return isBST(root, null, null);
	}

	public boolean isBST(TreeNode root, int max, int min) {
		if (root == null) {
			return true;
		}
		if ((min != null && n.data <= min) || (max != null && n.data > max)) {
			return false;
		}
		if (!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max)) {
			return false;
		}
		return true; 
	}

	public TreeNode nextNode(TreeNode n) {
		if (n == null) return null;

		if (n.right != null) {
			return leftmostChild(n.right);
		}
		TreeNode p = n.parent;
		while (p != null && p.left != n) {
			n = p;
			p = p.parent;
		}
		return p;
	}

	public TreeNode leftmostChild(TreeNode n) {
		if (n == null) {
			return null;
		}
		while (n.left != null) {
			n = n.left;
		}
		return n;
	}

	// If we have links to parents and can mark nodes visited
	public TreeNode commonAncestor(TreeNode n1, TreeNode n2) {
		n1.visited = true;
		n2.visited = true;
		while (n1.parent != null && n2.parent != null) {
			if (n1.parent != null) {
				if (n1.parent.visited) {
					return n1.parent;
				} else {
					n1 = n1.parent;
					n1.visited = true;
				}
			}
			if (n2.parent != null) {
				if (n2.parent.visited) {
					return n2.parent;
				} else {
					n2 = n2.parent;
					n2.visited = true;
				}
			}
		}
		return null;
	}

	// If we don't have links to parents
	public boolean covers(TreeNode root, TreeNode n) {
		if (root == null) return false;
		if (root == n) {
			return true;
		}
		return covers(root.left, n) || covers(root.right, n);
	}

	public TreeNode commonAncestorHelper(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) return null;
		if (root == p || root == q) return root;
		boolean n1_on_left = covers(root.left, n1);
		boolean n2_on_left = covers(root.left, n2);
		if (n1_on_left != n2_on_left) { // n1 and n2 on different sides
			return root;
		}
		TreeNode child_side = n1_on_left ? root.left : root.right;
		return commonAncestorHelper(child_side, n1, n2);
	}

	public TreeNode commonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
		if (!covers(root, n1) || !covers(root, n2)) {
			return null;
		}
		return commonAncestorHelper(root, n1, n2);
	}

	public void findSum(TreeNode node, int sum, int[] path, int level) {
		if (node == null) {
			return;
		}
		path[level] = node.data;
		int t = 0;
		for (int i = level; i >= 0; i--) {
			t += path[i];
			if (t == sum) {
				print(path, i, level);
				break;
			}
		}
		findSum(node.left, sum, path, level+1);
		findSum(node.right, sum, path, level+1);
		path[level] = Integer.MIN_VALUE;
	}

	public void findSum(TreeNode node, int sum) {
		int depth = findDepth(node);
		int[] path = new int[depth];
		findSum(node, sum, path, 0);
	}

	public void print(int[] path, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.println();
	}

	public int findDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(findDepth(node.right), findDepth(node.left));
	}
}