package edu.kcg.Poker.Table.DataManager;

public interface PhasesManagerInterface {
	public static final int PREFLOP = 0, FLOP = 1, TURN = 2, RIVER = 3,
			SHOWDOWN = 4;

	public int getCurrentPhase();

	public int getRound();

	public void setCurrentPhase(int currentPhase);

	public void setRound(int round);
}
