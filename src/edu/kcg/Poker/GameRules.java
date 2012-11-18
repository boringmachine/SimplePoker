package edu.kcg.Poker;
/**
 * ゲームに必要な機能を定義したインターフェイス.
 * @author Shun.S
 *
 */
public interface GameRules {

	/**
	 * 手番の種類を数値として定義。
	 */
	public static final int FIRST=0,HUMAN=1,CHANCE=2,FINAL=3;
	
	/**
	 * 手番の実行手順を定義する。
	 */
	void execute();
	
	/**
	 * ゲームの状態を返す.GameRulesのstatic定数に対応するようにする.
	 * @return
	 */
	int gameStatus();
	
	/**
	 * ゲーム開始時の処理.
	 */
	void firstPhase();
	
	/**
	 * ゲーム終了時の処理.
	 */
	void finalPhase();
	
	/**
	 * 偶然手番の処理.
	 */
	void chancePhase();
	
	/**
	 * 人為手番の処理.
	 */
	void humanPhase();
	
	/**
	 * 次の手番へ遷移する。
	 * @return 遷移後の手番の種類。GameRulesのstatic定数に対応するようにする。
	 */
	int nextPhase();
	
}
