package edu.kcg.Poker.Tester;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import edu.kcg.Poker.Common.HandChecker;


public class HandChekerTester{

	@Test
	public void testFlushByMax12() throws Exception{

		// コミュニティカード
		int[] com = { 1, 5, 6, 50, 51 };
		// プレイヤーの手札
		int handl = 11;
		int handr = 12;
		handl <<= 6;

		int expected = HandChecker.FL | 12;
		int actual = HandChecker.checkHand(handl | handr, com);
		
		assertThat(actual,is(expected));
	}
	
	@Test
	public void testStraightByMax4() throws Exception{
		// コミュニティカード 1:C 3:S 5:S H:12 H:11  
		int[] com = { 15, 39, 43 ,12, 11 };
		
		// プレイヤーの手札 4:H 2:D
		int handl = 3;
		int handr = 27;
		handl <<= 6;

		int expected = HandChecker.ST | 4;
		int actual = HandChecker.checkHand(handl | handr, com);
		
		assertThat(actual,is(expected));		
	}
	
	@Test
	public void testFullHouseBy1And2(){
		// コミュニティカード  
		int[] com = { 1, 14, 11 ,12, 2 };
		
		// プレイヤーの手札
		int handl = 27;
		int handr = 28;
		handl <<= 6;

		int expected = HandChecker.FU | 2;
		int actual = HandChecker.checkHand(handl | handr, com);
		
		assertThat(actual,is(expected));	
	}
	
	@Test
	public void testFourCardBy1(){
		// コミュニティカード  
		int[] com = { 1, 12, 11, 10, 14};
		
		// プレイヤーの手札
		int handl = 27;
		int handr = 40;
		handl <<= 6;

		int expected = HandChecker.FO | 1;
		int actual = HandChecker.checkHand(handl | handr, com);
		
		assertThat(actual,is(expected));	
	}
	
	@Test
	public void testThreeCardBy1(){
		// コミュニティカード  
		int[] com = { 1, 12, 11, 10, 19};
		
		// プレイヤーの手札
		int handl = 27;
		int handr = 40;
		handl <<= 6;

		int expected = HandChecker.TH | 1;
		int actual = HandChecker.checkHand(handl | handr, com);
		
		assertThat(actual,is(expected));	
	}
	
	@Test
	public void testTwoPairBy1And2(){
		// コミュニティカード  
		int[] com = { 1, 2, 11, 10, 19};
		
		// プレイヤーの手札
		int handl = 27;
		int handr = 41;
		handl <<= 6;

		int expected = HandChecker.TW | (2 << 6) | 1;
		int actual = HandChecker.checkHand(handl | handr, com);
		
		assertThat(actual,is(expected));	
	}
	
	@Test
	public void testOnePairBy1(){
		// コミュニティカード  
		int[] com = { 1, 2, 11, 10, 19};
				
		// プレイヤーの手札
		int handl = 27;
		int handr = 43;
		handl <<= 6;

		int expected = HandChecker.ON | 1;
		int actual = HandChecker.checkHand(handl | handr, com);
				
		assertThat(actual,is(expected));	
	}
	
	@Test
	public void testNoPairByMax7(){
		// コミュニティカード  
		int[] com = { 0, 1, 2, 3, 18};
				
		// プレイヤーの手札
		int handl = 19;
		int handr = 20;
		handl <<= 6;

		int expected = 7;
		int actual = HandChecker.checkHand(handl | handr, com);
				
		assertThat(actual,is(expected));
	}
	
}
