package edu.kcg.Poker;

public interface PokerHand {

	/**
	 * 各役の強さの値.
	 */
	public static int FL = 0x1000, ST = 0x800, TH = 0x400, TW = 0x200,
			ON = 0x100, FO = FL | TH | ON, FU = (TH | ON) << 2, SF = FL | ST;

	/**
	 * 上位6ビットと下位6ビットに分けて手札を管理するマスク。
	 */
	public static final int HAND_L = 0xFC0, HAND_R = 0x3F;

	public int checkHand(int hands);

}
