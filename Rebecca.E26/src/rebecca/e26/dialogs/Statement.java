package rebecca.e26.dialogs;

import java.io.Serializable;

import rebecca.e26.util.Set;

public class Statement implements Serializable {

	Set<Sentence> passages = new Set<Sentence>();

	public Set<Sentence> getPassages() {
		return passages;
	}

}
