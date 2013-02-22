package edu.kcg.Poker;

/**
 * ゲームに必要な機能を定義したインターフェイス.
 * 
 * @author Shun.S
 * 
 */
public interface GameRules {

	/**
	 * 手番の実行手順を定義する。
	 */
	abstract void execute();

}
