package edu.kcg.Poker.Tester;

import edu.kcg.Poker.Player;
import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.Table;

/**
 * 役判定モジュールのテスター。
 */
public class HandCheckerTester {
	public static void main(String[] args) {
		Table table = new Table();
		PokerGame game = new PokerGame(table);

		// コミュニティカード
		int[] com = { 5, 2, 4, 8, 10 };
		// プレイヤーの手札
		int handl = 51;
		int handr = 50;

		handl <<= 6;
		game.addPlayer(new Player());
		game.firstPhase();
		table.setCommunityCards(com);
		game.getTable().getChairs().get(0).setHands(handl | handr);
		game.finalPhase();
	}
}
