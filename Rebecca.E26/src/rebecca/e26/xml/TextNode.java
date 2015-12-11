package rebecca.e26.xml;

public class TextNode extends Node {

	public TextNode(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	String text = "";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (text == null) {
			text = "";
		}
		this.text = text;
	}
}
