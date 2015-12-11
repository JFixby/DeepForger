package rebecca.e30.util;

import java.util.Collection;
import java.util.Iterator;

public class Map<K, V> implements java.util.Map<K, V>, java.io.Serializable {

	private static final long serialVersionUID = 5590712271597883271L;
	transient volatile Vector<K> K = new Vector<K>();
	transient volatile Vector<V> V = new Vector<V>();

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		K.clear();
		V.clear();
	}

	@Override
	public synchronized boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Map<?, ?>) {
			Map<?, ?> s = (Map<?, ?>) obj;
			if (!(this.keySet().equals(s.keySet()))) {
				return false;
			}

			for (int i = 0; i < size(); i++) {
				if (!compare(this.getVal(i), s.get(this.getKey(i)))) {
					return false;
				}

			}

			return true;

		} else {

			return (this == obj);
		}
	}

	private boolean compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		if (o1 == o2) {
			return true;
		}
		if (o1 != null) {
			return o1.equals(o2);
		}

		return false;
	}

	public synchronized void print() {
		System.out.println("----<" + Map.class.toString() + "@"
				+ Integer.toHexString(hashCode()) + ">--------------------");

		for (int i = 0; i < this.size(); i++) {
			K el = K.get(i);
			V vl = V.get(i);

			if (el == null & vl == null) {
				System.out.println("   [" + i + "]  null |->  null");
			}

			else if (el == null & vl != null) {
				System.out.println("   [" + i + "]  null |-> <" + vl + ">");
			}

			else if (el != null & vl != null) {
				System.out.println("   [" + i + "] <" + el + "> |-> <" + vl
						+ ">");
			} else if (el != null & vl == null) {
				System.out.println("   [" + i + "] <" + el + "> |->  null");
			}

		}
		System.out.println();
	}

	@Override
	public synchronized boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return K.contains(key);
	}

	@Override
	public synchronized boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return V.contains(value);
	}

	@Override
	public synchronized Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		Set<java.util.Map.Entry<K, V>> S = new Set<java.util.Map.Entry<K, V>>();
		for (int i = 0; i < this.size(); i++) {
			java.util.Map.Entry<K, V> e = new Entry<K, V>(this.getKey(i), this
					.getVal(i));
			S.add(e);
		}
		return S;
	}

	public synchronized V getVal(int i) {
		// TODO Auto-generated method stub
		return V.get(i);
	}

	public synchronized K getKey(int i) {
		// TODO Auto-generated method stub
		return K.get(i);
	}

	@Override
	public synchronized V get(Object key) {
		// TODO Auto-generated method stub
		int k = this.K.indexOf(key);
		if (k == -1) {
			return null;
		}
		return V.get(k);
		// return V.get(index);
	}

	@Override
	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return K.isEmpty();
	}

	@Override
	public synchronized Set<K> keySet() {
		// TODO Auto-generated method stub
		Set<K> s = new Set<K>();
		s.addAll(K);
		return s;
	}

	@Override
	public synchronized V put(K key, V value) {
		// TODO Auto-generated method stub
		int k = this.K.indexOf(key);
		if (k == -1) {
			K.add(key);
			V.add(value);
			return null;
		}
		V e = V.get(k);
		V.setElementAt(value, k);
		return e;

	}

	@Override
	public synchronized void putAll(java.util.Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		Iterator<? extends K> i = m.keySet().iterator();
		while (i.hasNext()) {
			K k = i.next();
			put(k, m.get(k));
		}

	}

	@Override
	public synchronized V remove(Object key) {
		// TODO Auto-generated method stub
		int k = K.indexOf(key);
		if (k != -1) {
			K.remove(k);
			return V.remove(k);
		}
		return null;
	}

	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		return K.size();
	}

	@Override
	public synchronized Collection<V> values() {
		// TODO Auto-generated method stub
		return V;
	}

	static class Entry<K, V> implements java.util.Map.Entry<K, V>, java.io.Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6673144464610372333L;
		private K k;
		private V v;

		public Entry(K key, V val) {
			k = key;
			v = val;
			// TODO Auto-generated constructor stub
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return k;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return v;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			V x = v;
			v = value;
			return x;
		}

		@Override
		public String toString() {
			return "( " + this.getKey() + " ; " + this.getValue() + " )";
		}

	}

}
