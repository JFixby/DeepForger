package rebecca.e26.util;

import rebecca.e26.RebeccaE26;

public class Set<E> implements java.io.Serializable, Pool<E> {

	static final long serialVersionUID = -6183454189705025259L;

	public synchronized boolean add(E e) {
		if (contains(e)) {
			return false;
		} else {
			return c.add(e);
		}
	}

	public synchronized boolean equals(Set t) {
		if (c.size() != t.size()) {
			return false;
		}
		Pool p = t;
		return containsAll(p);
	}

	Vector<E> c = new Vector<E>();

	@Override
	public synchronized void addAll(Pool<E> t) {
		// TODO Auto-generated method stub
		for (int i = 0; i < t.size(); i++) {
			add(t.get(i));
		}

	}
	
	
	public  Vector<E> getAll() {
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		c.clear();
	}

	@Override
	public synchronized boolean contains(Object t) {
		// TODO Auto-generated method stub
		return c.contains(t);
	}

	@Override
	public synchronized boolean containsAll(Pool t) {
		// TODO Auto-generated method stub
		return c.containsAll(t);
	}

	@Override
	public synchronized boolean equals(Pool t) {
		// TODO Auto-generated method stub
		return c.equals(t);
	}

	@Override
	public synchronized E get(int i) {
		// TODO Auto-generated method stub
		return c.get(i);
	}

	@Override
	public synchronized E FirstElement() {
		// TODO Auto-generated method stub
		return c.firstElement();
	}

	@Override
	public synchronized int getIndexOf(Object t) {
		// TODO Auto-generated method stub
		return c.getIndexOf(t);
	}

	@Override
	public synchronized E LastElement() {
		// TODO Auto-generated method stub
		return c.LastElement();
	}

	@Override
	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return c.isEmpty();
	}

	@Override
	public synchronized boolean remove(Object t) {
		// TODO Auto-generated method stub
		return c.remove(t);
	}

	@Override
	public synchronized E removeAt(int i) {
		// TODO Auto-generated method stub
		return c.removeAt(i);
	}

	@Override
	public synchronized void removeAll(Pool t) {
		for (int i = 0; i < t.size(); i++) {
			remove(t.get(i));
		}
	}

	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		return c.size();
	}

	@Override
	public synchronized void print() {
		// TODO Auto-generated method stub
		System.out.println("----------------------");
		this.print(0);

	}

	@Override
	public synchronized void print(int spaces) {
		int n = c.size();
		for (int i = 0; i < n; i++) {
			System.out.println(RebeccaE26.getIndentString(spaces) + "[" + i
					+ "] " + c.get(i).toString());
		}

	}

}
