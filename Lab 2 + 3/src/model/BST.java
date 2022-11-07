package model;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class BST {
    Node root;

    private Node addRecursive(Node current, String value) {
        if (current == null) {
            return new Node(value);
        }

        if (value.compareTo(current.value) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (value.compareTo(current.value) > 0) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public void add(String value) {
        root = addRecursive(root, value);
    }

    private Node deleteRecursive(Node current, String value) {
        if (current == null) {
            return null;
        }

        if (value.compareTo(current.value) == 0) {
            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            String smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value.compareTo(current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private String findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void delete(String value) {
        root = deleteRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, String value) {
        if (current == null) {
            return false;
        }
        if (value.compareTo(current.value) == 0) {
            return true;
        }
        return value.compareTo(current.value) < 0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public boolean containsNode(String value) {
        return containsNodeRecursive(root, value);
    }

    private BST createBinaryTree() {
        BST bt = new BST();

        bt.add("f");
        bt.add("d");
        bt.add("h");
        bt.add("c");
        bt.add("d");
        bt.add("g");
        bt.add("i");

        return bt;
    }

    @Test
    public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
        BST bt = createBinaryTree();

        assertTrue(bt.containsNode("f"));
        assertTrue(bt.containsNode("d"));

        assertFalse(bt.containsNode("a"));
    }

    @Test
    public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
        BST bt = createBinaryTree();

        bt.add("i");
        assertTrue(bt.containsNode("i"));
        bt.delete("i");
        assertTrue(bt.containsNode("i"));
        bt.delete("i");
        assertFalse(bt.containsNode("i"));
    }
}
