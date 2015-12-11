package rebecca.e29.dialogs;

import rebecca.e29.util.Set;

public class MultiAxis extends Atomic implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1622424458272223596L;

	Set<Atomic> Atomics = new Set<Atomic>();

	public synchronized Set<Atomic> getAtomics() {
		return Atomics;
	}

}
