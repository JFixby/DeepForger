package rebecca.e26.util;



public interface Pool<T> extends Printable {

	public boolean add(T t);

	public void addAll(Pool<T> t);

	public T get(int i);

	public boolean remove(Object t);

	public int getIndexOf(Object t);

	public T FirstElement();

	public T LastElement();

	public T removeAt(int i);

	public void clear();

	public int size();

	public boolean isEmpty();

	public boolean contains(Object t);

	public boolean containsAll(Pool t);

	public boolean equals(Pool t);

	public void removeAll(Pool t);

	
	

}
