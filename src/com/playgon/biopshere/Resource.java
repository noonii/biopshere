package com.playgon.biopshere;

public interface Resource {
	
	public int getAmount();
	public void setAmount(int value);
	public boolean consume(int amount);
	public void addAmount(int amount);
	public void remAmount(int amount);

}
