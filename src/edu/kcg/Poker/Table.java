package edu.kcg.Poker;

import java.util.ArrayList;
/**
 * ゲームに使用するデータを持つクラス.
 * @author Shun.S
 */
public class Table{

	
	public static final char[] MARK = {'H','C','D','S'};
	private static int lastId = 0;

	private int tableId;
	private ArrayList<Chair> chairs;
	private int round;
	private int currentPlayer;
	private int anty;
	private int limit;
	private int pot;
	private int maxRaise;
	private int[] deck = new int[52];
	private int deckIndex;
	private int[] communityCards = new int[5];
	private int communityCardsIndex;
	private int dealer;
	private int currentPhase;
		
	public Table(){
		chairs  = new ArrayList<Chair>();
		tableId = lastId++;
		currentPlayer=0;
		dealer = 0;
		anty = 1;
		limit = Integer.MAX_VALUE;
		pot = 0;
		maxRaise = 0;
		deckIndex = 0;		
		communityCardsIndex = 0;
		for(int i=0;i<this.communityCards.length;i++){
			communityCards[i] = -1;
		}
	}
	
	/**
	 * ポットを加算。
	 * @return
	 */
	public void addPot(int x){
		pot += x;
	}
	
	public int chairSize(){
		return chairs.size();
	}
	
	public void addChair(Player player){
		chairs.add(new Chair(player));
	}
	
	public int popDeck(){
		return deck[deckIndex--];
	}
	
	public void pushCommunityCards(int x){
		this.communityCards[communityCardsIndex++]=x;
	}
	
	
	public void setPot(int pot) {
		this.pot = pot;
	}

	public int getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase;
	}

	public ArrayList<Chair> getChairs() {
		return chairs;
	}

	public int getDeckIndex() {
		return deckIndex;
	}

	public void setDeckIndex(int deckIndex) {
		this.deckIndex = deckIndex;
	}

	public int getCommunityCardsIndex() {
		return communityCardsIndex;
	}

	public void setCommunityCardsIndex(int communityCardsIndex) {
		this.communityCardsIndex = communityCardsIndex;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public int getAnty() {
		return anty;
	}
	public void setAnty(int anty) {
		this.anty = anty;
	}
	public int getMaxRaise() {
		return maxRaise;
	}
	public void setMaxRaise(int maxBet) {
		this.maxRaise = maxBet;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getDealer() {
		return dealer;
	}
	public void setDealer(int dealer) {
		this.dealer = dealer;
	}

	public int getTableId() {
		return tableId;
	}

	public int getPot() {
		return pot;
	}
	public int[] getCommunityCards() {
		return communityCards;
	}

	public void setCommunityCards(int[] communityCards) {
		this.communityCards = communityCards;
	}

	public void setDeck(int[] deck) {
		this.deck = deck;
	}
	
	
}
