package edu.kcg.Poker;

/**
 * ゲームに必要な機能を定義したインターフェイス.
 * 
 * @author Shun.S
 * 
 */
public interface GameRules {

	/**
	 * 手番の種類を数値として定義。
	 */
	public static final int FIRST = 0, HUMAN = 1, CHANCE = 2, FINAL = 3;

	/**
	 * 偶然手番の処理.
	 */
	abstract void chancePhase();

	/**
	 * 手番の実行手順を定義する。
	 */
	abstract void execute();

	/**
	 * ゲーム終了時の処理.
	 */
	abstract void finalPhase();

	/**
	 * ゲーム開始時の処理.
	 */
	abstract void firstPhase();

	/**
	 * ゲームの状態を返す.GameRulesのstatic定数に対応するようにする.
	 * 
	 * @return
	 */
	abstract int gameStatus();

	/**
	 * 人為手番の処理.
	 */
	abstract void humanPhase();

	/**
	 * 次の手番へ遷移する。
	 * 
	 * @return 遷移後の手番の種類。GameRulesのstatic定数に対応するようにする。
	 */
	abstract int nextPhase();

}
