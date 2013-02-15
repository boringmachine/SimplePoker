package edu.kcg.Poker.Tester;

import edu.kcg.Poker.Player;
import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.Table;
import edu.kcg.Poker.Strategy.HumanPlayerStdIn;
import edu.kcg.Poker.Strategy.SampleStrategy;

/**
 * ポーカー全体のテスター。
 * 
 * @author Shun.S
 * 
 */
public class PokerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Table table = new Table();
		Player[] players = new Player[3];
		PokerGame game = new PokerGame(table);

		table.setAnty(10);
		table.setLimit(Integer.MAX_VALUE);

		players[0] = new Player();
		players[1] = new Player();

		players[0].setStrategy(new HumanPlayerStdIn());
		players[1].setStrategy(new SampleStrategy());

		game.addPlayer(players[0]);
		game.addPlayer(players[1]);
		game.execute();
	}

}