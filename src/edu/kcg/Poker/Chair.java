package edu.kcg.Poker;

/**
 * プレイヤーがテーブルに参加した時に使用するデータを持つクラス.
 * @author Shun.S
 *
 */
public class Chair{
	public static final int HANDBITMASK_L = 0x03F;
	public static final int HANDBITMASK_R = 0xFC0;
	
	private Player player;
	private int bankroll;
	private int hands;
	private int hand;
	private int lastPlay;
	private int addedRaise;
	private int currentRaise;
	private boolean fold ;
	private boolean allin;
	private boolean winner;
	
	public Chair(Player player){
		this.player = player;
		lastPlay = 0;
		addedRaise = 0;
		currentRaise = 0;
		bankroll = 1000;
		fold = false;
		allin = false;
		winner = false;
	}
	/**
	 * プレイヤーが座る椅子の情報をまとめて取得する.
	 * @param id プレイヤーidをチェックする.
	 * @return 
	 */
	public Params packParams(){
		Params pack = new Params();
		pack.put("bankroll",bankroll);
		pack.put("hands",hands);
		pack.put("hand", hand);
		pack.put("lastPlay",lastPlay);
		pack.put("addedRaise", addedRaise);
		pack.put("currentRaise",currentRaise);
		pack.put("fold",fold);
		pack.put("allin",allin);
		pack.put("winner",winner);
		return pack;
	}
	
	public int choice(int maxBet,int limit){
		if(isFold()){
			return -2;
		}
		if(isAllin()){
			return 0;
		}
		AdaptStrategy st = (AdaptStrategy)player.getStrategy();
		st.setChairParams(this.packParams());
		int option = player.getStrategy().solveRaise();
		if(option>-1){
			int bet = maxBet + option - this.currentRaise;
			if(bankroll-bet<0){
				bet = bankroll;
				setAllin(true);
			}
			if(bet>limit){
				bet = limit;
			}
			this.addedRaise += bet;
			bankroll -= bet;
		}else if(option == -1){
			setFold(true);
		}
		this.setLastPlay(option);
		return option;
	}
	public int getCurrentRaise() {
		return currentRaise;
	}
	public void setCurrentRaise(int currentRaise) {
		this.currentRaise = currentRaise;
	}
	public int getHand() {
		return hand;
	}
	public void setHand(int hand) {
		this.hand = hand;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	public boolean isFold() {
		return fold;
	}
	public void setFold(boolean fold) {
		this.fold = fold;
	}
	public boolean isAllin() {
		return allin;
	}
	public void setAllin(boolean allin) {
		this.allin = allin;
	}
	public void setAddedBet(int addedBet) {
		this.addedRaise = addedBet;
	}
	public int getAddedBet() {
		return addedRaise;
	}
	public int getLastPlay() {
		return lastPlay;
	}


	public void setLastPlay(int lastPlay) {
		this.lastPlay = lastPlay;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getHands() {
		return hands;
	}
	public void setHands(int hands) {
		this.hands = hands;
	}
	public void payAnty(int x){
		this.setLastPlay(x);
		this.addedRaise+=x;
		this.currentRaise = x;
		bankroll -= x;
	}
	public int getBankroll() {
		return bankroll;
	}
	public void profit(int x){
		bankroll += x;
	}
	
	
}
