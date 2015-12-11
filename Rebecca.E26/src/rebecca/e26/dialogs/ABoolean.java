package rebecca.e26.dialogs;

import java.io.Serializable;

public class ABoolean extends Axis implements Serializable {

	private static final long serialVersionUID = -7867122746762810570L;

	Boolean Defv = null;

	public Boolean getDefault() {
		return Defv;
	}

	public void setDefault(Boolean defv) {
		Defv = defv;
	}

}
