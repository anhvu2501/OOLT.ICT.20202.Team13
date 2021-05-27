package oop.ict.project.tree.binary;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.GenericTree;
import oop.ict.project.tree.generic.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree extends GenericTree {
    final int MAX_NB_CHILDREN = 2;

    public BinaryTree() {
        super();
    }

    public BinaryTree(Node root) {
        super(root);
    }

    public BinaryTree(Circle rootValue) {
        super(rootValue);
    }

    @Override
    public Node insertNode(Integer parentValue, Node newNode) throws TreeException {
        boolean isParentInTree = isInTree(root, parentValue);
        if (isParentInTree) {
            Node foundParentNode = searchNode(root, parentValue);
            if (foundParentNode.getNbChildren() < this.MAX_NB_CHILDREN) {
                newNode.setDepth(foundParentNode.getDepth() + 1);
                foundParentNode.children.add(newNode);
                return newNode;
            } else {
                throw new TreeException("Cannot insert! Binary tree can only has maximum 2 children per node");
            }

        } else {
            throw new TreeException("Cannot find node with value " + parentValue);
        }
    }

    public void deleteDeepest(Node root, Node delNodeBinary) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node temp;

        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp == delNodeBinary) {
                return;
            }

//            for (Node node : temp.children) {
//                if (node != null) {
//                    if (node == delNodeBinary)
//                        return;
//                    else
//                        q.add(node);
//                }
//            }

            for (int i = 0; i < temp.children.size(); i++) {
                if (temp.children.get(i) != null) {
                    if (temp.children.get(i).equals(delNodeBinary)) {
                        temp.children.set(i, null);
                        return;
                    } else
                        q.add(temp.children.get(i));
                }
            }

//            if (temp.children.get(temp.children.size() - 1) != null) {
//                if (temp.children.get(temp.children.size() - 1).equals(delNodeBinary)) {
//                    temp.children.set(temp.children.size() - 1, null);
//                    return;
//                } else
//                    q.add(temp.children.get(temp.children.size() - 1));
//            }
//
//            if (temp.children.get(0) != null) {
//                if (temp.children.get(0).equals(delNodeBinary)) {
//                    temp.children.set(0, null);
//                    return;
//                } else
//                    q.add(temp.children.get(0 - 1));
//            }
        }
    }

    public void deleteNodeBinary(Node root, int key) {
        if (root == null)
            return;

        for (Node node : root.children) {
            if (node == null) {
                return;
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node temp = null;
        Node keyNodeBinary = null;

        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.rootCircle.getSearchKey() == key)
                keyNodeBinary = temp;

            for (Node node : temp.children) {
                if (node != null)
                    q.add(node);
            }
        }

        if (keyNodeBinary != null) {
            int x = temp.rootCircle.getSearchKey();
            deleteDeepest(root, temp);
            keyNodeBinary.rootCircle = new Circle(x);
        }
    }


