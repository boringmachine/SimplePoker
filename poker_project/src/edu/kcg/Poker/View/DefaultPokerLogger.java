package edu.kcg.Poker.View;

import edu.kcg.Poker.Chair;
import edu.kcg.Poker.GameRules;
import edu.kcg.Poker.HandChecker;
import edu.kcg.Poker.Table;

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
				System.out.print("[" + (card % 13 + 1) + ":"
						+ Table.MARK[card / 13] + "]");
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
			stringLastPlay = Messages.getString("PokerGame.FOLD"); //$NON-NLS-1$
		} else if (chair.isAllin()) {
			stringLastPlay = Messages.getString("PokerGame.ALLIN"); //$NON-NLS-1$
		} else if (lastplay == 0) {
			stringLastPlay = Messages.getString("PokerGame.CALL"); //$NON-NLS-1$
		} else {
			stringLastPlay = Messages.getString("PokerGame.BET") + String.valueOf(lastplay) + "$"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		System.out.println(index
				+ Messages.getString("PokerGame.LAST_PLAY") + stringLastPlay); //$NON-NLS-1$
	}

	@Override
	public void phaseLast() {
		System.out.println(Messages.getString("PokerGame.LINE")); //$NON-NLS-1$
	}

	@Override
	public void phaseStatus(int status) {
		switch (status) {
		case GameRules.FIRST:
			System.out.println(Messages.getString("PokerGame.FIRST")); //$NON-NLS-1$
			break;
		case GameRules.HUMAN:
			System.out.println(Messages.getString("PokerGame.HUMAN")); //$NON-NLS-1$
			break;
		case GameRules.CHANCE:
			System.out.println(Messages.getString("PokerGame.CHANCE")); //$NON-NLS-1$
			break;
		case GameRules.FINAL:
			System.out.println(Messages.getString("PokerGame.FINAL")); //$NON-NLS-1$
			break;
		}
	}

	@Override
	public void playerBankroll(int index) {
		Chair chair = table.getChairs().get(index);
		System.out
				.println(index
						+ Messages.getString("PokerGame.BANKROLL") + chair.getBankroll()); //$NON-NLS-1$		
	}

	@Override
	public void playerHands(int index) {
		Chair chair = table.getChairs().get(index);
		int hands = chair.getHands();

		// ハンドを２枚に分解。
		int handl = (hands & HandChecker.HAND_L) >> 6;
		int handr = (hands & HandChecker.HAND_R);
		// それぞれ数字とマークに分解。
		int handln = handl % 13 + 1;
		char handlm = Table.MARK[handl / 13];
		int handrn = handr % 13 + 1;
		char handrm = Table.MARK[handr / 13];
		System.out.println(index + ":" + "[" + handln + ":" + handlm + "]"
				+ "[" + handrn + ":" + handrm + "]");
	}

	@Override
	public void playerStatus(int index) {
		Chair chair = table.getChairs().get(index);
		int hands = chair.getHands();
		int hand_l = (hands & HandChecker.HAND_L) >> 6;
		int hand_r = (hands & HandChecker.HAND_R);
		System.out.println(" " + Messages.getString("PokerGame.POT")
				+ table.getPot() + ","
				+ Messages.getString("PokerGame.MAX_BET") + table.getMaxRaise()
				+ "," + Messages.getString("PokerGame.TOTAL_BET")
				+ chair.getAddedBet() + ","
				+ Messages.getString("PokerGame.CUR_BET")
				+ chair.getCurrentRaise() + "\n"
				+ Messages.getString("PokerGame.YOUR_HANDS") + "["
				+ (hand_l % 13 + 1) + ":" + Table.MARK[hand_l / 13] + "]" + "["
				+ (hand_r % 13 + 1) + ":" + Table.MARK[hand_r / 13] + "]");
	}

	@Override
	public void setTable(Table table) {
		this.table = table;
	}

}
