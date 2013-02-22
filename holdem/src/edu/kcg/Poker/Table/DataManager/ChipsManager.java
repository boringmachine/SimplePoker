package edu.kcg.Poker.Table.DataManager;

import java.util.ArrayList;

import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;

public class ChipsManager {
	private int anty;
	private int limit;
	private int maxRaise;
	private int pot;
	private Table table;

	public ChipsManager(Table table) {
		this.table = table;
		anty = 1;
		limit = Integer.MAX_VALUE;
		pot = 0;
		maxRaise = 0;
	}

	public void addPot(int x) {
		pot += x;
	}

	public int addPot(int option, int pastBankroll, int currentRaise) {

		int maxBet = this.getMaxRaise();
		// 選択肢がフォルドでないなら上乗せ分をポットに加算。
		if (option > -1) {
			int bet = option + maxBet;
			if (pastBankroll < bet) {
				bet = pastBankroll + currentRaise;
			}
			this.setMaxRaise(bet);
			this.addPot(bet - currentRaise);
			return bet;
		}
		return -1;
	}

	public int getAnty() {
		return anty;
	}

	public int getLimit() {
		return limit;
	}

	public int getMaxRaise() {
		return maxRaise;
	}

	public int getPot() {
		return pot;
	}

	public void payBlind(int small, int big) {
		PlayersManager playerManage = table.getPlayerManager();
		int anty = this.getAnty();
		ArrayList<Chair> chairs = playerManage.getChairs();

		// スモールブラインドにアンティを支払わせる。
		Chair smallblind = chairs.get(small);
		smallblind.payAnty(anty);

		// ビッグブラインドにアンティを支払わせる。
		Chair bigblind = chairs.get(big);
		bigblind.payAnty(anty * 2);

		// ポットにアンティを支払い分を追加。
		this.addPot(anty * 3);

		// レイズの最大値と現在手番のプレイヤーを更新。
		this.setMaxRaise(anty * 2);
		playerManage.setCurrentPlayer(big);
	}

	public void setAnty(int anty) {
		this.anty = anty;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setMaxRaise(int maxBet) {
		this.maxRaise = maxBet;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}
}
