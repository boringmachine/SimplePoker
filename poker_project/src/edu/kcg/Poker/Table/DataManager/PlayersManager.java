package edu.kcg.Poker.Table.DataManager;

import java.util.ArrayList;

import edu.kcg.Poker.Client.Player;
import edu.kcg.Poker.Table.Chair;

public class PlayersManager implements PlayersManagerInterface {
	private ArrayList<Chair> chairs;
	private int currentPlayer;
	private int dealer;

	public PlayersManager() {
		chairs = new ArrayList<Chair>();
		currentPlayer = 0;
		dealer = 0;
	}

	public void addChair(Player player) {
		chairs.add(new Chair(player));
	}

	public int chairSize() {
		return chairs.size();
	}

	public ArrayList<Chair> getChairs() {
		return chairs;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getDealer() {
		return dealer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setDealer(int dealer) {
		this.dealer = dealer;
	}
}
