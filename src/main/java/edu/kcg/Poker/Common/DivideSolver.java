package edu.kcg.Poker.Common;

import java.util.ArrayList;

import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;

public class DivideSolver {

	private ArrayList<Chair> chairs;
	private int[] communityCards;
	private int pot;

	public DivideSolver(Table table) {
		this.chairs = table.getPlayerManager().getChairs();
		this.pot = table.getChipManager().getPot();
		this.communityCards = table.getCardManager().getCommunityCards();
	}

	private int backOverRaise(int maxAddedBet, boolean[] winners) {
		// 勝者よりも掛け金が多い敗北プレイヤーに過多分の返上。
		for (int i = 0; i < chairs.size(); i++) {
			Chair chair = chairs.get(i);
			if (winners[i]) {
				int x = chair.getAddedBet();
				if (x > maxAddedBet) {
					x = chair.getAddedBet() - maxAddedBet;
					pot -= x;
					chair.profit(x);
				}
			}
		}
		return pot;
	}

	public void divideProfit() {
		int maxAddedBet = 0;
		int sumWinner = 0;
		int max = 0;
		int pot = 0;
		int chairSize = chairs.size();
		int[] handrolls = new int[chairSize];
		boolean[] winners = new boolean[chairSize];

		handrolls = this.solveHandrolls();
		max = this.solveMaxHandroll(handrolls);
		winners = this.solveWinner(max, handrolls);
		sumWinner = this.solveSumWinnersBet(winners);
		maxAddedBet = this.solveMaxAddedBet(winners);

		pot = this.backOverRaise(maxAddedBet, winners);
		this.divideProfit(pot, sumWinner, winners);

	}

	private void divideProfit(int pot, int sumWinner, boolean[] winners) {
		int chairSize = chairs.size();

		// ある勝者の合計掛け金をxとすると、x/sumWinnerが分配される。
		// ただし、sumWinnerは勝者の金額の合計。
		for (int i = 0; i < chairSize; i++) {
			Chair chair = chairs.get(i);
			if (winners[i]) {
				int x = chair.getAddedBet();
				int profit = (x * pot) / sumWinner;
				chair.profit(profit);
			}
		}
	}

	private int[] solveHandrolls() {
		int handroll = 0;
		int[] handrolls = new int[chairs.size()];

		for (int i = 0; i < chairs.size(); i++) {
			Chair chair = chairs.get(i);
			int hands = chair.getHands();
			handroll = 0;
			if (!chair.isFold()) {
				handroll = HandChecker.checkHand(hands, communityCards);
			}

			handrolls[i] = handroll;
		}
		return handrolls;
	}

	private int solveMaxAddedBet(boolean[] winners) {
		int maxAddedBet = 0;
		int chairSize = chairs.size();
		
		for (int i = 0; i < chairSize; i++) {
			Chair chair = chairs.get(i);
			if (winners[i]) {
				int x = chair.getAddedBet();
				if (x > maxAddedBet) {
					maxAddedBet = x;
				}
			}
		}
		return maxAddedBet;
	}

	private int solveMaxHandroll(int[] handrolls) {
		int max = 0;

		for (int i = 0; i < handrolls.length; i++) {
			if (handrolls[i] >= max) {
				max = handrolls[i];
			}
		}
		return max;
	}

	private int solveSumWinnersBet(boolean[] winners) {
		int sumWinner = 0;
		int chairSize = chairs.size();
		for (int i = 0; i < chairSize; i++) {
			if (winners[i]) {
				sumWinner += chairs.get(i).getAddedBet();
			}
		}
		return sumWinner;
	}

	private boolean[] solveWinner(int max, int[] handRoll) {
		int chairSize = chairs.size();
		boolean[] winner = new boolean[chairSize];
		for (int i = 0; i < winner.length; i++) {
			if (handRoll[i] == max) {
				winner[i] = true;
			} else {
				winner[i] = false;
			}
		}
		return winner;
	}
}
