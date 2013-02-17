package edu.kcg.Poker.Factory;

import java.util.Vector;

import edu.kcg.Poker.PokerGame;
import edu.kcg.Poker.Table;
import edu.kcg.Poker.View.PokerView;

public class GameFactory {

	public static final Vector<PokerGame> pool = new Vector<PokerGame>();
	
	public static PokerGame createGame(){
		PokerGame game = new PokerGame();
		pool.add(game);
		return game;
	}
	
	public static PokerGame createGame(Table table){
		PokerGame game = new PokerGame(table);
		pool.add(game);
		return game;
	}
	

	public static PokerGame createGame(PokerView view){
		PokerGame game = new PokerGame(view);
		pool.add(game);
		return game;
	}
	
	public static PokerGame createGame(Table table,PokerView view){
		PokerGame game = new PokerGame(table,view);
		pool.add(game);
		return game;
	}
	
	public static PokerGame getGame(int index){
		return pool.get(index);
	}
	
}
