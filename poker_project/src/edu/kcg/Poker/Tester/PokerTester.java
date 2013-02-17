package edu.kcg.Poker.Tester;

import edu.kcg.Poker.Player;
import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.Table;
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
		PokerGame game = new PokerGame();

		Table table = game.getTable();
		// Table table = game.createTable();
		table.setAnty(10);
		table.setLimit(1000);

		for(Player player : players){
			player = new Player();
			player.setStrategy(new HumanPlayerStdIn());
			game.addPlayer(player);
		}

		game.execute();
	}

}
