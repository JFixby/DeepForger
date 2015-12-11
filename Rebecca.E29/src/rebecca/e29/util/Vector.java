package rebecca.e29.util;


public class Vector<E> extends java.util.Vector<E> implements  java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6553290574630170293L;

	public synchronized void print() {
		System.out.println("----<" + Vector.class.toString() + "@"
				+ Integer.toHexString(hashCode()) + ">--------------------");

		for (int i = 0; i < this.size(); i++) {
			E el = this.get(i);
			if (el != null) {
				System.out.println("   [" + i + "] <" + el + ">");
			} else {
				System.out.println("   [" + i + "]  null");
			}
		}
		System.out.println();

	}

}
