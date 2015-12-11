package rebecca.e27.util;



public interface Collection<T> {

	public int size();

	public T get(int i);

	public boolean add(T t);

	public void addAll(Collection<? extends T> c);

	public boolean addAll(java.util.Collection<? extends T> c);

	public T remove(int i);

	public boolean removeAll(Collection<?> c);

	public boolean removeAll(java.util.Collection<?> c);

	public boolean remove(Object t);

	public T getLastElement();

	public T getFirstElement();

	public int getIndexOf(Object t);

	public void clear();

	public boolean contains(Object o);

	public boolean isEmpty();

}
