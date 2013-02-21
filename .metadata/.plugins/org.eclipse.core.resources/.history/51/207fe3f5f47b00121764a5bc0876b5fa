package edu.kcg.Poker;

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
	private boolean allin;
	private int bankroll;
	private int currentRaise;
	private boolean fold;
	// private int hand;
	private int hands;
	private int lastPlay;
	private Player player;

	// private boolean winner;

	public Chair(Player player) {
		this.player = player;
		lastPlay = 0;
		addedRaise = 0;
		currentRaise = 0;
		bankroll = 1000;
		fold = false;
		allin = false;
		// winner = false;
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
				setAllin(true);
			}
			if (bet > limit) {
				bet = limit;
			}
			this.addedRaise += bet;
			bankroll -= bet;
		} else if (option == -1) {
			setFold(true);
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

	/*
	 * public int getHand() { return hand; }
	 */
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
		return allin;
	}

	public boolean isFold() {
		return fold;
	}

	/*
	 * public boolean isWinner() { return winner; }
	 */
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

	public void setAllin(boolean allin) {
		this.allin = allin;
	}

	public void setCurrentRaise(int currentRaise) {
		this.currentRaise = currentRaise;
	}

	public void setFold(boolean fold) {
		this.fold = fold;
	}

	/*
	 * public void setHand(int hand) { this.hand = hand; }
	 */
	public void setHands(int hands) {
		this.hands = hands;
	}

	public void setLastPlay(int lastPlay) {
		this.lastPlay = lastPlay;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	/*
	 * public void setWinner(boolean winner) { this.winner = winner; }
	 */
}
