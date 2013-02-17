package edu.kcg.Poker.Tester;

import edu.kcg.Poker.Player;
import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.Table;
import edu.kcg.Poker.Factory.GameFactory;
import edu.kcg.Poker.Strategy.*;

/**
 * ポーカー全体のテスター.
 * 
 * @author Shun.S
 */
public class PokerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Player[] players = new Player[3];
		PokerGame game = GameFactory.createGame();
		Thread thread = new Thread(game);

		Table table = game.getTable();
		table.setAnty(10);
		table.setLimit(1000);

		for (Player player : players) {
			player = new Player();
			player.setStrategy(new HumanPlayerStdIn());
			game.addPlayer(player);
		}
		
		thread.start();
	}

}
