package edu.kcg.Poker.Factory;

import edu.kcg.Poker.Table;
import edu.kcg.Poker.View.DefaultPokerView;
import edu.kcg.Poker.View.PokerView;

public class TableAndViewFactory {
	protected Table table;
	protected PokerView view;

	public PokerView createPokerView() {
		if (this.table == null) {
			this.table = new Table();
		}
		PokerView view = new DefaultPokerView(this.table);
		this.view = view;
		return this.view;
	}

	public PokerView createPokerView(Table table) {
		PokerView view = new DefaultPokerView(table);
		this.view = view;
		this.table = table;
		return this.view;
	}

	public Table createTable() {
		Table table = new Table();
		this.table = table;
		if (this.view == null) {
			this.view = new DefaultPokerView(this.table);
		}
		this.view.setTable(this.table);
		return this.table;
	}

	public Table createTable(PokerView view) {
		Table table = new Table();
		this.table = table;
		this.view = view;
		this.view.setTable(this.table);
		return this.table;
	}
}
