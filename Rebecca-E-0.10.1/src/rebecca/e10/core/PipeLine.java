package rebecca.e10.core;

import java.util.HashMap;
import java.util.Vector;

public abstract class PipeLine extends NObject implements Runnable {

	private HashMap<String, Slot> IN;
	private HashMap<String, Slot> OUT;
	private HashMap<String, Slot> INT;
	private HashMap<String, Pipe> P;

	private Vector<Slot> vIN;
	private Vector<Slot> vOUT;
	private Vector<Slot> vINT;
	private Vector<Pipe> vP;

	private boolean Damage = false;
	private boolean Paused = false;
	private boolean on = false;
	private boolean Inited = false;
	public String Mission;
	public long t0;

	public PipeLine(String Mission) {
		IN = new HashMap<String, Slot>();
		OUT = new HashMap<String, Slot>();
		INT = new HashMap<String, Slot>();
		P = new HashMap<String, Pipe>();

		vIN = new Vector<Slot>();

		vOUT = new Vector<Slot>();

		vINT = new Vector<Slot>();

		vP = new Vector<Pipe>();

		String t = "                                                   ";
		this.Mission = Mission
				+ t.substring(0, 1 + t.length() - Mission.length());
		Damage = false;
		on = false;

	}

	public void switchON() {
		this.on = true;
	}

	public void setPause(boolean b) {
		this.Paused = b;
		for (int i = 0; i < P.size(); i++) {
			this.vP.get(i).setPause(b);
		}
	}

	public boolean Paused() {
		return this.Paused;
	}

	public void switchOFF() {
		this.on = false;
	}

	public String getMission() {
		return this.Mission;
	}

	public Boolean getState() {
		return this.on;
	}

	public void Start() {

		if (this.Inited) {
			this.switchON();
			this.PipeStartAll();
			Thread t = new Thread(this);
			t.start();
		} else {
			this.Init();
			this.Start();
		}

	}

	public void run() {
		while (this.getState()) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		WorkFinished();
	}

	public void WorkFinished() {
		System.out.println("-----------------------------------");
		System.out.println("Work Finished.");
		PrintInfo();
		System.out.println("-----------------------------------");
	}

	public void PrintInfo() {
		System.out.println("PipeLine: [" + this.GetName() + "]");
		System.out.println("Mission: [" + this.getMission() + "]");
	}

	public void Init() {
		this.Inited = true;
		PipeInitAll();
	}

	public void PipeInitAll() {
		for (int i = 0; i < P.size(); i++) {
			this.vP.get(i).Init();
		}

	}

	public void PipeStartAll() {
		for (int i = 0; i < P.size(); i++) {
			this.vP.get(i).Start();
		}

	}

	public void ShutDown() {
		this.Inited = false;
		PipeShutDownAll();
	}

	public void PipeShutDownAll() {
		for (int i = 0; i < P.size(); i++) {
			this.vP.get(i).ShutDown();

		}

	}

	public void PipeStopAll() {
		for (int i = 0; i < P.size(); i++) {
			this.vP.get(i).Stop();
		}

	}

	public Slot getOutSlot(String tring) {
		return this.OUT.get(tring);
	}

	public Slot getInternalSlot(String tring) {
		return this.INT.get(tring);
	}

	public Slot getInSlot(String tring) {
		return this.IN.get(tring);
	}

	public Pipe getPipe(String tring) {
		return this.P.get(tring);
	}

	public void removeOutSlot(String tring) {
		this.vOUT.remove(this.getOutSlot(tring));
		this.OUT.remove(tring);
		removeInternalSlot(tring);
	}

	public void removePipe(String tring) {
		this.vP.remove(this.getPipe(tring));
		this.P.remove(tring);

	}

	public void removeInSlot(String tring) {
		this.vIN.remove(this.getInSlot(tring));
		this.IN.remove(tring);
		removeInternalSlot(tring);
	}

	public void removeInternalSlot(String tring) {
		this.vINT.remove(this.getInternalSlot(tring));
		this.INT.remove(tring);
	}

	public void registerOutSlot(Slot s) {
		this.vOUT.add(s);
		this.OUT.put(s.GetName(), s);
		registerInternalSlot(s);
	}

	public void registerInSlot(Slot s) {
		this.vIN.add(s);
		this.IN.put(s.GetName(), s);
		registerInternalSlot(s);
	}

	public void registerPipe(Pipe p) {
		this.vP.add(p);
		this.P.put(p.GetName(), p);

	}

	public void registerPipe(Pipe[] pi) {
		for (int i = 0; i < pi.length; i++) {
			if (pi[i] != null) {
				this.registerPipe(pi[i]);
			}
		}

	}

	public void registerInternalSlot(Slot s) {
		this.vINT.add(s);
		this.INT.put(s.GetName(), s);
	}

	public void registerInternalSlot(Slot[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				this.registerInternalSlot(s[i]);
			}
		}
	}

	public void setDamage() {
		setDamage(true);

	}

	public void setDamage(boolean b) {
		this.Damage = b;
		if (b) {
			DamageSignal();
		}
	}

	public Boolean getDamage() {
		return this.Damage;

	}

	public void DamageSignal() {
		System.out.println("-----------------------------------");
		System.out.println("Critical Damage!");
		PrintInfo();
		System.out.println("-----------------------------------");
		this.switchOFF();

	}

	public void Stop() {

		ShutDown();
		PipeStopAll();
		switchOFF();
	}

}
