package edu.kcg.Poker;

public abstract class AdaptStrategy implements Strategy{
	public Params tableParams;
	public Params chairParams;
	
	abstract protected void setTableParams(Params tableParams);
	abstract protected void setChairParams(Params chairParams);
}
