package rebecca.e26.dialogs;

import java.io.Serializable;

import rebecca.e26.util.Set;
import rebecca.e26.util.*;

public class MultiAxis extends Axis implements Serializable {

	private static final long serialVersionUID = 7245409746083824670L;

	Set<Axis> axis = new Set<Axis>();

	public Set<Axis> getAxis() {
		return axis;
	}

}
