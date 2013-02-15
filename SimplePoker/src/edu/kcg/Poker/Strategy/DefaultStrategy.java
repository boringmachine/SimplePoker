package edu.kcg.Poker.Strategy;

import edu.kcg.Poker.Params;

public class DefaultStrategy extends AdaptStrategy {

	@Override
	public int solveRaise() {
		return 0;
	}

	@Override
	public void setParams(Params params) {
		this.params = params;
	}

}
