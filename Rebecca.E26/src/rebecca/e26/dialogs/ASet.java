package rebecca.e26.dialogs;

import java.io.Serializable;

import rebecca.e26.util.Set;
import rebecca.e26.HollyShitException;
import rebecca.e26.util.*;

public abstract class ASet extends Axis implements Serializable {

	Set<Option> s = new Set<Option>();

	public Set<Option> getOptions() {

		return s;
	}

	// public void setOptions(Set<Option> Sis) {
	// this.s.clear();
	// clear_default();
	// for (int i = 0; i < Sis.size(); i++) {
	//
	// s.add(Sis.get(i));
	//
	// }
	//
	// }

	public abstract void clear_default();

}
