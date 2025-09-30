/*
 * @file: Node.java
 * @description: This class represents a node in a Binary Search Tree (BST)
 * @author: Zell Godbold
 * @date: September 25, 2025
 */

// Establishes the Nodes
public class Node<T extends Comparable<T>> {
    T data ;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Various getter and setter methods
    public T getValue() {
        return data;
    }

    public void setValue(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    // Comparable Interface
    public int compareTo(Node<T> node) {
        return this.data.compareTo(node.getValue());
    }
}