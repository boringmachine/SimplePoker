package edu.kcg.Poker.Factory;

import java.util.Vector;

import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.View.PokerGameLogger;

public class GameFactory {

	public static final Vector<PokerGame> pool = new Vector<PokerGame>();

	public static void allGameDo() {
		for (PokerGame game : pool) {
			Thread thread = new Thread(game);
			thread.start();
		}
	}

	public static PokerGame createGame() {
		PokerGame game = new PokerGame();
		pool.add(game);
		return game;
	}

	public static PokerGame createGame(PokerGameLogger view) {
		PokerGame game = new PokerGame(view);
		pool.add(game);
		return game;
	}

	public static PokerGame getGame(int index) {
		return pool.get(index);
	}

}
