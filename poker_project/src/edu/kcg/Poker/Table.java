package edu.kcg.Poker;

import java.util.ArrayList;

import edu.kcg.Poker.Strategy.Params;

/**
 * ゲームに使用するデータを持つクラス.
 * 
 * @author Shun.S
 */
public class Table {

	public static final char[] MARK = { 'H', 'C', 'D', 'S' };
	private static int lastId = 0;

	private int anty;
	private ArrayList<Chair> chairs;

	private int[] communityCards = new int[5];
	private int communityCardsIndex;
	private int currentPhase;
	private int currentPlayer;
	private int dealer;
	private int[] deck = new int[52];
	private int deckIndex;
	private int limit;
	private int maxRaise;
	private int pot;
	private int round;
	private int tableId;

	public Table() {
		chairs = new ArrayList<Chair>();
		tableId = lastId++;
		currentPlayer = 0;
		dealer = 0;
		anty = 1;
		limit = Integer.MAX_VALUE;
		pot = 0;
		maxRaise = 0;
		deckIndex = 0;
		communityCardsIndex = 0;
		for (int i = 0; i < this.communityCards.length; i++) {
			communityCards[i] = -1;
		}
	}

	public void addChair(Player player) {
		chairs.add(new Chair(player));
	}

	public void addPot(int x) {
		pot += x;
	}

	public int chairSize() {
		return chairs.size();
	}

	public int getAnty() {
		return anty;
	}

	public ArrayList<Chair> getChairs() {
		return chairs;
	}

	public int[] getCommunityCards() {
		return communityCards;
	}

	public int getCommunityCardsIndex() {
		return communityCardsIndex;
	}

	public int getCurrentPhase() {
		return currentPhase;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getDealer() {
		return dealer;
	}

	public int getDeckIndex() {
		return deckIndex;
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

	public int getRound() {
		return round;
	}

	public int getTableId() {
		return tableId;
	}

	/**
	 * プレイヤーが参照できるパラメータを取得する.
	 * 
	 * @return
	 */
	public Params packParams() {
		return new Params(this);
	}

	public int popDeck() {
		return deck[deckIndex--];
	}

	public void pushCommunityCards(int x) {
		this.communityCards[communityCardsIndex++] = x;
	}

	public void setAnty(int anty) {
		this.anty = anty;
	}

	public void setCommunityCards(int[] communityCards) {
		this.communityCards = communityCards;
	}

	public void setCommunityCardsIndex(int communityCardsIndex) {
		this.communityCardsIndex = communityCardsIndex;
	}

	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setDealer(int dealer) {
		this.dealer = dealer;
	}

	public void setDeck(int[] deck) {
		this.deck = deck;
	}

	public void setDeckIndex(int deckIndex) {
		this.deckIndex = deckIndex;
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

	public void setRound(int round) {
		this.round = round;
	}
}
