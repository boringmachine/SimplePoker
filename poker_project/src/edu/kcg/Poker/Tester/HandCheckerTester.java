package edu.kcg.Poker.Tester;

import edu.kcg.Poker.HandChecker;

/**
 * 役判定モジュールのテスター。
 */
public class HandCheckerTester {
	public static void main(String[] args) {

		// コミュニティカード
		int[] com = { 5, 15, 4, 8, 10 };
		
		// プレイヤーの手札
		int handl = 51;
		int handr = 50;
		
		handl <<= 6;
		
		System.out.println(HandChecker.checkHand(handl|handr, com));
		
	}
}
