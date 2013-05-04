package edu.kcg.Poker.Tester;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import edu.kcg.Poker.Common.HandChecker;

@RunWith(Theories.class)
public class HandChekerTester {

	@DataPoints
	public static P[] f = {
			new P(new int[]{0,1,2,3,4},HandChecker.SF | 4),
			new P(new int[]{39,48,49,50,51},HandChecker.SF | 13),
			new P(new int[]{0,0+13,0+26,0+39},HandChecker.FO),
			new P(new int[]{12,12+13,12+26,12+39},HandChecker.FO | 12),
			new P(new int[]{0,0+13,0+26,1,1+13},HandChecker.FU | 1),
			new P(new int[]{0,2,4,6,8},HandChecker.FL | 8),
			new P(new int[]{0,1,2,3,4+13},HandChecker.ST | 4),
			new P(new int[]{9,10,11,12,13},HandChecker.ST | 13),
			new P(new int[]{0,0+13,0+26},HandChecker.TH),
			new P(new int[]{0,0+13,3,3+13},HandChecker.TW | 3<<6),
			new P(new int[]{0,0+13},HandChecker.ON),
			new P(new int[]{12},12),

	};
	
	@Theory
	public void testCheckHand(P f) throws Exception{
		assertThat(HandChecker.checkHand(f.hand, f.com),is(f.expected));
	}
	
	static class P{
		int[] com;
		int hand;
		int expected;
		
		P(int[] c, int e){
			this.com = c;
			this.hand = 0xF | 0xE <<6;
			this.expected = e;
		}
	}
}
