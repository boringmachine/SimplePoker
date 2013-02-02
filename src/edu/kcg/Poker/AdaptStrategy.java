package edu.kcg.Poker;

public abstract class AdaptStrategy implements Strategy{
	public Params params;
	
	abstract protected void setParams(Params tableParams);
}
