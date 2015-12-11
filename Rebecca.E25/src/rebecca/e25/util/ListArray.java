package rebecca.e25.util;

import java.util.Vector;

public class ListArray<E> extends Vector<E> implements Printable,
		java.io.Serializable, Cloneable, List<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111383824746112295L;

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public E getLast() {
		// TODO Auto-generated method stub
		int n = this.size();
		if (n > 0) {
			return this.get(n - 1);
		}
		return null;
	}

}
