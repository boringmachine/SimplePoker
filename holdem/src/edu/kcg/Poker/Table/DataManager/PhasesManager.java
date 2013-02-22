package edu.kcg.Poker.Table.DataManager;

import java.util.ArrayList;

import edu.kcg.Poker.Common.DivideSolver;
import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;

public class PhasesManager {
	public static final int FIRST = 0, HUMAN = 1, CHANCE = 2, FINAL = 3;
	public static final int PREFLOP = 0, FLOP = 1, TURN = 2, RIVER = 3,
			SHOWDOWN = 4;
	private int currentPhase;
	private int round;
	private Table table;

	public PhasesManager(Table table) {
		this.table = table;
	}

	public void chancePhase() {
		PlayersManager playerManage = table.getPlayerManager();
		CardsManager cardManage = table.getCardManager();

		int countFold = playerManage.countFold();
		int countAllin = playerManage.countAllin();
		int chairSize = playerManage.getChairSize();
		int notFolder = chairSize - countFold;
		int round = updateRound();
		cardManage.dealCommunityCard(round);

		// logger.communityCardStatus();

		if (notFolder == 1 || (notFolder - countAllin) < 2) {
			if (round == 4) {
				return;
			}
			chancePhase();
		}
	}

	public void finalPhase() {
		ChipsManager chipManage = table.getChipManager();
		DivideSolver solver = new DivideSolver(table);
		solver.divideProfit();
		// logger.communityCardStatus();
		// firstフェーズに戻るためにポットを初期化。
		chipManage.setPot(0);
	}

	public void firstPhase() {
		PlayersManager playerManage = table.getPlayerManager();
		ChipsManager chipManage = table.getChipManager();
		int[] blind = playerManage.decideBlind();
		int small = blind[0];
		int big = blind[1];
		chipManage.payBlind(small, big);
	}

	public int gameStatus() {

		if (isFirst()) {
			return FIRST;
		}

		if (isFinal()) {
			return FINAL;
		}

		if (isHuman()) {
			return HUMAN;
		}

		if (updateChance()) {
			return CHANCE;
		}

		// それ以外は偶然手番。
		return CHANCE;
	}

	public int getCurrentPhase() {
		return currentPhase;
	}

	public int getRound() {
		return round;
	}

	public void humanPhase() {
		PlayersManager playerManage = table.getPlayerManager();
		int currentPlayer = playerManage.getCurrentPlayer();
		Chair chair = playerManage.getChairs().get(currentPlayer);

		// logger.playerStatus(currentPlayer);

		playerManage.decideCurrentRaise(chair, currentPlayer);

		// logger.lastPlayStatus(currentPlayer);
	}

	public int nextPhase() {
		PlayersManager playerManage = table.getPlayerManager();
		int currentPhase = this.gameStatus();
		playerManage.updateCurrentPlayer(currentPhase);
		this.setCurrentPhase(currentPhase);
		return currentPhase;
	}

	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public boolean updateChance() {
		PlayersManager playerManage = table.getPlayerManager();
		ArrayList<Chair> chairs = playerManage.getChairs();

		// 現在手番が偶然手番の時の処理。
		if (this.getCurrentPhase() == CHANCE) {
			// 現在手番を人為手番に設定。
			this.setCurrentPhase(HUMAN);
			// フォルドしていないかオールインしていない場合、
			// 最終プレイの値をIntegerの最小値に設定する。
			for (Chair chair : chairs) {
				if (!(chair.isFold() || chair.isAllin())) {
					chair.setLastPlay(Integer.MIN_VALUE);
				}
			}
			// 形式的に偶然手番をリターンする。
			return true;
		}
		return false;
	}

	public int updateRound() {
		ChipsManager chipManage = table.getChipManager();
		PlayersManager playerManage = table.getPlayerManager();
		ArrayList<Chair> chairs = playerManage.getChairs();
		++round;
		this.setRound(round);
		chipManage.setMaxRaise(0);
		for (Chair chair : chairs) {
			chair.setCurrentRaise(0);
		}
		return round;
	}

	private boolean isFinal() {
		if (round == PhasesManager.SHOWDOWN) {
			return true;
		}

		return false;
	}

	private boolean isFirst() {
		ChipsManager chipManage = table.getChipManager();
		if (chipManage.getPot() == 0) {
			return true;
		}

		return false;
	}

	private boolean isHuman() {
		PlayersManager playerManage = table.getPlayerManager();
		ChipsManager chipManage = table.getChipManager();
		ArrayList<Chair> chairs = playerManage.getChairs();
		// あるプレイヤーがオールインもフォルドもしていない場合、
		// 全員の掛け金の最大と同じ額を賭けていなければ人為手番。
		// または、最終プレイの値がIntegerの最小値になっていたら人為手番。
		for (Chair chair : chairs) {
			boolean b1 = chair.getCurrentRaise() != chipManage.getMaxRaise();
			boolean b2 = !(chair.isAllin() || chair.isFold());
			boolean b3 = chair.getLastPlay() == Integer.MIN_VALUE;
			if (b1 && b2 || b3) {
				return true;
			}
		}
		return false;
	}
}
