package rebecca.e25.util;

import java.util.Vector;



public class MapArray<K, V> implements Map<K, V> {

	private static final long serialVersionUID = -2385369034039400323L;
	private Vector<DupletAB<K, V>> V = new Vector<DupletAB<K, V>>(0);

	// /////////////////////////////////
	public void clear() {
		this.V.clear();
	}

	// /////////////////////////////////
	public boolean containsKey(K key) {
		return this.getI(key) != -1;
	}

	// /////////////////////////////////
	public boolean containsValue(V value) {
		for (int i = 0; i < V.size(); i++) {
			if (V.get(i).getB().equals(value)) {
				return true;
			}
		}
		return false;
	}

	// ///////////////////////////////////
	public V get(K key) {
		int k = this.getI(key);
		if (k != -1) {
			return V.get(k).getB();
		} else {
			return null;
		}
	}

	// ///////////////////////////////////
	public V get(int i) {
		return V.get(i).getB();
	}

	// ///////////////////////////////////
	public K getKey(int i) {
		return V.get(i).getA();
	}

	// ///////////////////////////////////
	public int getI(K key) {
		for (int i = 0; i < V.size(); i++) {
			if (V.get(i).getA().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	// ///////////////////////////////////
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return V.size() == 0;
	}

	// ///////////////////////////////////
	public Set<K> keys() {
		Set<K> KV = new SetArray<K>();
		for (int i = 0; i < V.size(); i++) {
			KV.add(V.get(i).getA());
		}
		return KV;
	}

	// ///////////////////////////////////
	public void put(K key, V value) {
		int k = this.getI(key);
		// TODO Auto-generated method stub
		if (k == -1) {
			DupletAB<K, V> D = new DupletAB<K, V>();
			D.setA(key);
			D.setB(value);
			V.add(D);
		} else {
			V.get(k).setB(value);
		}
	}

	// ///////////////////////////////////
	public void putAll(Map<? extends K, ? extends V> m) {
		for (int i = 0; i < m.size(); i++) {
			put(m.getKey(i), m.get(i));
		}

	}

	// ///////////////////////////////////
	public void remove(K key) {
		int k = this.getI(key);
		if (k != -1) {
			V.remove(k);
		}
	}

	// ///////////////////////////////////
	public int size() {
		return V.size();
	}

	// ///////////////////////////////////
	public Set<V> values() {
		Set<V> KV = new SetArray<V>();
		for (int i = 0; i < V.size(); i++) {
			KV.add(V.get(i).getB());
		}
		return KV;
	}

	public void print() {
		System.out.println(this.toString());
	}

	public String toString() {
		String t = "";

		for (int i = 0; i < this.size(); i++) {
			t = t + "[" + i + "]" + " " + this.getKey(i) + " :->" + " "
					+ this.get(i) + " \n";

		}

		return t;

	}

}
