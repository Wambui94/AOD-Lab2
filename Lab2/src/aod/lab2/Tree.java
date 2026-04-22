package aod.lab2;

public interface Tree<T> {
	 /**
     * Adds a new item to the tree if it does not already exist.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
	public void add(T item);
	
	/**
     * Searches for an item in the tree.
     *
     * @param itemToSearchFor item to search for
     * @return true if found, otherwise false
     */
	public boolean searchFor(T itemsToSearchFor);
	
	/**
     * Returns the number of items in the tree.
     *
     * @return tree size
     */
	public int size();
	
	 /**
     * Removes all items from the tree.
     */
	public void clear();
	
	/**
     * Removes an item from the tree if it exists.
     *
     * @param itemToRemove item to remove
     * @return true if removed, otherwise false
     */
	public boolean remove(T itemToRemove);
}
