package com.playgon.resources;

import com.playgon.biopshere.ResourceImpl;

public class Water extends ResourceImpl {
	
	public Water() {
		
	}

	/**
	 * Initialize with amount of Water the organism consumes per day
	 * @param amount
	 */
	public Water(int amount) {
		setAmount(amount);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " x " + getAmount();
	}
}
