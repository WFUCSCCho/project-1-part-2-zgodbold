/*********************************************************
 * @file: BST.java
 * @description: Generic Binary Search Tree with Iterable
 *               inorder traversal and utility methods.
 *********************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T> {

    // Internal node class
    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root;

    public BST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Insert (duplicates by compareTo() are ignored)
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node<T> insertRec(Node<T> node, T data) {
        if (node == null) return new Node<>(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = insertRec(node.left, data);
        else if (cmp > 0) node.right = insertRec(node.right, data);
        // if cmp == 0 -> duplicate (do nothing)
        return node;
    }

    // Search: returns the stored object if found, otherwise null
    public T search(T data) {
        Node<T> node = searchRec(root, data);
        return node == null ? null : node.data;
    }

    private Node<T> searchRec(Node<T> node, T data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp == 0) return node;
        else if (cmp < 0) return searchRec(node.left, data);
        else return searchRec(node.right, data);
    }

    // Remove: returns true if removed, false otherwise
    public boolean remove(T data) {
        if (search(data) == null) return false;
        root = removeRec(root, data);
        return true;
    }

    private Node<T> removeRec(Node<T> node, T data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = removeRec(node.left, data);
        else if (cmp > 0) node.right = removeRec(node.right, data);
        else {
            // node to delete found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // two children: replace with inorder successor (smallest in right subtree)
            Node<T> succ = minNode(node.right);
            node.data = succ.data;
            // remove successor node (note: successor.compareTo(node.data) == 0 now)
            node.right = removeRec(node.right, succ.data);
        }
        return node;
    }

    private Node<T> minNode(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Return an in-order traversal as a String (each element.toString() on its own line)
    public String printInOrder() {
        StringBuilder sb = new StringBuilder();
        for (T val : this) {
            sb.append(val.toString()).append(System.lineSeparator());
        }
        // remove trailing newline if present
        if (sb.length() > 0) sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }

    // Iterable implementation (in-order iterator)
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<T> {
        private final Stack<Node<T>> stack = new Stack<>();

        BSTIterator() {
            pushLeft(root);
        }

        // push this node and all left children onto the stack
        private void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<T> node = stack.pop();
            pushLeft(node.right);
            return node.data;
        }
    }
}
