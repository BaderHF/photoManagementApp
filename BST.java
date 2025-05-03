
public class BST<T> {
    public class BSTNode <T> {
        public String key;
        public T data;
        public BSTNode<T> left, right;

        public BSTNode(String k, T val) {
                key = k;
                data = val;
                left = right = null;
        }

        public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {
                key = k;
                data = val;
                left = l;
                right = r;
        }
    }

	BSTNode<T> root, current;
	String Keys;

	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}
	

	public boolean findkey(String tkey) {
		BSTNode<T> p = root, q = root;

		if (empty())
			return false;

		while (p != null) {
			q = p;
			if (p.key.compareToIgnoreCase(tkey) == 0) {
				current = p;
				return true;
				}
			else if (tkey.compareToIgnoreCase(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		current = q;
		return false;
	}
	
	public boolean insert(String k, T val) {
		BSTNode<T> p, q = current;

		if (findkey(k)) {
			current = q;
			return false;
		}

		p = new BSTNode<T>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		} else {

			if (k.compareToIgnoreCase(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	public boolean remove_key(String tkey) {
		Boolean removed = false;
		BSTNode<T> p;
		p = remove_aux(tkey, root, removed);
		current = root = p;
		return removed;
	}

	private BSTNode<T> remove_aux(String key, BSTNode<T> p, Boolean flag) {
		BSTNode<T> q, child = null;
		if (p == null)
			return null;
		if (key.compareToIgnoreCase(p.key) < 0)
			p.left = remove_aux(key, p.left, flag); // go left
		else if (key.compareToIgnoreCase(p.key) > 0)
			p.right = remove_aux(key, p.right, flag); // go right
		else {
			flag = true;
			if (p.left != null && p.right != null) { // two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			} 
			else {
				if (p.right == null) // one child
					child = p.left;
				else if (p.left == null) // one child
					child = p.right;
				return child;
			}
		}
		return p;
	}

	private BSTNode<T> find_min(BSTNode<T> p) {
		if (p == null)
			return null;

		while (p.left != null) {
			p = p.left;
		}

		return p;
	}

	public boolean update(String key, T data) {
		remove_key(current.key);
		return insert(key, data);
	}

	
	public String inOrder() {
		Keys = "";
		if (root != null)
			inOrderRec(root);
		return Keys;

	}

	private void inOrderRec(BSTNode<T> p) {
		if (p.left != null)
			inOrderRec(p.left);
		if (Keys == "")
			Keys = p.key;
		else
			Keys += " AND " + p.key;
		if (p.right != null)
			inOrderRec(p.right);
	}
}