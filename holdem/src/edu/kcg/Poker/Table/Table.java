package edu.kcg.Poker.Table;

import edu.kcg.Poker.Strategy.Params;
import edu.kcg.Poker.Table.DataManager.CardsManager;
import edu.kcg.Poker.Table.DataManager.ChipsManager;
import edu.kcg.Poker.Table.DataManager.PhasesManager;
import edu.kcg.Poker.Table.DataManager.PlayersManager;

/**
 * ゲームに使用するデータを持つクラス.
 * 
 * @author Shun.S
 */
public class Table {

	private static int lastId = 0;
	private CardsManager cardManager;
	private ChipsManager chipManager;
	private PhasesManager phaseManager;
	private PlayersManager playerManager;
	private int tableId;

	public Table() {
		tableId = lastId++;
		phaseManager = new PhasesManager(this);
		playerManager = new PlayersManager(this);
		chipManager = new ChipsManager(this);
		cardManager = new CardsManager(this);
	}

	public CardsManager getCardManager() {
		return cardManager;
	}

	public ChipsManager getChipManager() {
		return chipManager;
	}

	public PhasesManager getPhaseManager() {
		return phaseManager;
	}

	public PlayersManager getPlayerManager() {
		return playerManager;
	}

	public int getTableId() {
		return tableId;
	}

	/**
	 * プレイヤーが参照できるパラメータを取得する.
	 * 
	 * @return
	 */
	public Params packParams(int index) {
		return new Params(this, index);
	}

}
