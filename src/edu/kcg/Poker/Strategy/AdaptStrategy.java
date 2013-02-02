package edu.kcg.Poker.Strategy;

import edu.kcg.Poker.Params;

public abstract class AdaptStrategy implements Strategy {
	public Params params;

	abstract public void setParams(Params tableParams);
}
