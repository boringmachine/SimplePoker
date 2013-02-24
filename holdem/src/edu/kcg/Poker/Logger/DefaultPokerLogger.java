package edu.kcg.Poker.Logger;

import java.util.ArrayList;

import edu.kcg.Poker.Common.HandChecker;
import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;
import edu.kcg.Poker.Table.DataManager.CardsManager;
import edu.kcg.Poker.Table.DataManager.ChipsManager;
import edu.kcg.Poker.Table.DataManager.PhasesManager;
import edu.kcg.Poker.Table.DataManager.PlayersManager;

public class DefaultPokerLogger extends PokerGameLogger {

	public DefaultPokerLogger(Table table) {
		super(table);
	}

	@Override
	public void afterChance() {
		this.communityCardStatus();
	}

	@Override
	public void afterFinal() {
		this.playersHandsStatus();
		this.playersBankrollStatus();
	}

	@Override
	public void afterFirst() {

	}

	@Override
	public void afterHuman() {
		int index = table.getPlayerManager().getCurrentPlayer();
		this.lastPlayStatus(index);
	}

	@Override
	public void afterPhase() {
		this.lastPhaseStatus();
	}

	@Override
	public void beforeChance() {
		System.out.println(Messages.getString("PokerGame.CHANCE"));
	}

	@Override
	public void beforeFinal() {
		System.out.println(Messages.getString("PokerGame.FINAL"));
	}

	@Override
	public void beforeFirst() {
		System.out.println(Messages.getString("PokerGame.FIRST"));		
	}


	@Override
	public void beforeHuman() {
		System.out.println(Messages.getString("PokerGame.HUMAN"));
		int index = table.getPlayerManager().getCurrentPlayer();
		this.playerStatus(index);
	}


	@Override
	public void beforePhase() {
	}


	@Override
	public void setTable(Table table) {
		this.table = table;
	}


	private void communityCardStatus() {
		for (int card : table.getCardManager().getCommunityCards()) {
			if (card == -1) {
				System.out.print("[]");
			} else {
				System.out.print("[" + (card % 13 + 1) + ":"
						+ CardsManager.MARK[card / 13] + "]");
			}
		}
		System.out.println();
	}


	private void playersBankrollStatus() {
		int chairSize = table.getPlayerManager().getChairSize();
		for (int i = 0; i < chairSize; i++) {
			Chair chair = table.getPlayerManager().getChairs().get(i);
			System.out.println(i + Messages.getString("PokerGame.BANKROLL")
					+ chair.getBankroll());
		}
	}




	private void lastPhaseStatus() {
		System.out.println(Messages.getString("PokerGame.LINE"));
	}




	private void playersHandsStatus() {
		int chairSize = table.getPlayerManager().getChairSize();
		for (int i = 0; i < chairSize; i++) {
			Chair chair = table.getPlayerManager().getChairs().get(i);
			int hands = chair.getHands();

			// ハンドを２枚に分解。
			int handl = (hands & HandChecker.HAND_L) >> 6;
			int handr = (hands & HandChecker.HAND_R);
			// それぞれ数字とマークに分解。
			int handln = handl % 13 + 1;
			char handlm = CardsManager.MARK[handl / 13];
			int handrn = handr % 13 + 1;
			char handrm = CardsManager.MARK[handr / 13];
			System.out.println(i + ":" + "[" + handln + ":" + handlm + "]"
					+ "[" + handrn + ":" + handrm + "]");
		}
	}


	private void lastPlayStatus(int index) {
		Chair chair = this.table.getPlayerManager().getChairs().get(index);
		int lastplay = chair.getLastPlay();
		String stringLastPlay = String.valueOf(lastplay);
		if (chair.isFold()) {
			stringLastPlay = Messages.getString("PokerGame.FOLD");
		} else if (chair.isAllin()) {
			stringLastPlay = Messages.getString("PokerGame.ALLIN");
		} else if (lastplay == 0) {
			stringLastPlay = Messages.getString("PokerGame.CALL");
		} else {
			stringLastPlay = Messages.getString("PokerGame.BET");
			stringLastPlay += String.valueOf(lastplay) + "$";
		}
		String stringPrint = index + Messages.getString("PokerGame.LAST_PLAY")
				+ stringLastPlay;
		System.out.println(stringPrint);
	}

	private void playersHandRollStatus(int[] handrolls) {
		for (int i = 0; i < handrolls.length; i++) {
			System.out.println(i + ")" + handrolls[i]);
		}
	}

	private void playerStatus(int index) {
		ChipsManager chipManager = table.getChipManager();
		Chair chair = table.getPlayerManager().getChairs().get(index);
		int hands = chair.getHands();
		int hand_l = (hands & HandChecker.HAND_L) >> 6;
		int hand_r = (hands & HandChecker.HAND_R);
		System.out.println(" " + Messages.getString("PokerGame.POT")
				+ chipManager.getPot() + ","
				+ Messages.getString("PokerGame.MAX_BET")
				+ chipManager.getMaxRaise() + ","
				+ Messages.getString("PokerGame.TOTAL_BET")
				+ chair.getAddedBet() + ","
				+ Messages.getString("PokerGame.CUR_BET")
				+ chair.getCurrentRaise() + "\n "
				+ Messages.getString("PokerGame.YOUR_HANDS") + "["
				+ (hand_l % 13 + 1) + ":" + CardsManager.MARK[hand_l / 13]
				+ "]" + "[" + (hand_r % 13 + 1) + ":"
				+ CardsManager.MARK[hand_r / 13] + "]");
	}

}
