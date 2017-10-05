package com.playgon.biopshere;

public class ResourceImpl implements Resource {
	
	private int amount;
	
	/**
	 * Default Constructor, saves us from calling one in child class
	 */
	public ResourceImpl() {
		amount = 0;
	}
	
	public ResourceImpl(int amount) {
		this.amount = amount;
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public void setAmount(int value) {
		amount = value;		
	}

	@Override
	public boolean consume(int amount) {	
		// Finished entire stock, set to 0 cause no negatives
		if (amount > this.amount) {
			this.amount = 0;
			return false; // dead
		} else {
			this.amount -= amount;
			return true; // alive
		}
	}

	@Override
	public void addAmount(int amount) {
		this.amount += amount;		
	}	
	
	@Override
	public void remAmount(int amount) {
		this.amount -= amount;
	}
			
}