//    public NodeBinary root;
//
//    public BinaryTree(Circle rootCircle) {
//        root = new NodeBinary(rootCircle);
//    }
//
//    public BinaryTree() {
//        root = null;
//    }
//
//    public NodeBinary isEmpty() {
//        return root = null;
//    }
//
//    public NodeBinary getRoot() throws TreeException {
//        if (root == null) {
//            throw new TreeException("tree.TreeException: Empty Tree");
//        }
//        return root;
//    }
//
//    public NodeBinary createTree(int[] arr, NodeBinary root, int i) {
//        if (i < arr.length) {
//            Circle circle = new Circle(arr[i]);
//            root = new NodeBinary(circle);
//            root.left = createTree(arr, root.left, 2 * i + 1);
//            root.right = createTree(arr, root.right, 2 * i + 2);
//        }
//        return root;
//    }
//
//    public void insertItem(NodeBinary temp, int key) {
//        Circle newCircle = new Circle(key);
//        if (temp == null) {
//            root = new NodeBinary(newCircle);
//            return;
//        }
//
//        Queue<NodeBinary> q = new LinkedList<>();
//        q.add(temp);
//
//        while (!q.isEmpty()) {
//            temp = q.peek();
//            q.remove();
//
//            if (temp.left == null) {
//                temp.left = new NodeBinary(newCircle);
//                break;
//            } else
//                q.add(temp.left);
//            if (temp.right == null) {
//                temp.right = new NodeBinary(newCircle);
//                break;
//            } else
//                q.add(temp.right);
//        }
//    }
//
//    //Search for a NodeBinary
//    public NodeBinary searchNodeBinary(Integer searchKey) {
//        return searchNodeBinary(root, searchKey);
//    }
//
//    protected NodeBinary searchNodeBinary(NodeBinary NodeBinary, Integer searchKey) {
//        if (NodeBinary != null) {
//            NodeBinary.highlightFlag = true;
//            if (NodeBinary.rootCircle.getSearchKey().equals(searchKey)) {
//                return NodeBinary;
//            } else {
//                NodeBinary foundNodeBinary = searchNodeBinary(NodeBinary.left, searchKey);
//                if (foundNodeBinary == null)
//                    foundNodeBinary = searchNodeBinary(NodeBinary.right, searchKey);
//                return foundNodeBinary;
//            }
//        } else
//            return null;
//    }
//
//    public void updateNodeBinary(Integer searchKey, Integer newValue) {
//        searchNodeBinary(root, searchKey).rootCircle = new Circle(newValue);
//    }
//
//
//    public void deleteDeepest(NodeBinary root, NodeBinary delNodeBinary) {
//        Queue<NodeBinary> q = new LinkedList<>();
//        q.add(root);
//
//        NodeBinary temp;
//
//        while (!q.isEmpty()) {
//            temp = q.peek();
//            q.remove();
//
//            if (temp == delNodeBinary) {
//                return;
//            }
//
//            if (temp.right != null) {
//                if (temp.right == delNodeBinary) {
//                    temp.right = null;
//                    return;
//                } else
//                    q.add(temp.right);
//            }
//
//            if (temp.left != null) {
//                if (temp.left == delNodeBinary) {
//                    temp.left = null;
//                    return;
//                } else
//                    q.add(temp.left);
//            }
//        }
//    }
//
//    public void deleteNodeBinary(NodeBinary root, int key) {
//        if (root == null)
//            return;
//
//        if (root.left == null && root.right == null) {
//            if (root.rootCircle.getSearchKey() == key) {
//                return;
//            } else
//                return;
//        }
//
//        Queue<NodeBinary> q = new LinkedList<>();
//        q.add(root);
//        NodeBinary temp = null;
//        NodeBinary keyNodeBinary = null;
//
//        while (!q.isEmpty()) {
//            temp = q.peek();
//            q.remove();
//
//            if (temp.rootCircle.getSearchKey() == key)
//                keyNodeBinary = temp;
//
//            if (temp.left != null)
//                q.add(temp.left);
//
//            if (temp.right != null)
//                q.add(temp.right);
//        }
//
//        if (keyNodeBinary != null) {
//            int x = temp.rootCircle.getSearchKey();
//            deleteDeepest(root, temp);
//            keyNodeBinary.rootCircle = new Circle(x);
//        }
//    }
//
//
//    public int getHeight(NodeBinary root) {
//        if (root == null)
//            return 0;
//        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
//    }
//
//    public void setResetColor(NodeBinary tNodeBinary) {
//        resetColor(tNodeBinary);
//    }
//
//    protected void resetColor(NodeBinary tNodeBinary) {
//        if (tNodeBinary != null) {
//            tNodeBinary.highlightFlag = false;
//
//            if (tNodeBinary.left != null) {
//                tNodeBinary.left.highlightFlag = false;
//            }
//
//            if (tNodeBinary.right != null) {
//                tNodeBinary.right.highlightFlag = false;
//            }
//            resetColor(tNodeBinary.left);
//            resetColor(tNodeBinary.right);
//        }
//    }
//
//    public void printInorder(NodeBinary root) {
//        if (root != null) {
//            printInorder(root.left);
//            System.out.print(root.rootCircle.getSearchKey() + " ");
//            printInorder(root.right);
//        }
//    }
//
//    public void printPostorder(NodeBinary root) {
//        if (root != null) {
//            printPostorder(root.left);
//            printPostorder(root.right);
//            System.out.print(root.rootCircle.getSearchKey() + " ");
//        }
//    }
//
//    public void printPreorder(NodeBinary root) {
//        if (root != null) {
//            System.out.print(root.rootCircle.getSearchKey() + " ");
//            printPreorder(root.left);
//            printPreorder(root.right);
//        }
//    }
//
//    public static void main(String[] args) {
//        BinaryTree binaryTree = new BinaryTree();
//        int[] arr = {1, 2, 3, 4, 5, 6, 6, 6, 6};
//        binaryTree.root = binaryTree.createTree(arr, binaryTree.root, 0);
//        binaryTree.printInorder(binaryTree.root);
//
//        binaryTree.insertItem(binaryTree.root, 7);
//        System.out.println();
//        binaryTree.printInorder(binaryTree.root);
//
//        System.out.println();
//        BinaryTree binaryTree1 = new BinaryTree();
//        binaryTree1.root = new NodeBinary(new Circle(10));
//        binaryTree1.root.left = new NodeBinary(new Circle(11));
//        binaryTree1.root.left.left = new NodeBinary(new Circle(7));
//        binaryTree1.root.right = new NodeBinary(new Circle(9));
//        binaryTree1.root.right.left = new NodeBinary(new Circle(15));
//        binaryTree1.root.right.right = new NodeBinary(new Circle(8));
//        System.out.println("Print Inorder: ");
//        binaryTree1.printInorder(binaryTree1.root);
//        System.out.println("\nPrint Preorder: ");
//        binaryTree1.printPreorder(binaryTree1.root);
//        System.out.println("\nPrint Postorder: ");
//        binaryTree1.printPostorder(binaryTree1.root);
//        System.out.println("\nTest Insert: Inorder: ");
//        binaryTree1.insertItem(binaryTree1.root, 12);
//        binaryTree1.printInorder(binaryTree1.root);
//
//        binaryTree1.deleteNodeBinary(binaryTree1.root, 11);
//        System.out.println();
//        binaryTree1.printInorder(binaryTree1.root);
//        System.out.println("\nGet Height: ");
//        System.out.println(binaryTree1.getHeight(binaryTree1.root));
//        System.out.println("Test search: ");
//        NodeBinary temp = binaryTree1.searchNodeBinary(8);
//        System.out.println(temp.rootCircle.getSearchKey());
//    }

}
