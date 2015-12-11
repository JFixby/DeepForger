package rebecca.e26.util;

import java.util.ArrayList;


public class List<T> implements java.io.Serializable, Pool<T> {

	private static final long serialVersionUID = 3201753224108449944L;

	Vector<T> v = new Vector<T>();

	@Override
	public synchronized boolean add(T t) {
		// TODO Auto-generated method stub
		return v.add(t);
	}

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		v.clear();
	}

	public synchronized String toString() {
		return v.toString();
	}

	@Override
	public synchronized boolean contains(Object t) {
		// TODO Auto-generated method stub
		return v.contains(t);
	}

	@Override
	public synchronized boolean containsAll(Pool t) {
		// TODO Auto-generated method stub
		java.util.Collection c = new ArrayList();
		for (int i = 0; i < t.size(); i++) {
			c.add(t.get(i));
		}
		return v.containsAll(c);
	}

	// @Override
	// public boolean addAll(Pool<T> t) {
	// // TODO Auto-generated method stub
	// boolean b = true;
	// // TODO Auto-generated method stub
	// for (int i = 0; i < t.size(); i++) {
	// b = b && super.add(t.get(i));
	// }
	// return b;
	// }
	//
	// public synchronized boolean containsAll(Pool t) {
	// boolean b = true;
	// // TODO Auto-generated method stub
	// for (int i = 0; i < t.size(); i++) {
	// b = b && super.contains(t.get(i));
	// }
	// return b;
	// }
	//
	public synchronized boolean equals(Pool t) {
		// TODO Auto-generated method stub
		if (v.size() != t.size()) {
			return false;
		}
		for (int i = 0; i < t.size(); i++) {
			if (v.get(i) != t.get(i)) {
				return false;
			}
		}
		return true;
	}

	public synchronized void removeAll(Pool t) {
		// TODO Auto-generated method stub
		for (int i = 0; i < t.size(); i++) {
			while (v.contains(t.get(i))) {
				v.remove(t.get(i));
			}
		}

	}

	public synchronized T FirstElement() {
		// TODO Auto-generated method stub
		return v.firstElement();
	}

	public synchronized T LastElement() {
		// TODO Auto-generated method stub
		return v.lastElement();
	}

	public synchronized int getIndexOf(Object t) {
		// TODO Auto-generated method stub
		return v.indexOf(t);
	}

	@Override
	public synchronized T get(int i) {
		// TODO Auto-generated method stub
		return v.get(i);
	}

	@Override
	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return v.isEmpty();
	}

	@Override
	public synchronized boolean remove(Object t) {
		// TODO Auto-generated method stub
		return v.remove(t);
	}

	@Override
	public synchronized T removeAt(int i) {
		// TODO Auto-generated method stub
		T t = v.get(i);
		v.removeElementAt(i);
		return t;

	}

	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		return v.size();
	}

	@Override
	public synchronized void print() {
		// TODO Auto-generated method stub
		v.print();

	}

	@Override
	public synchronized void print(int spaces) {
		// TODO Auto-generated method stub
		v.print(spaces);

	}

	@Override
	public synchronized void addAll(Pool<T> t) {
		// TODO Auto-generated method stub
		for (int i = 0; i < t.size(); i++) {
			add(t.get(i));
		}

	}

}
