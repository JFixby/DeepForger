package rebecca.e25.util;

import java.util.Vector;

public class SetArray<T> implements Set<T> {

	private static final long serialVersionUID = -2528092768701299247L;
	private Vector<T> V = new Vector<T>(0);

	public void add(T e) {
		if (!V.contains(e)) {
			V.add(e);
		} else {
			int i = V.indexOf(e);
			V.set(i, e);
		}
	}

	public void clear() {
		V.clear();

	}

	public boolean contains(T t) {
		return V.contains(t);
	}

	public int getI(T t) {
		return V.indexOf(t);
	}

	public boolean isEmpty() {
		return V.isEmpty();
	}

	public boolean remove(T t) {
		return V.remove(t);
	}

	public void print() {
		System.out.println(this.toString());
	}

	public int size() {
		return V.size();
	}

	public String toString() {
		return V.toString();
	}

	public T get(int i) {
		return this.V.get(i);
	}

	public void insertElementAt(T t, int i) {
		V.insertElementAt(t, i);
	}

	public void removeElementAt(int i) {
		V.remove(i);
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub
		return V.lastElement();
	}

	@Override
	public void add(Set<T> e) {

		for (int i = 0; i < e.size(); i++) {
			add(e.get(i));
		}
		// TODO Auto-generated method stub

	}

}
