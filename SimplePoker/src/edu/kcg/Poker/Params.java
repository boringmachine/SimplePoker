package edu.kcg.Poker;

import java.util.ArrayList;

public class Params {

	private int hands;
	private int anty;
	private int limit;
	private int maxRaise;
	private int pot;
	private int round;
	private int[] lastPlays;
	private int[] bankrolls;
	
	public Params(Table table){
		this.anty = table.getAnty();
		this.limit = table.getLimit();
		this.maxRaise = table.getMaxRaise();
		this.pot = table.getPot();
		this.round = table.getRound();
		this.setBankrolls(table.getChairs());
		this.setLastPlays(table.getChairs());
	}
	
	public int getHands(){
		return this.hands;
	}
	
	public void setHands(int hands){
		this.hands = hands;
	}
	
	public int getAnty() {
		return anty;
	}

	public void setAnty(int anty) {
		this.anty = anty;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getMaxRaise() {
		return maxRaise;
	}

	public void setMaxRaise(int maxRaise) {
		this.maxRaise = maxRaise;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int[] getLastPlays() {
		return lastPlays;
	}

	public int[] getBankrolls() {
		return bankrolls;
	}

	public void setLastPlays(ArrayList<Chair> chairs){
		this.lastPlays = new int[chairs.size()];
		int i = 0;
		for(Chair chair:chairs){
			this.lastPlays[i] = chair.getLastPlay();
			i++;
		}		
	}
	
	public void setBankrolls(ArrayList<Chair> chairs){
		this.bankrolls = new int[chairs.size()];
		int i = 0;
		for(Chair chair:chairs){
			this.bankrolls[i] = chair.getBankroll();
			i++;
		}		
	}
	
	
	
}
