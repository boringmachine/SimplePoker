package edu.kcg.Poker.Table.DataManager;

import java.util.ArrayList;
import java.util.Vector;

import edu.kcg.Poker.Client.Player;
import edu.kcg.Poker.Strategy.AdaptStrategy;
import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;

public class PlayersManager {
	private ArrayList<Chair> chairs;
	private int currentPlayer;
	private int dealer;
	private Table table;

	public PlayersManager(Table table) {
		this.table = table;
		chairs = new ArrayList<Chair>();
		currentPlayer = 0;
		dealer = 0;
	}

	public Chair addChair(Player player) {
		Chair chair = new Chair(player);
		chairs.add(chair);
		return chair;
	}
	
	public int countAllin() {
		int count = 0;
		for (Chair chair : chairs) {
			if (chair.isAllin()) {
				count++;
			}
		}
		return count;
	}

	public int countFold() {
		int count = 0;
		for (Chair chair : chairs) {
			if (chair.isFold()) {
				count++;
			}
		}
		return count;
	}

	public int[] decideBlind() {
		int chairSize = this.getChairSize();
		int[] blind = new int[2];

		// スモールブラインドを決定する。
		int small = this.getDealer() + 1;
		if (small > chairSize - 1) {
			small = 0;
		}
		// ビッグブラインドを決定する。
		int big = small + 1;
		if (big > chairSize - 1) {
			big = 0;
		}
		blind[0] = small;
		blind[1] = big;

		return blind;
	}

	public void decideCurrentRaise(Chair chair, int currentPlayer) {
		ChipsManager chipManage = table.getChipManager();
		if (!chair.isFold()) {
			int pastBankroll = chair.getBankroll();
			int option = this.choice(chair, currentPlayer);
			int currentRaise = chair.getCurrentRaise();
			int bet = chipManage.addPot(option, pastBankroll, currentRaise);
			chair.setCurrentRaise(bet);
		}
	}

	public ArrayList<Chair> getChairs() {
		return chairs;
	}

	public int getChairSize() {
		return chairs.size();
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getDealer() {
		return dealer;
	}

	public void nextDealer() {
		int dealer = this.getDealer();
		int playerNum = this.getChairSize();
		dealer++;
		if (dealer > playerNum - 1) {
			dealer = 0;
		}
		this.setDealer(dealer);
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setDealer(int dealer) {
		this.dealer = dealer;
	}

	public void updateChairs() {
		ChipsManager chipManage = table.getChipManager();
		Vector<Chair> buffer = new Vector<Chair>();
		int anty = chipManage.getAnty() * 2;

		for (Chair chair : chairs) {
			if (chair.getBankroll() < anty) {
				buffer.add(chair);
			}
		}

		for (Chair removeItem : buffer) {
			chairs.remove(removeItem);
		}

	}

	public void updateCurrentPlayer(int currentPhase) {
		int currentPlayer = this.getCurrentPlayer();
		int playerNum = this.getChairSize();
		if (currentPhase == PhasesManager.HUMAN
				|| currentPhase == PhasesManager.FIRST) {
			currentPlayer++;
			if (currentPlayer > playerNum - 1) {
				currentPlayer = 0;
			}
		} else {
			currentPlayer = this.getDealer();
		}
		this.setCurrentPlayer(currentPlayer);
	}

	private int choice(Chair chair, int chairIndex) {
		ChipsManager chipManage = table.getChipManager();

		int maxBet = chipManage.getMaxRaise();
		int limit = chipManage.getLimit();
		int option = 0;
		int chairSize = this.getChairSize();
		int countFold = countFold();
		int notFolder = chairSize - countFold;
		int countAllin = countAllin();

		// 現在プレイヤーが戦略で使えるパラメータを渡し、選択肢を選択させる。
		if ((notFolder > 1) && (notFolder - countAllin != 0)) {
			AdaptStrategy strategy = (AdaptStrategy) chair.getPlayer()
					.getStrategy();
			strategy.setParams(table.packParams(chairIndex));
			option = chair.choice(maxBet, limit);
		}
		return option;
	}
}
