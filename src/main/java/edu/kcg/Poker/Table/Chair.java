package edu.kcg.Poker.Table;

import edu.kcg.Poker.Client.Player;

/**
 * プレイヤーがテーブルに参加した時に使用するデータを持つクラス.
 * 
 * @author Shun.S
 * 
 */
public class Chair {
	public static final int HANDBITMASK_L = 0x03F;
	public static final int HANDBITMASK_R = 0xFC0;

	private int addedRaise;
	private int bankroll;
	private int currentRaise;
	private int hands;
	private int lastPlay;
	private Player player;

	public Chair(Player player) {
		this.player = player;
		lastPlay = 0;
		addedRaise = 0;
		currentRaise = 0;
		bankroll = 300;
	}

	public int choice(int maxBet, int limit) {
		if (isFold()) {
			return -2;
		}
		if (isAllin()) {
			return 0;
		}
		int option = player.getStrategy().solveRaise();
		if (option > -1) {
			int bet = maxBet + option - this.currentRaise;
			if (bankroll - bet < 0) {
				bet = bankroll;
			}
			if (bet > limit) {
				bet = limit;
			}
			this.addedRaise += bet;
			bankroll -= bet;
		}

		this.setLastPlay(option);
		return option;
	}

	public int getAddedBet() {
		return addedRaise;
	}

	public int getBankroll() {
		return bankroll;
	}

	public int getCurrentRaise() {
		return currentRaise;
	}

	public int getHands() {
		return hands;
	}

	public int getLastPlay() {
		return lastPlay;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isAllin() {
		return bankroll == 0;
	}

	public boolean isFold() {
		return this.lastPlay == -1;
	}

	public void payAnty(int x) {
		this.setLastPlay(x);
		this.addedRaise += x;
		this.currentRaise = x;
		bankroll -= x;
	}

	public void profit(int x) {
		bankroll += x;
	}

	public void setAddedBet(int addedBet) {
		this.addedRaise = addedBet;
	}

	public void setCurrentRaise(int currentRaise) {
		this.currentRaise = currentRaise;
	}

	public void setHands(int hands) {
		this.hands = hands;
	}

	public void setLastPlay(int lastPlay) {
		this.lastPlay = lastPlay;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
