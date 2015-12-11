package rebecca.e26.util;

import java.io.Serializable;

import rebecca.e26.RebeccaE26;

public class Map<A, B> implements Printable, Serializable {

	private static final long serialVersionUID = 7227313497541883103L;

	Set<A> K = new Set<A>();
	Vector<B> B = new Vector<B>();

	public synchronized void clear() {
		K.clear();
		B.clear();
	}

	public synchronized boolean containsKey(Object key) {
		return K.contains(key);
	}

	public synchronized boolean containsValue(Object value) {
		return B.contains(value);
	}

	public synchronized B get(Object key) {
		int i = K.getIndexOf(key);
		if (i != -1) {
			return this.B.get(i);
		}
		return null;
	}

	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return K.isEmpty();
	}

	public synchronized Set<A> getKeySet() {
		// TODO Auto-generated method stub
		Set<A> S = new Set<A>();
		S.addAll((Pool<A>) K);
		return S;
	}

	public synchronized B put(A key, B value) {
		// TODO Auto-generated method stub
		
		int i = this.K.getIndexOf(key);
		if (i == -1) {
			K.add(key);
			B.add(value);
			return null;
		} else {
			B b = B.get(i);
			B.setElementAt(value, i);
			return b;
		}

	}

	public synchronized void putAll(Map<? extends A, ? extends B> m) {
		// TODO Auto-generated method stub
		for (int i = 0; i < m.size(); i++) {
			A key = m.getKey(i);
			put(key, m.get(key));
		}
	}

	public synchronized A getKey(int i) {
		return K.get(i);
	}

	public synchronized B remove(Object key) {
		int i = this.K.getIndexOf(key);
		if (i == -1) {
			return null;
		}
		K.removeAt(i);
		return B.removeAt(i);

	}

	public synchronized int size() {
		// TODO Auto-generated method stub
		return K.size();
	}

	public synchronized Set<B> getValues() {
		// TODO Auto-generated method stub
		Set<B> S = new Set<B>();
		S.addAll((Pool<B>) B);
		return S;
	}

	public synchronized void print() {
		// TODO Auto-generated method stub
		System.out.println();
		print(0);

	}

	public synchronized void print(int spaces) {
		// TODO Auto-generated method stub
		String I = RebeccaE26.getIndentString(spaces);
		for (int i = 0; i < K.size(); i++) {
			System.out.println(I + "[" + i + "] " + K.get(i) + " :-> "
					+ B.get(i));
		}

	}

	public B getValue(int i) {
		// TODO Auto-generated method stub
		return this.B.get(i);
	}

}
