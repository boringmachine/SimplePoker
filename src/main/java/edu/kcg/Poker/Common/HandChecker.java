package edu.kcg.Poker.Common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HandChecker {

	/**
	 * 各役の強さの値.
	 */
	public static final int FL = 0x1000, ST = 0x800, TH = 0x400, TW = 0x200,
			ON = 0x100, FO = FL | TH | ON, FU = (TH | ON) << 2, SF = FL | ST;

	/**
	 * 上位6ビットと下位6ビットに分けて手札を管理するマスク。
	 */
	public static final int HAND_L = 0xFC0, HAND_R = 0x3F;

	public static int checkHand(int hands, int[] comcard) {
		int[] cards;
		cards = Arrays.copyOf(comcard, comcard.length + 2);
		cards[cards.length - 1] = hands & HAND_R;
		cards[cards.length - 2] = (hands & HAND_L) >> 6;
	
		int fBit = 0;
		int sBit = 0;

		fBit |= checkFlush(cards);
		sBit |= checkStraight(cards);
		
		if (fBit > 0 && sBit > 0) {
				sBit |= HandChecker.SF;
				return sBit;
		}
		
		int bit = fBit > sBit ? fBit : sBit;		
		
		int buf = bit;
		bit = checkOther(cards);

		if (buf > bit) {
			bit = buf;
		}
		
		return bit;
	}

	/**
	 * 各カードのマークを調べる。
	 * 
	 * @param cards
	 * @return
	 */
	private static int[] cardMarks(int[] cards) {
		int[] buf = new int[cards.length];
		int i = 0;
		for (int x : cards) {
			buf[i++] = x / 13;
		}
		return buf;
	}

	/**
	 * 各カードの数字を調べる。
	 * 
	 * @param cards
	 * @return
	 */
	private static int[] cardNums(int[] cards) {
		int[] buf = new int[cards.length];
		int i = 0;
		for (int x : cards) {
			buf[i++] = x % 13;
		}
		return buf;
	}

	/**
	 * フラッシュかどうかチェック。
	 * 
	 * @param cards
	 * @return
	 */
	private static int checkFlush(int[] cards) {
		int i = 0;
		int[] buf = cardMarks(cards);
		while (i < 4) {
			int count = 0;
			int max = 0;
			for (int j = buf.length - 1; j > -1; j--) {
				if (buf[j] == i) {
					++count;
					int num = cards[j] % 13;
					if (max < num) {
						max = num;
					}
				}
			}
			if (count > 4) {
				return max | HandChecker.FL;
			}
			i++;
		}
		return 0;
	}

	/**
	 * 数字の枚数を用いる役のチェッカー。
	 * 
	 * @param cards
	 * @return
	 */
	private static int checkOther(int[] cards) {
		// 各数字の枚数をカウントした配列。
		int[] buf = countNums(cards);
		int bit = 0;
		boolean three = false, two = false, one = false, nopair = false;
		for (int i = 12; i > -1; i--) {
			if (buf[i] == 4) {
				// 4ペアなら即リターン
				return (HandChecker.FO | i);
			} else if (buf[i] == 3) {
				if (three && one) {
					// すでにフルハウスが決定しているなら何もしない。
				} else if (one || three) {
					// ワンペアまたはスリーカードが決まっているならフルハウス。
					bit &= HandChecker.HAND_R;
					bit |= HandChecker.FU;
				} else {
					// 役が決まっていないならスリーカード。
					bit = (HandChecker.TH | i);
				}
				// スリーカードのフラグをtrueにする。
				three = true;
			} else if (buf[i] == 2) {
				if (three && one) {
					// すでにフルハウスが決定しているなら何もしない。
				} else if (three) {
					// スリーカードが決まっているならフルハウス。
					bit &= HandChecker.HAND_R;
					bit |= HandChecker.FU;
				} else if (!two) {
					// ツーペアが決まっていない場合。
					if (one) {
						// ワンペアが決まっているならツーペア。
						bit = (bit & HandChecker.HAND_R) << 6;
						bit |= (HandChecker.TW | i);
						// ツーペアのフラグをtrueにする。
						two = true;
					} else {
						// ワンペアが決まっていないならワンペア。
						bit = (HandChecker.ON | i);
					}
				}
				// ワンペアのフラグをtrueにする。
				one = true;
			} else if (buf[i] == 1) {
				if (three || two || one || nopair) {
					// 何かの役が決まっているなら何もしない。
				} else {
					// 役が決まっていないならハイカード。
					bit = i;
					nopair = true;
				}
			}
		}
		// 役の強さを返す。
		return bit;
	}

	/**
	 * ストレートをチェック。
	 * 
	 * @param cards
	 * @return
	 */
	private static int checkStraight(int[] cards) {
		int[] buf = cardNums(cards);
		int[] sorted = mysort(buf);

		if (sorted.length < 5)
			return (0);
		if (sorted[0] == 0) {
			buf = Arrays.copyOf(sorted, sorted.length);
			sorted = new int[buf.length + 1];
			int i = 0;
			for (i = 0; i < buf.length; i++) {
				sorted[i] = buf[i];
			}
			sorted[i] = 13;
		}
		int i = sorted.length - 1;
		while (i > 3) {
			if (sorted[i - 4] + 4 == sorted[i])
				return (sorted[i] | HandChecker.ST);
			i--;
		}

		return 0;
	}

	/**
	 * 各数字の枚数を数える。
	 * 
	 * @param cards
	 * @return 数えた回数を配列として返す。
	 */
	private static int[] countNums(int[] cards) {
		int[] buf = cardNums(cards);
		int[] cardnum = new int[13];
		Arrays.fill(cardnum, 0);
		for (int x : buf) {
			cardnum[x]++;
		}
		return cardnum;
	}

	/**
	 * 重複を取り除いてソートする。
	 * 
	 * @param cards
	 * @return
	 */
	private static int[] mysort(int[] cards) {
		Set<Integer> set = new HashSet<Integer>();
		// 集合クラスは重複を捨てる性質がある.
		for (int x : cards) {
			set.add(x);
		}
		Object[] buf = set.toArray();
		int[] array = new int[buf.length];
		int i = 0;
		for (Object x : buf) {
			array[i] = (Integer) x;
			i++;
		}
		return array;
	}

}
