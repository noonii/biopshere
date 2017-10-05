package com.playgon.resources;

import com.playgon.biopshere.ResourceImpl;

public class Food extends ResourceImpl {
	
	public Food() {
		
	}
	
	/**
	 * Initialize with amount of food the organism consumes per day
	 * @param amount
	 */
	public Food(int amount) {
		setAmount(amount);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " x " + getAmount();
	}
}
