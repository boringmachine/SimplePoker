package edu.kcg.Poker;

/**
 * ポーカー全体のテスター。
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
		
		for(Player player:players){
			player = new Player();
			player.setStrategy(new HumanPlayerStdIn());
			game.addPlayer(player);
		}
		
		game.execute();
	}

}
