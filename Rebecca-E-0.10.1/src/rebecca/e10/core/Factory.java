package rebecca.e10.core;

import java.util.HashMap;
import java.util.Vector;

public abstract class Factory extends NObject implements Runnable {

	private HashMap<String, PipeLine> PL;
	private HashMap<String, Slot> SL;
	// private HashMap<String, Slot> INT;
	// private HashMap<String, Pipe> P;

	private Vector<PipeLine> vPL;
	private Vector<Slot> vSL;
	// private Vector<Slot> vINT;
	// private Vector<Pipe> vP;

	private boolean Damage = false;
	private boolean Paused = false;
	private boolean on = false;
	private boolean Inited = false;
	public String Mission;
	public long t0;

	public Factory(String Name, String Mission) {
		super(Name);
		PL = new HashMap<String, PipeLine>();
		SL = new HashMap<String, Slot>();

		vPL = new Vector<PipeLine>();
		vSL = new Vector<Slot>();

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
		for (int i = 0; i < this.PL.size(); i++) {
			this.PL.get(i).setPause(b);
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
			this.PipeLinesStartAll();
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
				System.gc();
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
		System.out.println("Factory: [" + this.GetName() + "]");
		System.out.println("Mission: [" + this.getMission() + "]");
		System.out.println("PipeLines:");
		for (int i = 0; i < PL.size(); i++) {
			this.vPL.get(i).PrintInfo();
			System.out.println("----------------");
		}
	}

	public void Init() {
		this.Inited = true;
		PipeLinesInitAll();
	}

	public void PipeLinesInitAll() {
		for (int i = 0; i < PL.size(); i++) {
			this.vPL.get(i).Init();
		}

	}

	public void PipeLinesStartAll() {
		for (int i = 0; i < PL.size(); i++) {
			this.vPL.get(i).Start();
		}

	}

	public void ShutDown() {
		this.Inited = false;
		PipeLinesShutDownAll();
	}

	public void PipeLinesShutDownAll() {
		for (int i = 0; i < PL.size(); i++) {
			this.vPL.get(i).ShutDown();

		}

	}

	public void PipeLinesStopAll() {
		for (int i = 0; i < PL.size(); i++) {
			this.vPL.get(i).Stop();
		}

	}

	public Slot getSlot(String tring) {
		return this.SL.get(tring);
	}

	public PipeLine getPipeLine(String tring) {
		return this.PL.get(tring);
	}

	public void removeSlot(String tring) {
		this.vSL.remove(this.getSlot(tring));
		this.SL.remove(tring);

	}

	public void removePipe(String tring) {
		this.vPL.remove(this.getPipeLine(tring));
		this.PL.remove(tring);

	}

	public void registerSlot(Slot s) {
		this.vSL.add(s);
		this.SL.put(s.GetName(), s);

	}

	public void registerPipeLine(PipeLine p) {
		this.vPL.add(p);
		this.PL.put(p.GetName(), p);

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
		PipeLinesStopAll();
		switchOFF();
	}

}
