package rebecca.e10.core;

import java.util.HashMap;
import java.util.Vector;

public abstract class Pipe extends NObject implements Runnable {

	private HashMap<String, Slot> IN;
	private HashMap<String, Slot> OUT;
	private Vector<Slot> vIN;
	private Vector<Slot> vOUT;
	private boolean Damage = false;
	private boolean Paused = false;
	private boolean on = false;
	public String Mission;
	public long t0;

	public Pipe(String Mission) {
		IN = new HashMap<String, Slot>();
		OUT = new HashMap<String, Slot>();
		vIN = new Vector<Slot>();
		vOUT = new Vector<Slot>();
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
	}

	public boolean Paused() {
		return this.Paused;
	}

	public void switchOFF() {
		this.on = false;
	}

	public String getMission() {
		return this.Mission;
		// TODO Auto-generated constructor stub
	}

	public Boolean getState() {
		return this.on;
		// TODO Auto-generated constructor stub
	}

	public void Start() {
		// this.Init();

		this.switchON();
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		while (this.getState()) {

			while (this.Paused) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (FreshIn() & !FreshOut()) {
				t0 = System.currentTimeMillis();
				Execute();
				System.out.println(this.GetName() + ": " + this.Mission
						+ " :      : " + (System.currentTimeMillis() - t0));
				setOutFresh();
				setInFresh(false);
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		WorkFinished();
	}

	private void setInFresh(boolean b) {
		for (int i = 0; i < this.vIN.size(); i++) {
			this.vIN.get(i).SetFresh(b);
		}
	}

	private void setOutFresh(boolean b) {
		for (int i = 0; i < this.vOUT.size(); i++) {
			this.vOUT.get(i).SetFresh(b);
		}
	}

	private void setOutFresh() {
		setOutFresh(true);
	}

	private void setInFresh() {
		setInFresh(true);
	}

	public void WorkFinished() {
		System.out.println(this.GetName()
				+ "-----------------------------------");
		System.out.println(this.GetName() + "Work Finished.");
		PrintInfo();
		System.out.println(this.GetName()
				+ "-----------------------------------");
	}

	public void PrintInfo() {
		System.out.println(this.GetName() + "Pipe: [" + this.GetName() + "]");
		System.out.println(this.GetName() + "Mission: [" + this.getMission()
				+ "]");
	}

	public abstract void Init();

	public abstract void Execute();

	public abstract void ShutDown();

	public Slot getOutSlot(String tring) {

		return this.OUT.get(tring);
	}

	public Slot getInSlot(String tring) {
		return this.IN.get(tring);
	}

	public void removeOutSlot(String tring) {
		this.vOUT.remove(this.getOutSlot(tring));
		this.OUT.remove(tring);
	}

	public void removeInSlot(String tring) {
		this.vIN.remove(this.getOutSlot(tring));
		this.IN.remove(tring);
	}

	public void registerOutSlot(Slot s) {
		// TODO Auto-generated method stub
		s.setWriter(this);
		this.vOUT.add(s);
		this.OUT.put(s.GetName(), s);
	}

	public void registerOutSlot(Slot[] s) {
		// TODO Auto-generated method stub
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				this.registerOutSlot(s[i]);
			}
		}
	}

	public void registerInSlot(Slot s) {
		// if(s!=null){
		s.setReader(this);
		this.vIN.add(s);
		this.IN.put(s.GetName(), s);// }
	}

	public boolean FreshIn() {
		for (int i = 0; i < vIN.size(); i++) {
			if (!this.vIN.get(i).GetFresh()) {
				return false;
			}
		}
		return true;
	}

	public boolean FreshOut() {
		for (int i = 0; i < vOUT.size(); i++) {
			if (this.vOUT.get(i).GetFresh()) {
				return true;
			}
		}
		return false;
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
		this.switchOFF();
		this.ShutDown();
	}

}
