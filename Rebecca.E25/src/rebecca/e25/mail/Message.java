/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rebecca.e25.mail;

import java.io.Serializable;
import java.util.HashMap;

import rebecca.e25.util.MapArray;
import rebecca.e25.util.Printable;

/**
 * 
 * @author stankevich
 */
public class Message implements  Serializable,Printable{

	/**
	 * ssssssssssssssssss
	 * 
	 */
	private static final long serialVersionUID = -2926768588430921460L;
	private String H = "";
	private MapArray<String, String> Body = new MapArray<String, String>();
	private MapArray<String, Serializable> At = new MapArray<String, Serializable>();

	public Message(String string) {
		this.setHeader(string);
	}

	public void addAttachment(String AttName, Serializable Att) {
		this.At.put(AttName, Att);
	}

	public Serializable getAttachment(String AttName) {
		return this.At.get(AttName);
	}

	public void print() {
		System.out.println(this.toString());
	}

	public String toString(String S) {
		return S + this.toString();
	}

	public String toString() {
		String t = "\n";

		t = t + ("[-Message: <" + this.getHeader() + ">--------]") + "\n";
		t = t + ("[-Body: ") + "\n";

		for (int i = 0; i < Body.size(); i++) {
			t = t
					+ (" [" + i + "] " + Body.getKey(i) + " = <"
							+ cure(this.Body.get(i)) + ">") + "\n";
		}

		t = t + ("[-Attachments: ") + "\n";

		for (int i = 0; i < At.size(); i++) {
			if (At.get(i) != null) {
				t = t
						+ (" ["
								+ i
								+ "] "
								+ At.getKey(i)
								+ " = <"
								+ (At.get(i).getClass().getName() + "@" + Integer
										.toHexString(At.get(i).hashCode())) + ">")
						+ "\n";
			} else {
				t = t
						+ (" [" + i + "] " + At.getKey(i) + " = <" + "%null%" + ">")
						+ "\n";
			}
		}

		t = t + ("[Message END]") + "\n";

		return t;
	}

	private String cure(String string) {
		// TODO Auto-generated method stub
		if (string == null)
			return "null";
		string.replaceAll("/n", "#");
		if (string.length() > 1000)
			return string.substring(0, 1000)+"...";

		return string;
	}

	public String getHeader() {
		return this.H;
	}

	public void addLine(String AttName, String Att) {
		this.Body.put(AttName, Att);
	}

	public String getLine(String AttName) {
		return this.Body.get(AttName);
	}

	public void setHeader(String Header) {
		// TODO Auto-generated method stub
		this.H = Header;
	}

}
