package edu.kcg.Poker.Table;

import java.util.ArrayList;

import edu.kcg.Poker.Client.Player;
import edu.kcg.Poker.Strategy.Params;
import edu.kcg.Poker.Table.DataManager.AdaptManager;
import edu.kcg.Poker.Table.DataManager.CardsManager;
import edu.kcg.Poker.Table.DataManager.ChipsManager;
import edu.kcg.Poker.Table.DataManager.PhasesManager;
import edu.kcg.Poker.Table.DataManager.PlayersManager;

/**
 * ゲームに使用するデータを持つクラス.
 * 
 * @author Shun.S
 */
public class Table extends AdaptManager{
	private int tableId;
	private static int lastId = 0;
	private PhasesManager phaseManager;
	private PlayersManager playerManager;
	private ChipsManager chipManager;
	private CardsManager cardManager;
	
	public Table() {
		tableId = lastId++;
		phaseManager = new PhasesManager();
		playerManager = new PlayersManager();
		chipManager = new ChipsManager();
		cardManager = new CardsManager();
	}

	public void addChair(Player player) {
		playerManager.addChair(player);
	}

	public void addPot(int chip) {
		chipManager.addPot(chip);
	}

	public int chairSize() {
		return playerManager.chairSize();
	}

	public int getAnty() {
		return chipManager.getAnty();
	}

	public ArrayList<Chair> getChairs() {
		return playerManager.getChairs();
	}

	public int[] getCommunityCards() {
		return cardManager.getCommunityCards();
	}

	public int getCommunityCardsIndex() {
		return cardManager.getCommunityCardsIndex();
	}

	public int getCurrentPhase() {
		return phaseManager.getCurrentPhase();
	}

	public int getCurrentPlayer() {
		return playerManager.getCurrentPlayer();
	}

	public int getDealer() {
		return playerManager.getDealer();
	}

	public int getDeckIndex() {
		return cardManager.getDeckIndex();
	}

	public int getLimit() {
		return chipManager.getLimit();
	}

	public int getMaxRaise() {
		return chipManager.getMaxRaise();
	}

	public int getPot() {
		return chipManager.getPot();
	}

	public int getRound() {
		return phaseManager.getRound();
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

	public int popDeck() {
		return cardManager.popDeck();
	}

	public void pushCommunityCards(int card) {
		cardManager.pushCommunityCards(card);
	}

	public void setAnty(int anty) {
		chipManager.setAnty(anty);
	}

	public void setCommunityCards(int[] communityCards) {
		cardManager.setCommunityCards(communityCards);
	}

	public void setCommunityCardsIndex(int communityCardsIndex) {
		cardManager.setCommunityCardsIndex(communityCardsIndex);
	}

	public void setCurrentPhase(int currentPhase) {
		phaseManager.setCurrentPhase(currentPhase);
	}

	public void setCurrentPlayer(int currentPlayer) {
		playerManager.setCurrentPlayer(currentPlayer);
	}

	public void setDealer(int dealer) {
		playerManager.setDealer(dealer);
	}

	public void setDeck(int[] deck) {
		cardManager.setDeck(deck);
	}

	public void setDeckIndex(int deckIndex) {
		cardManager.setDeckIndex(deckIndex);
	}

	public void setLimit(int limit) {
		chipManager.setLimit(limit);
	}

	public void setMaxRaise(int maxBet) {
		chipManager.setMaxRaise(maxBet);
	}

	public void setPot(int pot) {
		chipManager.setPot(pot);
	}

	public void setRound(int round) {
		phaseManager.setRound(round);
	}
}
