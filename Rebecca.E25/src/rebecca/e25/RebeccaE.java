package rebecca.e25;

import rebecca.e25.exe.Carrier;
import rebecca.e25.exe.Rotor;
import rebecca.e25.mail.Message;

public class RebeccaE {

	public Carrier newCarrier() {
		Carrier c = new Carrier();
		c.RebeccaE = this;
		return c;
	}

	public Rotor newRotor() {
		return new Rotor();
	}

	public Message newMessage(String header) {
		return new Message(header);
	}
}
