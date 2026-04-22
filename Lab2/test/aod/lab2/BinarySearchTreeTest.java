package aod.lab2;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.classfile.attribute.CompilationIDAttribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for BinarySearchTree.
 */
class BinarySearchTreeTest {
	
	private static final int FIXTURE[] = {4,2,6,7,5};
	
	private BinarySearchTree<Integer> emptyTree;
    private BinarySearchTree<Integer> dataTree;
    
    @BeforeEach
    void setUp() {
    	emptyTree = new BinarySearchTree<Integer>();
    	dataTree = new BinarySearchTree<>();
    	
    	for (int i : FIXTURE) {
			dataTree.add(i);
		}
    }

    @Test
    void newTreeShouldBeEmpty() {
        assertEquals(0, emptyTree.size());
    }
   
    @Test
    void sizeOnEmptyTree() {
    	assertEquals(0, emptyTree.size());
    }
    
    @Test
    void sizeOnNonEmptyTree() {
    	assertEquals(FIXTURE.length, dataTree.size());
    }
    

    @Test
    void addShouldIncreaseSizeAndMakeElementSearchable() {
    	emptyTree.add(10);
        emptyTree.add(5);
        emptyTree.add(15);

        assertEquals(3, emptyTree.size());
        assertTrue(emptyTree.searchFor(10));
        assertTrue(emptyTree.searchFor(5));
        assertTrue(emptyTree.searchFor(15));
    }

    @Test
    void addShouldIgnoreDuplicates() {
        dataTree.add(10);
        dataTree.add(10);
        dataTree.add(10);

        assertEquals(6, dataTree.size());
        assertEquals("[2, 4, 5, 6, 7, 10]", dataTree.toString());
    }

    @Test
    void toStringShouldReturnSortedOrder() {
        dataTree.add(10);
        dataTree.add(4);
        dataTree.add(13);
        dataTree.add(2);
        dataTree.add(7);
        dataTree.add(11);
        dataTree.add(15);

        assertEquals("[2, 4, 5, 6, 7, 10, 11, 13, 15]", dataTree.toString());
    }

    @Test
    void clearShouldEmptyTree() {
    	dataTree.clear();

        assertEquals(0, dataTree.size());

        for (int value : FIXTURE) {
            assertFalse(dataTree.searchFor(value));
        }
        
    }
    
    @Test
    void clearShouldKeepEmptyTreeEmpty() {
        emptyTree.clear();

        assertEquals(0, emptyTree.size());
    }

    @Test
    void removeShouldReturnFalseWhenElementDoesNotExist() {
    	int oldSize = dataTree.size();

        assertFalse(dataTree.remove(999));
        assertEquals(oldSize, dataTree.size());

        for (int value : FIXTURE) {
            assertTrue(dataTree.searchFor(value));
        }
    }

    @Test
    void removeShouldHandleLeafNode() {
    	int valueToRemove = 5; // leaf in fixture
        int oldSize = dataTree.size();

        assertTrue(dataTree.remove(valueToRemove));
        assertEquals(oldSize - 1, dataTree.size());
        assertFalse(dataTree.searchFor(valueToRemove));

        for (int value : FIXTURE) {
            if (value != valueToRemove) {
                assertTrue(dataTree.searchFor(value));
            }
        }
       
    }

    @Test
    void removeShouldHandleNodeWithOneChild() {
    	// First remove 5, then 6 only has child 7 left
        assertTrue(dataTree.remove(5));

        int oldSize = dataTree.size();
        int valueToRemove = 6;

        assertTrue(dataTree.remove(valueToRemove));
        assertEquals(oldSize - 1, dataTree.size());
        assertFalse(dataTree.searchFor(valueToRemove));

        assertTrue(dataTree.searchFor(4));
        assertTrue(dataTree.searchFor(2));
        assertTrue(dataTree.searchFor(7));
        
    }

    @Test
    void removeShouldHandleNodeWithTwoChildren() {
    	// First remove 5, then 6 only has child 7 left
        assertTrue(dataTree.remove(5));

        int oldSize = dataTree.size();
        int valueToRemove = 6;

        assertTrue(dataTree.remove(valueToRemove));
        assertEquals(oldSize - 1, dataTree.size());
        assertFalse(dataTree.searchFor(valueToRemove));

        assertTrue(dataTree.searchFor(4));
        assertTrue(dataTree.searchFor(2));
        assertTrue(dataTree.searchFor(7));
        
    }

    @Test
    void removeShouldHandleRoot() {
    	int rootValue = 4;
        int oldSize = dataTree.size();

        assertTrue(dataTree.remove(rootValue));
        assertEquals(oldSize - 1, dataTree.size());
        assertFalse(dataTree.searchFor(rootValue));

        for (int value : FIXTURE) {
            if (value != rootValue) {
                assertTrue(dataTree.searchFor(value));
            }
        }
       
    }

    @Test
    void addNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> dataTree.add(null));
    }

    @Test
    void searchForNullShouldReturnFalse() {
        assertFalse(dataTree.searchFor(null));
    }
    void searchForShouldReturnTrueForFixtureValues() {
        for (int value : FIXTURE) {
            assertTrue(dataTree.searchFor(value));
        }
    }
    
    @Test
    void searchForShouldReturnFalseForMissingValues() {
        int[] missingValues = {1, 3, 8, 10, 99};

        for (int value : missingValues) {
            assertFalse(dataTree.searchFor(value));
        }
    }

    @Test
    void removeNullShouldReturnFalse() {
        assertFalse(dataTree.remove(null));
    }
    
    @Test
    void sizeShouldEqualFixtureLength() {
        assertEquals(FIXTURE.length, dataTree.size());
    }
    @Test
    void sizeShouldIncreaseWhenAddingNewElements() {
        int oldSize = emptyTree.size();

        emptyTree.add(10);
        emptyTree.add(5);
        emptyTree.add(15);

        assertEquals(oldSize + 3, emptyTree.size());
    }
}
