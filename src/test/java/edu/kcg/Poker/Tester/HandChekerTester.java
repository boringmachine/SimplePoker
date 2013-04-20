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
	public static Fixture[] f = {
			new Fixture(new int[]{0,1,2,3,4},HandChecker.SF | 4),
			new Fixture(new int[]{39,48,49,50,51},HandChecker.SF | 13),
			new Fixture(new int[]{0,0+13,0+26,0+39},HandChecker.FO),
			new Fixture(new int[]{12,12+13,12+26,12+39},HandChecker.FO | 12),
			new Fixture(new int[]{0,0+13,0+26,1,1+13},HandChecker.FU | 1),
			new Fixture(new int[]{0,2,4,6,8},HandChecker.FL | 8),
			new Fixture(new int[]{0,1,2,3,4+13},HandChecker.ST | 4),
			new Fixture(new int[]{9,10,11,12,13},HandChecker.ST | 13),
			new Fixture(new int[]{0,0+13,0+26},HandChecker.TH),
			new Fixture(new int[]{0,0+13,3,3+13},HandChecker.TW | 3<<6),
			new Fixture(new int[]{0,0+13},HandChecker.ON),
			new Fixture(new int[]{12},12),

	};
	
	@Theory
	public void testCheckHand(Fixture f) throws Exception{
		assertThat(HandChecker.checkHand(f.hand, f.com),is(f.expected));
	}
	
	static class Fixture{
		int[] com;
		int hand;
		int expected;
		
		Fixture(int[] c, int e){
			this.com = c;
			this.hand = 0xF | 0xE <<6;
			this.expected = e;
		}
	}
}
