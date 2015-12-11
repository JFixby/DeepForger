package rebecca.e26.dialogs;

import java.io.Serializable;

import rebecca.e26.HollyShitException;
import rebecca.e26.util.Pool;
import rebecca.e26.util.Set;

public class AChooseMultiSet extends ASet implements Serializable {

	Pool<Integer> default_val = new Set<Integer>();

	public Set<Integer> getDefault() {
		Set<Integer> r = new Set<Integer>();
		r.addAll(this.default_val);
		return r;
	}

	public void setDefault(Set<Integer> Sis) {
		this.default_val.clear();
		for (int i = 0; i < Sis.size(); i++) {
			if (0 <= Sis.get(i) && Sis.get(i) < this.s.size()) {
				this.default_val.add(Sis.get(i));
			} else {
				throw new HollyShitException("Default value " + Sis.get(i)
						+ " is of range! " + s.size());
			}

		}

	}

	@Override
	public void clear_default() {
		default_val.clear();
		// TODO Auto-generated method stub

	}

}
