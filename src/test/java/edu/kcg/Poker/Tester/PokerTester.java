package edu.kcg.Poker.Tester;

import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.Client.Player;
import edu.kcg.Poker.Factory.GameFactory;
import edu.kcg.Poker.Strategy.*;
import edu.kcg.Poker.Table.Table;

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
		// Thread thread = new Thread(game);

		Table table = game.getTable();
		table.getChipManager().setAnty(10);
		table.getChipManager().setLimit(1000);

		for (Player player : players) {
			player = new Player();
			player.setStrategy(new DefaultStrategy());
			game.addPlayer(player);
		}

		game.execute();
		// thread.start();
	}

}
