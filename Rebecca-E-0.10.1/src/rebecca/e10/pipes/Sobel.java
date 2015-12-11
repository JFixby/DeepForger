package rebecca.e10.pipes;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;
import rebecca.e10.datasets.ObrazC1;

public class Sobel extends Pipe {

	private Slot<ObrazC1> input;
	private Slot<ObrazC1> output;
	ObrazC1 I = null;
	ObrazC1 O = null;

	public Sobel(Slot<ObrazC1> input, Slot<ObrazC1> output) {
		super("Obraz -> Sobel ->Obraz");

		if ((input != null) & (output != null)) {

			this.input = input;
			this.registerInSlot(input);

			this.output = output;
			this.registerOutSlot(output);

			// I=new ObrazC3();

		}
		;

	}

	public void Execute() {

		I = this.input.GetDATA(this);

		double valr = 0;
		double valg = 0;
		double valb = 0;

		O = new ObrazC1(I.getHeight(), I.getWidth());

		for (int i = 0; i < I.getHeight(); i++) {
			for (int j = 0; j < I.getWidth(); j++) {
				valr = (I.getVal(i + 1, j) - I.getVal(i - 1, j))
						* (I.getVal(i + 1, j) - I.getVal(i - 1, j))
						+ (I.getVal(i, j + 1) - I.getVal(i, j - 1))
						* (I.getVal(i, j + 1) - I.getVal(i, j - 1));
				valr = Math.sqrt(valr);
				if (valr > 255) {
					valr = 255;
				}
				O.setVal(i, j, (char) valr);

			}
		}

		this.output.PushDATA(O, this);
	}

	public void Init() {

	}

	public void ShutDown() {
	}

}
