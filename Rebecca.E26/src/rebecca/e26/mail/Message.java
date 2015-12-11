package rebecca.e26.mail;

import java.io.Serializable;

import rebecca.e26.RebeccaE26;
import rebecca.e26.util.Map;
import rebecca.e26.util.Printable;
import rebecca.e26.util.Set;

public class Message implements Serializable, Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2439614195407568083L;
	String h;
	Map<String, String> L = new Map<String, String>();
	Map<String, Serializable> A = new Map<String, Serializable>();

	public Message(String Header) {
		h = Header;
	}

	public String getLine(String line_name) {
		return (String) L.get(line_name);
	}

	public String addLine(String line_name, String Line) {
		return L.put(line_name, Line);
	}

	public String getHeader() {
		return h;
	}

	public Serializable getAttachment(String att_name) {
		return A.get(att_name);
	}

	public <T extends Serializable> T getAttachment(String att_name,
			Class<T> clazz) {
		return (T) A.get(att_name);
	}

	public Serializable addAttachment(String att_name, Serializable att) {
		return A.put(att_name, att);
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		print(0);
	}

	@Override
	public void print(int spaces) {
		// TODO Auto-generated method stub

		System.out.println(RebeccaE26.getIndentString(spaces) + "[-Message: <"
				+ this.getHeader() + ">--------]");
		spaces = spaces + 2;
		System.out.println(RebeccaE26.getIndentString(spaces) + "[body]");
		for (int i = 0; i < L.size(); i++) {
			System.out.println(RebeccaE26.getIndentString(spaces + 3) + "[" + i
					+ "] " + L.getKey(i) + " = <" + L.getValue(i) + ">");
		}
		System.out.println(RebeccaE26.getIndentString(spaces) + "[/body]");
		System.out
				.println(RebeccaE26.getIndentString(spaces) + "[attachments]");
		for (int i = 0; i < A.size(); i++) {
			System.out.println(RebeccaE26.getIndentString(spaces + 3) + "[" + i
					+ "] " + A.getKey(i) + " = <" + A.getValue(i) + ">");
		}
		System.out.println(RebeccaE26.getIndentString(spaces)
				+ "[/attachments]");
	}
}
