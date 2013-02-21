package edu.kcg.Poker.Logger;

import java.util.ArrayList;

import edu.kcg.Poker.GameRules;
import edu.kcg.Poker.Common.HandChecker;
import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;
import edu.kcg.Poker.Table.DataManager.CardsManager;

public class DefaultPokerLogger extends PokerGameLogger {

	public DefaultPokerLogger(Table table) {
		super(table);
	}

	@Override
	public void communityCardStatus() {
		for (int card : table.getCommunityCards()) {
			if (card == -1) {
				System.out.print("[]");
			} else {
				System.out.print(
					"["	+ (card % 13 + 1) + ":"	+ CardsManager.MARK[card / 13] + "]");
			}
		}
		System.out.println();
	}

	
	@Override
	public void lastPlayStatus(int index) {
		Chair chair = table.getChairs().get(index);
		int lastplay = chair.getLastPlay();
		String stringLastPlay = String.valueOf(lastplay);
		if (chair.isFold()) {
			stringLastPlay =  Messages.getString("PokerGame.FOLD");
		} else if (chair.isAllin()) {
			stringLastPlay =  Messages.getString("PokerGame.ALLIN");
		} else if (lastplay == 0) {
			stringLastPlay =  Messages.getString("PokerGame.CALL");
		} else {
			stringLastPlay =  Messages.getString("PokerGame.BET");
			stringLastPlay += String.valueOf(lastplay) + "$";
		}
		String stringPrint = index + Messages.getString("PokerGame.LAST_PLAY") + stringLastPlay;
		System.out.println(stringPrint);
	}

	@Override
	public void lastPhaseStatus() {
			System.out.println(Messages.getString("PokerGame.LINE"));
	}

	@Override
	public void phaseNameStatus(int status) {
		switch (status) {
		case GameRules.FIRST:
			System.out.println(Messages.getString("PokerGame.FIRST"));
			break;
		case GameRules.HUMAN:
			System.out.println(Messages.getString("PokerGame.HUMAN"));
			break;
		case GameRules.CHANCE:
			System.out.println(Messages.getString("PokerGame.CHANCE"));
			break;
		case GameRules.FINAL:
			System.out.println(Messages.getString("PokerGame.FINAL"));
			break;
		}
	}
	
	@Override
	public void playersBankrollStatus() {
		ArrayList<Chair> chairs = table.getChairs();
		int chairSize = table.chairSize();
		for(int i=0;i<chairSize;i++){
			Chair chair = chairs.get(i);
			System.out.println(i 
				+ Messages.getString("PokerGame.BANKROLL") + chair.getBankroll());
		}
	}

	@Override
	public void playersHandsStatus() {
		ArrayList<Chair> chairs = table.getChairs();
		int chairSize = table.chairSize();
		for(int i=0 ; i<chairSize;i++){
			Chair chair = chairs.get(i);
			int hands = chair.getHands();
	
			// ハンドを２枚に分解。
			int handl = (hands & HandChecker.HAND_L) >> 6;
			int handr = (hands & HandChecker.HAND_R);
			// それぞれ数字とマークに分解。
			int handln = handl % 13 + 1;
			char handlm = CardsManager.MARK[handl / 13];
			int handrn = handr % 13 + 1;
			char handrm = CardsManager.MARK[handr / 13];
			System.out.println(i + ":" 
					+ "[" + handln + ":" + handlm + "]"
					+ "[" + handrn + ":" + handrm + "]");
		}
	}

	@Override
	public void playerStatus(int index) {
		Chair chair = table.getChairs().get(index);
		int hands = chair.getHands();
		int hand_l = (hands & HandChecker.HAND_L) >> 6;
		int hand_r = (hands & HandChecker.HAND_R);
		System.out.println(" " 
			+ Messages.getString("PokerGame.POT")        + table.getPot()          + ","
			+ Messages.getString("PokerGame.MAX_BET")    + table.getMaxRaise()     + "," 
			+ Messages.getString("PokerGame.TOTAL_BET")  + chair.getAddedBet()     + ","
			+ Messages.getString("PokerGame.CUR_BET")    + chair.getCurrentRaise() + "\n "
			+ Messages.getString("PokerGame.YOUR_HANDS")
			+ "[" + (hand_l % 13 + 1) + ":" + CardsManager.MARK[hand_l / 13] + "]" 
			+ "[" + (hand_r % 13 + 1) + ":" + CardsManager.MARK[hand_r / 13] + "]");
	}

	@Override
	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public void playersHandRollStatus(int[] handrolls) {
		for(int i=0;i<handrolls.length;i++){
			System.out.println(i+")"+handrolls[i]);
		}	
	}
}