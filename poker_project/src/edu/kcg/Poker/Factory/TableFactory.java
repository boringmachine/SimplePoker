package edu.kcg.Poker.Factory;

import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.Table;
import edu.kcg.Poker.View.DefaultPokerLogger;
import edu.kcg.Poker.View.PokerGameLogger;

public class TableFactory {

	public Table createTable(PokerGame game) {
		Table table = new Table();
		game.table = table;
		if (game.view == null) {
			game.view = new DefaultPokerLogger(game.table);
		}
		game.view.setTable(game.table);
		return game.table;
	}

	public Table createTable(PokerGame game,PokerGameLogger view) {
		Table table = new Table();
		game.table = table;
		game.view = view;
		game.view.setTable(game.table);
		return game.table;
	}
}
