package edu.kcg.Poker;

public class DefaultStrategy extends AdaptStrategy{

	@Override
	public int solveRaise() {
		return 0;
	}

	@Override
	public void setTableParams(Params tableParams) {
		// TODO 自動生成されたメソッド・スタブ
		this.tableParams = tableParams;
	}

	@Override
	public void setChairParams(Params chairParams) {
		// TODO 自動生成されたメソッド・スタブ
		this.chairParams = chairParams;		
	}

}
