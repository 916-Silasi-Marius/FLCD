package model;

public class Node {
    String value;
    Node left;
    Node right;

    public Node(String val) {
        this.value = val;
        this.left = null;
        this.right = null;
    }
}
