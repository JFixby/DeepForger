package rebecca.e26.util;

import java.util.ArrayList;



import rebecca.e26.RebeccaE26;

public class Vector<E> extends java.util.Vector<E> implements Printable,
		java.io.Serializable, Pool<E> {

	private static final long serialVersionUID = 7021497176887874195L;

	@Override
	public synchronized void print() {
		// TODO Auto-generated method stub
		System.out.println("----------------------");
		this.print(0);

	}

	@Override
	public synchronized void print(int spaces) {
		int n = super.size();
		for (int i = 0; i < n; i++) {
			System.out.println(RebeccaE26.getIndentString(spaces) + "[" + i
					+ "] " + super.get(i).toString());
		}

	}

	@Override
	public void addAll(Pool<E> t) {
		// TODO Auto-generated method stub
		for (int i = 0; i < t.size(); i++) {
			add(t.get(i));
		}

	}
	
	

	@Override
	public boolean containsAll(Pool t) {
		// TODO Auto-generated method stub
		java.util.Collection c = new ArrayList();
		for (int i = 0; i < t.size(); i++) {
			c.add(t.get(i));
		}
		return super.containsAll(c);

	}

	@Override
	public boolean equals(Pool t) {
		// TODO Auto-generated method stub
		if (t.size() != size()) {
			return false;
		}

		for (int i = 0; i < t.size(); i++) {
			if (!RebeccaE26.compareObjects(t.get(i), get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E FirstElement() {
		// TODO Auto-generated method stub
		return super.firstElement();
	}
	
	
	public E getAt(int i){
		return get(i);
	}

	@Override
	public int getIndexOf(Object t) {
		// TODO Auto-generated method stub
		return super.indexOf(t);
	}

	@Override
	public E LastElement() {
		// TODO Auto-generated method stubis
		return super.lastElement();
	}

	@Override
	public E removeAt(int i) {
		// TODO Auto-generated method stub
		return super.remove(i);
	}

	@Override
	public void removeAll(Pool t) {
		for (int i = 0; i < t.size(); i++) {
			Object x = t.get(i);
			while (contains(x)) {
				remove(x);
			}
		}

	}

}
