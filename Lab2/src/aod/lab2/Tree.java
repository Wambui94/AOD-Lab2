package aod.lab2;

public interface Tree<T> {
	public void add(T item);
	public boolean searchFor(T itemsToSearchFor);
	public int size();
	public void clear();
	public boolean remove(T itemToRemove);
}
