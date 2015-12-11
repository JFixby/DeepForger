package rebecca.e26.dialogs;

import javax.xml.bind.JAXBException;


import rebecca.e26.dialogs.xmlmatrix.XPassage;
import rebecca.e26.util.Printable;
import rebecca.e26.util.Vector;


public class Passage extends Vector<Sentence> implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6974572235294262780L;

	String id = "";

	String name = "";

	String info = "";

	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = nn(name);
	}

	protected String nn(String s) {
		if (s == null) {
			s = "";
		}
		return s;
	}

	public String getInfo() {
		return nn(info);
	}

	public void setInfo(String text) {
		this.info = nn(text);
	}

	public String toString() {
		return "id = <" + this.id + "> name = <" + this.name + "> "
				+ super.toString();
	}

	@Override
	public synchronized void print() {
		// TODO Auto-generated method stub

		XPassage x = (rebecca.e26.dialogs.xmlmatrix.ReXMLTransformer
				.transform(this));
		try {
			System.out.println(new String(rebecca.e26.xml.XMLTransmitter
					.Marshall(x)));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Building

	}
}
