package edu.kcg.Poker.Logger;

import edu.kcg.Poker.Table.Table;

abstract public class PokerGameLogger {

	protected Table table;

	public PokerGameLogger(Table table) {
		this.table = table;
	}

	abstract public void beforePhase();
	
	abstract public void afterPhase();
	
	abstract public void beforeFirst();
	
	abstract public void afterFirst();
	
	abstract public void beforeHuman();
	
	abstract public void afterHuman();
	
	abstract public void beforeChance();
	
	abstract public void afterChance();
	
	abstract public void beforeFinal();
	
	abstract public void afterFinal();

	abstract public void setTable(Table table);
	
}
