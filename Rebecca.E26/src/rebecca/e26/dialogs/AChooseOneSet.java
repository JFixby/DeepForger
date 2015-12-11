package rebecca.e26.dialogs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import rebecca.e26.HollyShitException;

@XmlRootElement
public class AChooseOneSet extends ASet implements Serializable {

	int default_val = -1;

	public int getDefault() {
		return default_val;
	}

	public void setDefault(int default_val) {
		if (this.s.size() > default_val) {
			if (default_val < -1) {
				default_val = -1;
			}
			this.default_val = default_val;
		} else {
			this.default_val = -1;
			throw new HollyShitException("Default value " + default_val
					+ " is of range! ( 0 ; " + s.size() + " )");

		}
	}

	@Override
	public void clear_default() {
		default_val = -1;
		// TODO Auto-generated method stub

	}

}
