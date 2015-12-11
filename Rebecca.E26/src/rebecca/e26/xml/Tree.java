package rebecca.e26.xml;

import rebecca.e26.util.Set;

public class Tree extends Node {

	public Tree(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	Set<Tree> SubTrees = new Set<Tree>();
	Set<TextNode> TextNode = new Set<TextNode>();

	public Set<Tree> getSubTrees() {
		return SubTrees;
	}

	public Set<TextNode> getTextNodes() {
		return TextNode;
	}

}
