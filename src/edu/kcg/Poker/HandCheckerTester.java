package edu.kcg.Poker;

/**
 * 役判定モジュールのテスター。
 */
public class HandCheckerTester {
	public static void main(String[] args){
		Table table = new Table();
		PokerGame game = new PokerGame(table);
		
		//コミュニティカード
		int[] com = {1,2,3,4,15};
		//プレイヤーの手札
		int handl = 17;
		int handr = 16;
		
		handl<<=6;
		table.setCommunityCards(com);
		Player player = new Player();
		game.addPlayer(player);
		game.firstPhase();
		game.getTable().getChairs().get(0).setHands(handl|handr);
		game.finalPhase();
	}
}
