package edu.kcg.Poker.Table.DataManager;


public class ChipsManager implements ChipsManagerInterFace{
	private int anty;
	private int limit;
	private int maxRaise;
	private int pot;

	public ChipsManager() {
		anty = 1;
		limit = Integer.MAX_VALUE;
		pot = 0;
		maxRaise = 0;
	}

	public void addPot(int x) {
		pot += x;
	}

	public int getAnty() {
		return anty;
	}

	public int getLimit() {
		return limit;
	}

	public int getMaxRaise() {
		return maxRaise;
	}

	public int getPot() {
		return pot;
	}

	public void setAnty(int anty) {
		this.anty = anty;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setMaxRaise(int maxBet) {
		this.maxRaise = maxBet;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}
}
