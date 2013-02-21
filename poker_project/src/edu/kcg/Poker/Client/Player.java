package edu.kcg.Poker.Client;

import edu.kcg.Poker.Strategy.Strategy;

/**
 * ゲームをプレイするプレイヤーのクラス.
 * 
 * @author Shun.S
 * 
 */
public class Player {
	static private int lastId = 0;
	private int playerId;
	private Strategy strategy;

	public Player() {
		this.playerId = lastId++;
	}

	public int getPlayerId() {
		return playerId;
	}

	public Strategy getStrategy() {
		return this.strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
