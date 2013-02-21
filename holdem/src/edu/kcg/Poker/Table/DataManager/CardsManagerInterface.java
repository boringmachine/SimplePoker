package edu.kcg.Poker.Table.DataManager;

public interface CardsManagerInterface {
	public static final char[] MARK = { 'H', 'C', 'D', 'S' };

	public int[] getCommunityCards();

	public int getCommunityCardsIndex();

	public int getDeckIndex();

	public int popDeck();

	public void pushCommunityCards(int x);

	public void setCommunityCards(int[] communityCards);

	public void setCommunityCardsIndex(int communityCardsIndex);

	public void setDeck(int[] deck);

	public void setDeckIndex(int deckIndex);
}
