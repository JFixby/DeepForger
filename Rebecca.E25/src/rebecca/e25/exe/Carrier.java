package rebecca.e25.exe;


import rebecca.e25.RebeccaE;
import rebecca.e25.util.Map;
import rebecca.e25.util.MapArray;
import rebecca.e25.util.Set;

public class Carrier implements Player{

	Map<Executor, Rotor> ER = new MapArray<Executor, Rotor>();
	public static RebeccaE RebeccaE;

	public Rotor plugExecutor(Executor E) {
		Rotor r = RebeccaE.newRotor();
		r.bindExecutor(E);
		ER.put(E, r);
		return r;
	}

	public void unplugExecutor(Executor E) {
		Rotor r = ER.get(E);
		r.Stop();
		ER.remove(E);
	}

	public int Pause() {
		int k = 0;
		for (int i = 0; i < ER.size(); i++) {
			ER.get(i).Pause();
			k++;
		}
		return k;
	}

	@Override
	public int Play() {
		int k = 0;
		for (int i = 0; i < ER.size(); i++) {
			ER.get(i).Play();
			k++;
		}
		return k;
	}

	@Override
	public int Stop() {
		int k = 0;
		for (int i = 0; i < ER.size(); i++) {
			ER.get(i).Stop();
			k++;
		}
		return k;
	}

}
