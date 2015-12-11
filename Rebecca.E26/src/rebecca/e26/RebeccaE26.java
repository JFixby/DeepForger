package rebecca.e26;

import rebecca.e26.util.List;
import rebecca.e26.util.Map;
import rebecca.e26.util.Printable;
import rebecca.e26.util.Set;

public class RebeccaE26 {

	
	public static boolean compareObjects(Object a, Object b) {
		// TODO Auto-generated method stub
		if (b == null && a == null) {
			return true;
		} else if (b != null && a != null) {
			return b.equals(a);
		}
		return false;
	}

	public static String getIndentString(int k) {
		char c = ' ';
		char[] C = new char[k];
		for (int i = 0; i < k; i++) {
			C[i] = c;
		}
		return String.copyValueOf(C);
	}

	public static void print(Object o) {
		if (o instanceof Printable) {
			Printable p = (Printable) o;
			p.print();
		} else {
			System.out.println(o.toString());
		}
	}

}
