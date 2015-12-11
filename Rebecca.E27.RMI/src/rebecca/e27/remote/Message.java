package rebecca.e27.remote;

import java.io.Serializable;

import rebecca.e26.util.Map;
import rebecca.e27.util.Printable;
import rebecca.e27.util.Printer;
import rebecca.e27.util.lang.HollyShitException;

public class Message implements Serializable, Printable {

	Map<String, String> lines = new Map<String, String>();
	Map<String, Serializable> attachments = new Map<String, Serializable>();

	public Map<String, String> getLines() {
		return lines;
	}

	public Map<String, Serializable> getaAttachments() {
		return attachments;
	}

	private static final long serialVersionUID = -5811859238807596429L;

	@Override
	public void print() {
		throw new HollyShitException("Not implemented yet!");

	}

}
