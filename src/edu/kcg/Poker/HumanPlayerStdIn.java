package edu.kcg.Poker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 標準入力を使って人間がプレイする戦略のクラス.
 * @author Shun.S
 *
 */
public class HumanPlayerStdIn implements Strategy{

	private static int serial = 0;
	private int id;
	public HumanPlayerStdIn(){
		id = serial++;
	}
	
	@Override
	public int solveRaise() {
		int bet=-2;
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("ベットを入力:");
			String line = in.readLine();
			bet = Integer.parseInt(line);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bet;
	}


}
