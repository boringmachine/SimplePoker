package edu.kcg.Poker.Table.DataManager;

public interface ChipsManagerInterFace {
	
	public void addPot(int x);

	public int getAnty();

	public int getLimit() ;

	public int getMaxRaise();

	public int getPot();

	public void setAnty(int anty);

	public void setLimit(int limit);

	public void setMaxRaise(int maxBet);

	public void setPot(int pot);
}