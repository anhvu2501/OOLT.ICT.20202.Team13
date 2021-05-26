
package oop.ict.project.tree.generic;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.exception.TreeException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class GenericTree {
    public Node root;

    public GenericTree() {
        this.root = null;
    }

    public GenericTree(Node root) {
        this.root = root;
        this.root.setDepth(1);
    }

    public GenericTree(Circle rootValue) {
        this.root.rootCircle = rootValue;
        this.root.setDepth(1);
    }

    public GenericTree createTree() {
        return new GenericTree();
    }

    public Node insertNode(Integer parentValue, Node newNode) throws TreeException {
        boolean isParentInTree = isInTree(root, parentValue);
        if (isParentInTree) {
            Node foundParentNode = searchNode(root, parentValue);
            foundParentNode.children.add(newNode);
            return newNode;
        } else {
            throw new TreeException("Cannot find node with value " + parentValue);
        }
    }

    public Node getParentNode(Node node, Integer key) {
        if (node.children.contains(searchNode(root, key))) {
            return node;
        }

        for (Node child : node.children) {
            Node foundNode = getParentNode(child, key);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

	public boolean deleteNode(Integer value) throws TreeException{  
//		Node foundDeleteNode = root;
//		boolean foundNode = searchNode(root, value, foundDeleteNode);
//		if (foundNode) {
//			
//			return true;
//		} else {
//			System.out.println("Cannot find node with value " + value);
//			return false;
//		}
		boolean isInTree = isInTree(root, value);
        if (isInTree) {
            Node foundNode = searchNode(root, value);
            if(foundNode.children == null) {
            	foundNode = null;
            	return true;
            }
            else {
            	Node parentNode = getParentNode(root, value);
            	for(Node child: foundNode.children) {
            		parentNode.children.add(child);
            	}
            	parentNode.children.remove(foundNode);
            	foundNode = null;
            	return true;
            }
        } else {
            throw new TreeException("Cannot find node with value " + value);
        }
	}

    public Node updateValueOfNode(Integer currentValue, Integer newValue) throws TreeException {
        // Node foundUpdateNode = new Node();
        boolean isUpdateInTree = isInTree(root, currentValue);
        if (isUpdateInTree) {
            Node foundUpdateNode = searchNode(root, currentValue);
            foundUpdateNode.rootCircle.setSearchKey(newValue);
            System.out.println(
                    "Node with value " + currentValue + " is updated to " + newValue);
            return foundUpdateNode;
        } else {
            throw new TreeException("Cannot find node with value " + currentValue);
        }
    }

    private class Pair {
        private Node node;
        private int state;

        private Pair(Node node, Integer state) {
            this.node = node;
            this.state = state;
        }
    }

    public ArrayList<Node> traversePreOrder() {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, -1));

        ArrayList<Node> preOrderList = new ArrayList<>();
        while (stack.size() > 0) {
            Pair top = stack.peek();
            if (top.state == -1) {
                preOrderList.add(top.node);
                top.state++;
            } else if (top.state == top.node.children.size()) {
                stack.pop();
            } else {
                Pair cp = new Pair(top.node.children.get(top.state), -1);
                stack.push(cp);
                top.state++;
            }
        }
        return preOrderList;
    }

    public ArrayList<Node> traversePostOrder() {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, -1));

        ArrayList<Node> postOrderList = new ArrayList<>();
        while (stack.size() > 0) {
            Pair top = stack.peek();
            if (top.state == -1) {
                top.state++;
            } else if (top.state == top.node.children.size()) {
                postOrderList.add(top.node);
                stack.pop();
            } else {
                Pair cp = new Pair(top.node.children.get(top.state), -1);
                stack.push(cp);
                top.state++;
            }
        }
        return postOrderList;
    }

    //for testing only
    public void traverseTree(Node rootNode) {
        System.out.println("Node Pre " + rootNode.rootCircle.getSearchKey());
        for (Node child : rootNode.children) {
            System.out
                    .println("Edge Pre " + rootNode.rootCircle.getSearchKey() + "--" + child.rootCircle.getSearchKey());
            traverseTree(child);
            System.out.println(
                    "Edge Post " + rootNode.rootCircle.getSearchKey() + "--" + child.rootCircle.getSearchKey());

        }
        System.out.println("Node Post " + rootNode.rootCircle.getSearchKey());
    }

    public boolean isInTree(Node node, Integer key) {
        if (node.rootCircle.getSearchKey() == key) {
            return true;
        }
        for (Node child : node.children) {
            boolean found = isInTree(child, key);
            if (found) {
                return true;
            }
        }
        return false;
    }

    public Node searchNode(Node node, Integer key) {
        if (node.rootCircle.getSearchKey() == key) {
            return node;
        }

        for (Node child : node.children) {
            Node foundNode = searchNode(child, key);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }


}

