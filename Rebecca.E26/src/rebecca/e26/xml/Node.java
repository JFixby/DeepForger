package rebecca.e26.xml;

import rebecca.e26.HollyShitException;
import rebecca.e26.util.Map;
import rebecca.e26.util.Set;

public abstract class Node {
	String n;

	public Node(String name) {
		if (name == null) {
			throw new HollyShitException("God damn you!");
		}
		n = name;
	}

	public String getName() {
		return n;
	}

	private Map<String, String> Attributes = new Map<String, String>();

	public Map<String, String> getAttributes() {
		return Attributes;
	}

}
