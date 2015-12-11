package rebecca.e25.util.printer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class NodeXML {

	public static String node2XmlString(Node n) throws SAXException,
			TransformerConfigurationException {
		// PrintWriter from a Servlet
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(out);
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		// SAX2.0 ContentHandler.
		TransformerHandler hd = tf.newTransformerHandler();
		Transformer serializer = hd.getTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		// serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "fake.dtd");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		hd.setResult(streamResult);
		hd.startDocument();
		resurect(hd, n);
		hd.endDocument();

		return out.toString();
	}

	private  static  void resurect(TransformerHandler hd, Node n) throws SAXException {
		// TODO Auto-generated method stub

		AttributesImpl atts = new AttributesImpl();

		for (int i = 0; i < n.getAttributes().size(); i++) {
			atts.addAttribute("", "", n.getAttributes().getKey(i), "", n
					.getAttributes().get(i));
		}
		// USERS tag.

		hd.startElement("", "", n.getName(), atts);
		hd.characters(n.getText().toCharArray(), 0,
				n.getText().toCharArray().length);
		// USER tags.

		for (int i = 0; i < n.getSubNodes().size(); i++) {
			resurect(hd, n.getSubNodes().get(i));
		}

		hd.endElement("", "", n.getName());

	}

	public static  Node xmlString2Node(String data, boolean b)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MyHandler handler = new MyHandler(b);

		ByteArrayInputStream is = new ByteArrayInputStream(data.getBytes());

		saxParser.parse(is, handler);
		// .parse(r, handler);

		Node N = handler.getOne();
		clear(N);

		return N;

	}

	private  static  void clear(Node n) {
		// TODO Auto-generated method stub
		if (n.getSubNodes().size() == 0) {

		} else {
			n.setText("");
			for (int i = 0; i < n.getSubNodes().size(); i++) {
				clear(n.getSubNodes().get(i));
			}
		}

	}

	public  static  Node xmlFile2Node(String FilePh, boolean b)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MyHandler handler = new MyHandler(b);

		File f = new File(FilePh);

		saxParser.parse(f, handler);
		// .parse(r, handler);

		return handler.getOne();

	}

	// public Node xmlString2Node(String r, boolean b) throws
	// ParserConfigurationException, SAXException, IOException {
	// // TODO Auto-generated method stub
	// File file = new File(r);
	// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	// DocumentBuilder db = dbf.newDocumentBuilder();
	// Document doc = db.parse(file);
	// doc.getDocumentElement().normalize();
	//
	// Element rt = doc.getDocumentElement();
	//
	// if (b)
	// System.out.println("Root element " + rt.getNodeName());
	// return xmlunpack(rt);
	// }

	private  static  Node xmlunpack(Element rt) {
		// TODO Auto-generated method stub
		Node n = new Node(rt.getNodeName());

		NamedNodeMap a = rt.getAttributes();

		for (int i = 0; i < a.getLength(); i++) {
			n.getAttributes().put(a.item(i).getNodeName(),
					a.item(i).getNodeValue());
		}

		NodeList nodeLst = rt.getChildNodes();

		for (int s = 0; s < nodeLst.getLength(); s++) {

			org.w3c.dom.Node fstNode = nodeLst.item(s);

			if (fstNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

				Element fstElmnt = (Element) fstNode;
				Node v = xmlunpack(fstElmnt);
				n.getSubNodes().add(v);

			}

		}
		return n;
	}
}

class MyHandler extends DefaultHandler {
	Vector<Node> n = new Vector<Node>();
	boolean f = true;
	boolean d;
	Node root;

	public MyHandler(boolean b) {
		super();
		d = b;

	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		// hd.characters(desc[i].toCharArray(),0,desc[i].length());

		char[] x = new char[length];
		for (int i = 0; i < length; i++) {
			x[i] = ch[start + i];

		}
		//
		// this.n.get(0).setText(t);
		// System.out.println(">>> [" + String.valueOf(x) + "] : " + x.length);
		this.n.get(0).setText(this.n.get(0).getText() + String.valueOf(x));
	}

	public Node getOne() {
		// TODO Auto-generated method stub
		return root;
	}

	public void put(Node root) {
		n.insertElementAt(root, 0);
	}

	public void startElement(String uri, String localName, String qName,
			org.xml.sax.Attributes attributes) throws SAXException {

		if (d) {
			System.out.println("<" + qName + ">");
		}
		Node N = new Node(qName);

		if (f) {
			root = N;
			f = false;
		} else {
			this.n.get(0).getSubNodes().add(N);
		}

		for (int i = 0; i < attributes.getLength(); i++) {

			if (d) {
				String s = attributes.getValue(i);
				if (s.length() > 80) {
					s = s.substring(0, 80);
					System.out
							.println(attributes.getQName(i) + ":" + s + "...");
				} else {
					System.out.println(attributes.getQName(i) + ":" + s);
				}

			}

			N.getAttributes().put(attributes.getQName(i),
					attributes.getValue(i));
		}

		this.n.insertElementAt(N, 0);
		// if (qName.equalsIgnoreCase("dir") || qName.equalsIgnoreCase("file"))
		// {}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (d) {
			System.out.println("</" + qName + ">");
		}
		this.n.remove(0);
		// if (qName.equalsIgnoreCase("dir") || qName.equalsIgnoreCase("file"))
		// {}
	}

};
