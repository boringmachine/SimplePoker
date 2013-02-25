package edu.kcg.Poker.Table.DataManager;

import java.util.ArrayList;
import java.util.Arrays;

import edu.kcg.Poker.Table.Chair;
import edu.kcg.Poker.Table.Table;

public class CardsManager {
	public static final char[] MARK = { 'H', 'C', 'D', 'S' };

	// community card
	private int[] communityCards = new int[5];

	private int communityCardsIndex;
	// Deck
	private int[] deck = new int[52];

	private int deckIndex;
	private Table table;

	public CardsManager(Table table) {
		this.table = table;
		
		communityCardsIndex = 0;
		Arrays.fill(communityCards, -1);

		deckIndex = 0;
		Arrays.fill(deck, 0);
	}

	/**
	 * カードを配る。
	 */
	public void deal() {
		PlayersManager playerManage = table.getPlayerManager();
		ArrayList<Chair> chairs = playerManage.getChairs();

		for (Chair chair : chairs) {
			int cardLeft = this.popDeck() << 6;
			int cardRight = this.popDeck();
			int hands = (cardLeft | cardRight);
			chair.setHands(hands);
		}
	}

	/**
	 * コミュニティカードを配る。
	 * 
	 * @param round
	 */
	public void dealCommunityCard(int round) {
		// ラウンド1ならデックからコミュニティカードに3枚出す。
		// ラウンド2,3なら、デックからコミュニティカードに1枚出す。
		// ラウンド4なら何もしない。
		if (round == PhasesManager.FLOP) {
			for (int i = 0; i < 3; i++) {
				int card = this.popDeck();
				this.pushCommunityCards(card);
			}
		} else if (round == PhasesManager.TURN || round == PhasesManager.RIVER) {
			int card = this.popDeck();
			this.pushCommunityCards(card);
		} else if (round == PhasesManager.SHOWDOWN) {
		}
	}

	public int[] getCommunityCards() {
		return communityCards;
	}

	public int getCommunityCardsIndex() {
		return communityCardsIndex;
	}

	public int getDeckIndex() {
		return deckIndex;
	}

	public int popDeck() {
		return deck[deckIndex--];
	}

	public void pushCommunityCards(int x) {
		this.communityCards[communityCardsIndex++] = x;
	}

	public void setCommunityCards(int[] communityCards) {
		this.communityCards = communityCards;
	}

	public void setCommunityCardsIndex(int communityCardsIndex) {
		this.communityCardsIndex = communityCardsIndex;
	}

	public void setDeck(int[] deck) {
		this.deck = deck;
	}

	public void setDeckIndex(int deckIndex) {
		this.deckIndex = deckIndex;
	}

	/**
	 * デックをシャッフル
	 */
	public void shuffle() {
		int[] bufDeck = new int[52];
		for (int i = 0; i < bufDeck.length; i++) {
			bufDeck[i] = i;
		}

		for (int i = bufDeck.length - 1; i > 0; i--) {
			int t = (int) (Math.random() * i);
			int tmp = bufDeck[i];
			bufDeck[i] = bufDeck[t];
			bufDeck[t] = tmp;
		}

		this.setDeck(bufDeck);
	}

}
