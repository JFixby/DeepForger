package rebecca.e25.util.printer;

import rebecca.e25.util.Map;
import rebecca.e25.util.MapArray;
import rebecca.e25.util.Printable;
import rebecca.e25.util.Set;
import rebecca.e25.util.SetArray;

public class Node implements Printable, XMLpossible {

	String n;
	String t = "";

	public String getText() {
		return t;
	}

	public void setText(String t) {
		this.t = t;
	}

	public Node(String name) {
		n = name;
	}

	public String getName() {
		return n;
	}

	private Map<String, String> Attributes = new MapArray<String, String>();

	private Set<Node> SubNodes = new SetArray<Node>();

	public Map<String, String> getAttributes() {
		return Attributes;
	}

	public Set<Node> getSubNodes() {
		return SubNodes;
	}

	void printAtr(String p, int i) {
		String F = p + "<A> (" + this.Attributes.getKey(i) + ") = [";
		System.out.println(F
				+ this.Attributes.get(i).replaceAll("\n",
						"\n" + s(F.length()) + "|") + "]");
	}

	void printSubNode(String p, int i) {
		this.SubNodes.get(i).print0(p);
	}

	@Override
	public void print() {
		this.print0(" ");
		// TODO Auto-generated method stub

	}

	String s(int k) {
		char c = ' ';
		char[] C = new char[k];
		for (int i = 0; i < k; i++) {
			C[i] = c;
		}
		return String.copyValueOf(C);
	}

	void print0(String p) {

		if (this.SubNodes.size() == 0) {
			String F = p + "<N> (" + this.n + ") = [";
			String tt = this.getText();
			if (tt.length() > 1000) {
				tt = tt.substring(0, 1000) + "................";
			}
			System.out.println(F
					+ tt.replaceAll("\n", "\n" + s(F.length()) + "|") + "]");
		} else {
			System.out.println(p + "  " + "<N> (" + this.n + ")");
		}

		for (int i = 0; i < this.Attributes.size(); i++) {
			printAtr(p + "            ", i);
		}

		for (int i = 0; i < this.SubNodes.size(); i++) {
			printSubNode(p + "      ", i);
		}

	}

	@Override
	public Node getNodeTree() {
		// TODO Auto-generated method stub
		return this;
	}
}
