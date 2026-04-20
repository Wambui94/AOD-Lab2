package aod.lab2;

import java.util.StringJoiner;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private BSTNode root;
    private int size;

    /**
     * Creates an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Adds a new item to the tree if it does not already exist.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        root = addRecursive(root, item);
    }

    /**
     * Recursive helper method for add.
     *
     * Base case:
     * If current node is null, create a new node.
     *
     * Recursive step:
     * Go left or right depending on comparison result.
     *
     * Duplicate values are ignored.
     *
     * @param current current node
     * @param item item to insert
     * @return updated subtree root
     */
    private BSTNode addRecursive(BSTNode current, T item) {
        if (current == null) {
            size++;
            return new BSTNode(item);
        }

        int comparison = item.compareTo(current.item);

        if (comparison < 0) {
            current.left = addRecursive(current.left, item);
        } else if (comparison > 0) {
            current.right = addRecursive(current.right, item);
        }

        return current;
    }

    /**
     * Searches for an item in the tree.
     *
     * @param itemToSearchFor item to search for
     * @return true if found, otherwise false
     */
    public boolean searchFor(T itemToSearchFor) {
        if (itemToSearchFor == null) {
            return false;
        }

        return searchRecursive(root, itemToSearchFor);
    }

    /**
     * Recursive helper method for search.
     *
     * @param current current node
     * @param target item to search for
     * @return true if found
     */
    private boolean searchRecursive(BSTNode current, T target) {
        if (current == null) {
            return false;
        }

        int comparison = target.compareTo(current.item);

        if (comparison == 0) {
            return true;
        }

        if (comparison < 0) {
            return searchRecursive(current.left, target);
        }

        return searchRecursive(current.right, target);
    }

    /**
     * Returns the number of items in the tree.
     *
     * @return tree size
     */
    public int size() {
        return size;
    }

    /**
     * Removes all items from the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the tree contents in sorted order.
     *
     * Uses in-order traversal.
     *
     * @return string representation of tree
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        buildInOrderString(root, joiner);
        return joiner.toString();
    }

    /**
     * Recursive helper method for in-order traversal.
     *
     * @param current current node
     * @param joiner collects values into a string
     */
    private void buildInOrderString(BSTNode current, StringJoiner joiner) {
        if (current == null) {
            return;
        }

        buildInOrderString(current.left, joiner);
        joiner.add(current.item.toString());
        buildInOrderString(current.right, joiner);
    }

    /**
     * Removes an item from the tree if it exists.
     *
     * @param itemToRemove item to remove
     * @return true if removed, otherwise false
     */
    public boolean remove(T itemToRemove) {
        if (itemToRemove == null || !searchFor(itemToRemove)) {
            return false;
        }

        root = removeRecursive(root, itemToRemove);
        size--;
        return true;
    }

    /**
     * Recursive helper method for remove.
     *
     * Cases:
     * 1. No children -> return null
     * 2. One child -> return child
     * 3. Two children -> replace with inorder successor
     *
     * @param current current node
     * @param target item to remove
     * @return updated subtree root
     */
    private BSTNode removeRecursive(BSTNode current, T target) {
        if (current == null) {
            return null;
        }

        int comparison = target.compareTo(current.item);

        if (comparison < 0) {
            current.left = removeRecursive(current.left, target);
        } else if (comparison > 0) {
            current.right = removeRecursive(current.right, target);
        } else {

            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            BSTNode successor = findMin(current.right);
            current.item = successor.item;
            current.right = removeRecursive(current.right, successor.item);
        }

        return current;
    }

    /**
     * Finds the smallest node in a subtree.
     *
     * @param current subtree root
     * @return node with smallest value
     */
    private BSTNode findMin(BSTNode current) {
        BSTNode temp = current;

        while (temp.left != null) {
            temp = temp.left;
        }

        return temp;
    }

    /**
     * Inner node class used in the tree.
     */
    private class BSTNode {

        private T item;
        private BSTNode left;
        private BSTNode right;

        /**
         * Creates a node with a value.
         *
         * @param item stored value
         */
        BSTNode(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
        }
    }
}