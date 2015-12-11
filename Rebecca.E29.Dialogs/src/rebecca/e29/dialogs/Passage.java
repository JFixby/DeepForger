package rebecca.e29.dialogs;

import rebecca.e29.util.Set;

public class Passage implements  java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6176031300400947181L;
	String name;
	Set<Sentence> Sentences = new Set<Sentence>();

	public synchronized  Set<Sentence> getSentences() {
		return Sentences;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

}
