package edu.kcg.Poker;

import java.util.ArrayList;

import edu.kcg.Poker.Client.Player;
import edu.kcg.Poker.Logger.DefaultPokerLogger;
import edu.kcg.Poker.Logger.PokerGameLogger;
import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;
import edu.kcg.Poker.Table.DataManager.CardsManager;
import edu.kcg.Poker.Table.DataManager.PhasesManager;
import edu.kcg.Poker.Table.DataManager.PlayersManager;

/**
 * テーブルのデータを処理し、ゲームを定義するクラス.
 * 
 * @author Shun.S
 * 
 */
public class PokerGame implements GameRules, Runnable {

	private PokerGameLogger logger;
	private Table table;

	public PokerGame() {
		createTable();
	}

	public PokerGame(PokerGameLogger view) {
		createTable(view);
	}

	public void addPlayer(Player player) {
		table.getPlayerManager().addChair(player);
	}

	public Table createTable() {
		Table table = new Table();
		this.table = table;
		if (this.logger == null) {
			this.logger = new DefaultPokerLogger(this.table);
		}
		this.logger.setTable(this.table);
		return this.table;
	}

	public Table createTable(PokerGameLogger view) {
		Table table = new Table();
		this.table = table;
		this.logger = view;
		this.logger.setTable(this.table);
		return this.table;
	}

	@Override
	public void execute() {
		PhasesManager phaseManage = table.getPhaseManager();
		int status;
		initGame();

		while (true) {
			PlayersManager playerManage = table.getPlayerManager();
			status = phaseManage.gameStatus();
			phaseManage.setCurrentPhase(status);
			
			gameGraph(status);

			if (playerManage.getChairSize() == 1) {
				break;
			}

			phaseManage.nextPhase();

		}
	}

	public Table getTable() {
		return table;
	}

	@Override
	public void run() {
		execute();
	}

	public void setTable(Table table) {
		this.table = table;
	}

	private void chairsInit() {
		PlayersManager playerManage = table.getPlayerManager();
		ArrayList<Chair> chairs = playerManage.getChairs();

		for (Chair chair : chairs) {
			chair.setLastPlay(0);
			chair.setHands(0);
			chair.setAddedBet(0);
		}
	}

	private void finalize(int status) {
		if (status == PhasesManager.FINAL) {
			initGame();
		}
	}

	private void gameGraph(int status) {
		PhasesManager phaseManage = table.getPhaseManager();
		logger.beforePhase();
		switch (status) {
		case PhasesManager.FIRST:
			logger.beforeFirst();
			phaseManage.firstPhase();
			logger.afterChance();
			break;
		case PhasesManager.HUMAN:
			logger.beforeHuman();
			phaseManage.humanPhase();
			logger.afterHuman();
			break;
		case PhasesManager.CHANCE:
			logger.beforeChance();
			phaseManage.chancePhase();
			logger.afterChance();
			break;
		case PhasesManager.FINAL:
			logger.beforeFinal();
			phaseManage.finalPhase();
			logger.afterFinal();
			break;
		}
		logger.afterPhase();
		finalize(status);
	}

	private void initGame() {
		PlayersManager playerManage = table.getPlayerManager();
		CardsManager cardManage = table.getCardManager();
		playerManage.updateChairs();
		chairsInit();
		tableInit();
		cardManage.shuffle();
		cardManage.deal();
		playerManage.nextDealer();
	}

	private void tableInit() {
		int[] comcard = new int[5];
		for (int i = 0; i < comcard.length; i++) {
			comcard[i] = -1;
		}
		table.getChipManager().setMaxRaise(0);
		table.getChipManager().setPot(0);
		table.getPhaseManager().setRound(0);
		table.getCardManager().setCommunityCardsIndex(0);
		table.getCardManager().setCommunityCards(comcard);
		table.getCardManager().setDeckIndex(51);
	}

}
