package edu.kcg.Poker.Table.DataManager;


public class PhasesManager implements PhasesManagerInterface{
	private int currentPhase;
	private int round;

	public int getCurrentPhase() {
		return currentPhase;
	}

	public int getRound() {
		return round;
	}

	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase;
	}

	public void setRound(int round) {
		this.round = round;
	}
}