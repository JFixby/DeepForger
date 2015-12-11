package rebecca.e25.util;


 public class DupletAB<A, B>  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -687713719964548617L;
	private A a;
	private B b;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

}
