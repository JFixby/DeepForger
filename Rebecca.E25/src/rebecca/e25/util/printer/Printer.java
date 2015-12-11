package rebecca.e25.util.printer;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import rebecca.e25.util.Printable;

public class Printer {

	public static void print(Object O, String packageName) throws JAXBException {

		System.out.println("--------------------------------------------");
		//XMLMarshal x = new XMLMarshal();
		System.out.println(XMLMarshal.Marshall(O, packageName));

	}

	public static void print(Object o) {
		if (o instanceof XMLpossible) {
			XMLpossible p = (XMLpossible) o;
			printNode(p.getNodeTree());
		} else if (o instanceof Printable) {
			Printable p = (Printable) o;
			p.print();
		} else {
			try {
				print(0, o, o.getClass().getPackage().getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(o.toString());
			}
		}
	}

	public static void print(int space, Object O, String packageName)
			throws JAXBException {
		Node n = null;
		String s = null;
		try {
			System.out.println("--------------------------------------------");
			//XMLMarshal x = new XMLMarshal();
			s = XMLMarshal.Marshall(O, packageName);
			//NodeXML N = new NodeXML();
			n = NodeXML.xmlString2Node(s, false);
			printNode(space, n);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	}

	private void printERRNode(Node n) {
		// TODO Auto-generated method stub

	}

	static String s(int k) {
		char c = ' ';
		char[] C = new char[k];
		for (int i = 0; i < k; i++) {
			C[i] = c;
		}
		return String.copyValueOf(C);
	}

	public static void printNode(int space, Node n) {
		// TODO Auto-generated method stub

		n.print0(s(space));
	}

	public static void printNode(Node n) {
		// TODO Auto-generated method stub
		n.print0("");
	}

}
