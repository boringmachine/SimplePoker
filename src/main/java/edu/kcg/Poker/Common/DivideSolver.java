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

	public int backOverRaise(int maxAddedBet, boolean[] winners) {
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

	/**
	 * 利益の分配。
	 */
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

		// logger.playersHandsStatus();
		// logger.playersHandRollStatus(handrolls);
		// logger.playersBankrollStatus();
	}

	public void divideProfit(int pot, int sumWinner, boolean[] winners) {
		int chairSize = chairs.size();
		// 勝者に利益を配分。
		// ある勝者の合計掛け金をxとすると、x/sumWinnerが分配される。
		for (int i = 0; i < chairSize; i++) {
			Chair chair = chairs.get(i);
			if (winners[i]) {
				int x = chair.getAddedBet();
				int profit = (x * pot) / sumWinner;
				chair.profit(profit);
			}
		}
	}

	public int[] solveHandrolls() {
		int handroll = 0;
		int[] handrolls = new int[chairs.size()];

		// 最大ハンドを計算。
		for (int i = 0; i < chairs.size(); i++) {
			// ハンドを取得
			Chair chair = chairs.get(i);
			int hands = chair.getHands();
			handroll = 0;
			// 役の強さを計算。
			if (!chair.isFold()) {
				handroll = HandChecker.checkHand(hands, communityCards);
			}

			handrolls[i] = handroll;
		}
		return handrolls;
	}

	public int solveMaxAddedBet(boolean[] winners) {
		int maxAddedBet = 0;
		int chairSize = chairs.size();
		// 勝者の掛け金の最大を計算。
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

	public int solveMaxHandroll(int[] handrolls) {
		int max = 0;

		// 最大ハンドを計算
		for (int i = 0; i < handrolls.length; i++) {
			// 最大ハンドより大きければ、
			if (handrolls[i] >= max) {
				max = handrolls[i];
			}
		}
		return max;
	}

	public int solveSumWinnersBet(boolean[] winners) {
		int sumWinner = 0;
		int chairSize = chairs.size();
		for (int i = 0; i < chairSize; i++) {
			if (winners[i]) {
				sumWinner += chairs.get(i).getAddedBet();
			}
		}
		return sumWinner;
	}

	public boolean[] solveWinner(int max, int[] handRoll) {
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
