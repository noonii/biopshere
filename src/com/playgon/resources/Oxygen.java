package com.playgon.resources;

import com.playgon.biopshere.ResourceImpl;

public class Oxygen extends ResourceImpl {
	
	public Oxygen() {
		
	}

	/**
	 * Initialize with amount of Oxygen the organism consumes per day
	 * @param amount
	 */
	public Oxygen(int amount) {
		setAmount(amount);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " x " + getAmount();
	}
}
