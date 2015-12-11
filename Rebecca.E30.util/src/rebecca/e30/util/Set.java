package rebecca.e30.util;

import java.util.Collection;
import java.util.Iterator;

public class Set<E> implements java.util.Set<E>, java.io.Serializable {

	Vector<E> V = new Vector<E>();
	private static final long serialVersionUID = 5403250897200569229L;

	public synchronized void print() {
		System.out.println("----<" + Set.class.toString() + "@"
				+ Integer.toHexString(hashCode()) + ">--------------------");

		for (int i = 0; i < size(); i++) {
			E el = V.get(i);
			if (el != null) {
				System.out.println("   [" + i + "] <" + el + ">");
			} else {
				System.out.println("   [" + i + "]  null");
			}
		}
		System.out.println();

	}

	@Override
	public synchronized boolean add(E e) {
		// TODO Auto-generated method stub
		if (contains(e)) {
			return false;
		} else {
			return V.add(e);
		}
	}

	public synchronized E firstElement() {
		// TODO Auto-generated method stub
		return V.firstElement();
	}

	public synchronized E get(int index) {
		// TODO Auto-generated method stub
		return V.get(index);
	}

	public synchronized int indexOf(Object o) {
		// TODO Auto-generated method stub
		return V.indexOf(o);
	}

	public synchronized E lastElement() {
		// TODO Auto-generated method stub
		return V.lastElement();
	}

	@Override
	public synchronized boolean addAll(Collection<? extends E> c) {
		boolean b = false;
		boolean x;
		Iterator<? extends E> iter = c.iterator();
		while (iter.hasNext()) {
			x = add(iter.next());
			b = b || x;
		}
		return b;
	}

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		V.clear();

	}

	@Override
	public synchronized boolean contains(Object o) {
		// TODO Auto-generated method stub
		return V.contains(o);
	}

	@Override
	public synchronized boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return V.containsAll(c);
	}

	@Override
	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return V.isEmpty();
	}

	@Override
	public synchronized Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return V.iterator();
	}

	@Override
	public synchronized boolean remove(Object o) {
		// TODO Auto-generated method stub
		return V.remove(o);
	}

	@Override
	public synchronized boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return V.removeAll(c);
	}

	@Override
	public synchronized boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return V.retainAll(c);
	}

	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		return V.size();
	}

	@Override
	public synchronized Object[] toArray() {
		// TODO Auto-generated method stub
		return V.toArray();
	}

	@Override
	public synchronized <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return V.toArray(a);
	}

	@Override
	public synchronized boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Set<?>) {
			Set<?> s = (Set<?>) obj;
			if (s.size() != this.size()) {
				return false;
			}

			for (int i = 0; i < s.size(); i++) {
				if (!this.contains(s.get(i))) {
					return false;
				}

			}
			return true;

		} else {

			return (this == obj);
		}
	}

}
