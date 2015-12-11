package rebecca.e25.fsm;

import rebecca.e25.mail.Message;
import rebecca.e25.util.*;

public class Switch implements FSM {

	Set<String> States = new SetArray<String>();
	Map<String, Job> Jobs = new MapArray<String, Job>();
	Job noState = null;

	public void addState(String St) {
		this.States.add(St);
	}

	public void bindJob(String State, Job J) {
		if (!this.States.contains(State)) {
			this.States.add(State);
		}
		this.Jobs.put(State, J);

	}

	public void bindNoStateJob(Job J) {
		this.noState = J;
	}

	@Override
	public void EatMessage(Message M) {
		// TODO Auto-generated method stub
		String s = M.getHeader();
		if (this.States.contains(s)) {
			Job j = this.Jobs.get(s);
			if (j != null) {
				j.DoJob(M);
			} else if (this.noState != null) {
				this.noState.DoJob(M);
			}
		}

	}

}
