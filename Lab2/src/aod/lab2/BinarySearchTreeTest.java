package aod.lab2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for BinarySearchTree.
 */
class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
    }

    @Test
    void newTreeShouldBeEmpty() {
        assertEquals(0, tree.size());
        assertEquals("[]", tree.toString());
        assertFalse(tree.searchFor(10));
    }

    @Test
    void addShouldIncreaseSizeAndMakeElementSearchable() {
        tree.add(10);
        tree.add(5);
        tree.add(15);

        assertEquals(3, tree.size());
        assertTrue(tree.searchFor(10));
        assertTrue(tree.searchFor(5));
        assertTrue(tree.searchFor(15));
    }

    @Test
    void addShouldIgnoreDuplicates() {
        tree.add(10);
        tree.add(10);
        tree.add(10);

        assertEquals(1, tree.size());
        assertEquals("[10]", tree.toString());
    }

    @Test
    void toStringShouldReturnSortedOrder() {
        tree.add(10);
        tree.add(4);
        tree.add(13);
        tree.add(2);
        tree.add(7);
        tree.add(11);
        tree.add(15);

        assertEquals("[2, 4, 7, 10, 11, 13, 15]", tree.toString());
    }

    @Test
    void clearShouldEmptyTree() {
        tree.add(10);
        tree.add(5);
        tree.add(15);

        tree.clear();

        assertEquals(0, tree.size());
        assertEquals("[]", tree.toString());
        assertFalse(tree.searchFor(10));
    }

    @Test
    void removeShouldReturnFalseWhenElementDoesNotExist() {
        tree.add(10);
        tree.add(5);
        tree.add(15);

        assertFalse(tree.remove(99));
        assertEquals(3, tree.size());
    }

    @Test
    void removeShouldHandleLeafNode() {
        tree.add(10);
        tree.add(5);
        tree.add(15);

        assertTrue(tree.remove(5));
        assertEquals(2, tree.size());
        assertFalse(tree.searchFor(5));
        assertEquals("[10, 15]", tree.toString());
    }

    @Test
    void removeShouldHandleNodeWithOneChild() {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(12);

        assertTrue(tree.remove(15));
        assertEquals(3, tree.size());
        assertFalse(tree.searchFor(15));
        assertEquals("[5, 10, 12]", tree.toString());
    }

    @Test
    void removeShouldHandleNodeWithTwoChildren() {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(12);
        tree.add(20);
        tree.add(11);
        tree.add(13);

        assertTrue(tree.remove(15));
        assertEquals(6, tree.size());
        assertFalse(tree.searchFor(15));
        assertEquals("[5, 10, 11, 12, 13, 20]", tree.toString());
    }

    @Test
    void removeShouldHandleRoot() {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(12);
        tree.add(20);

        assertTrue(tree.remove(10));
        assertEquals(4, tree.size());
        assertFalse(tree.searchFor(10));
        assertEquals("[5, 12, 15, 20]", tree.toString());
    }

    @Test
    void addNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> tree.add(null));
    }

    @Test
    void searchForNullShouldReturnFalse() {
        assertFalse(tree.searchFor(null));
    }

    @Test
    void removeNullShouldReturnFalse() {
        assertFalse(tree.remove(null));
    }
}
