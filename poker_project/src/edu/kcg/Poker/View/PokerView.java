package edu.kcg.Poker.View;

import edu.kcg.Poker.Table;

abstract public class PokerView {

	protected Table table;

	public PokerView(Table table) {
		this.table = table;
	}

	abstract public void communityCardStatus();

	abstract public void lastPlayStatus(int index);

	abstract public void phaseLast();

	abstract public void phaseStatus(int status);

	abstract public void playerBankroll(int index);

	abstract public void playerHands(int index);

	abstract public void playerStatus(int index);

	abstract public void setTable(Table table);
}