package oop.ict.project.tree.generic;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.exception.TreeException;

import java.util.ArrayList;
import java.util.Stack;

public class GenericTree {
    public Node root;

    public GenericTree() {
        this.root = null;
    }

    public GenericTree(Node root) {
        this.root = root;
        this.root.setDepth(1);
    }

    public GenericTree(Circle rootValue) {
        this.root.rootCircle = rootValue;
        this.root.setDepth(1);
    }

    public GenericTree createTree() {
        return new GenericTree();
    }

    public Node insertNode(Integer parentValue, Node newNode) throws TreeException {
        boolean isParentInTree = isInTree(root, parentValue);
        if (isParentInTree) {
            Node foundParentNode = searchNode(root, parentValue);
            foundParentNode.children.add(newNode);
            return newNode;
        } else {
            throw new TreeException("Cannot find node with value " + parentValue);
        }
    }
    
    public Node getParentNode(Node node, Integer key) {
        if (node.children.contains(searchNode(root, key))) {
            return node;
        }

        for (Node child : node.children) {
            Node foundNode = getParentNode(child, key);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

	public boolean deleteNode(Integer value) throws TreeException{  
//		Node foundDeleteNode = root;
//		boolean foundNode = searchNode(root, value, foundDeleteNode);
//		if (foundNode) {
//			
//			return true;
//		} else {
//			System.out.println("Cannot find node with value " + value);
//			return false;
//		}
		boolean isInTree = isInTree(root, value);
        if (isInTree) {
            Node foundNode = searchNode(root, value);
            if(foundNode.children == null) {
            	foundNode = null;
            	return true;
            }
            else {
            	Node parentNode = getParentNode(root, value);
            	for(Node child: foundNode.children) {
            		parentNode.children.add(child);
            	}
            	parentNode.children.remove(foundNode);
            	foundNode = null;
            	return true;
            }
        } else {
            throw new TreeException("Cannot find node with value " + value);
        }
	}
	

    public Node updateValueOfNode(Integer currentValue, Integer newValue) throws TreeException {
        // Node foundUpdateNode = new Node();
        boolean isUpdateInTree = isInTree(root, currentValue);
        if (isUpdateInTree) {
            Node foundUpdateNode = searchNode(root, currentValue);
            foundUpdateNode.rootCircle.setSearchKey(newValue);
            System.out.println(
                    "Node with value " + currentValue + " is updated to " + newValue);
            return foundUpdateNode;
        } else {
            throw new TreeException("Cannot find node with value " + currentValue);
        }
    }

    private class Pair {
        private Node node;
        private int state;

        private Pair(Node node, Integer state) {
            this.node = node;
            this.state = state;
        }
    }

    public ArrayList<Node> traversePreOrder() {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, -1));

        ArrayList<Node> preOrderList = new ArrayList<>();
        while (stack.size() > 0) {
            Pair top = stack.peek();
            if (top.state == -1) {
                preOrderList.add(top.node);
                top.state++;
            } else if (top.state == top.node.children.size()) {
                stack.pop();
            } else {
                Pair cp = new Pair(top.node.children.get(top.state), -1);
                stack.push(cp);
                top.state++;
            }
        }
        return preOrderList;
    }

    public ArrayList<Node> traversePostOrder() {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, -1));

        ArrayList<Node> postOrderList = new ArrayList<>();
        while (stack.size() > 0) {
            Pair top = stack.peek();
            if (top.state == -1) {
                top.state++;
            } else if (top.state == top.node.children.size()) {
                postOrderList.add(top.node);
                stack.pop();
            } else {
                Pair cp = new Pair(top.node.children.get(top.state), -1);
                stack.push(cp);
                top.state++;
            }
        }
        return postOrderList;
    }

    //for testing only
    public void traverseTree(Node rootNode) {
        System.out.println("Node Pre " + rootNode.rootCircle.getSearchKey());
        for (Node child : rootNode.children) {
            System.out
                    .println("Edge Pre " + rootNode.rootCircle.getSearchKey() + "--" + child.rootCircle.getSearchKey());
            traverseTree(child);
            System.out.println(
                    "Edge Post " + rootNode.rootCircle.getSearchKey() + "--" + child.rootCircle.getSearchKey());

        }
        System.out.println("Node Post " + rootNode.rootCircle.getSearchKey());
    }

    public boolean isInTree(Node node, Integer key) {
        if (node.rootCircle.getSearchKey() == key) {
            return true;
        }
        for (Node child : node.children) {
            boolean found = isInTree(child, key);
            if (found) {
                return true;
            }
        }
        return false;
    }

    public Node searchNode(Node node, Integer key) {
        if (node.rootCircle.getSearchKey() == key) {
            return node;
        }

        for (Node child : node.children) {
            Node foundNode = searchNode(child, key);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

}

