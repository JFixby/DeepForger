package rebecca.e25.util;

import java.util.Vector;

public interface Set<T> extends java.io.Serializable, Printable {

	public void add(T e);

	public void add(Set<T> e);

	public void clear();

	public boolean contains(T t);

	public int getI(T t);

	public boolean isEmpty();

	public boolean remove(T t);

	public int size();

	public String toString();

	public T get(int i);

	public T getLast();

	public void insertElementAt(T t, int i);

	public void removeElementAt(int i);

}
