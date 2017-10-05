package com.playgon.constants;

import java.util.LinkedList;
import java.util.List;

import com.playgon.enums.OrganismEnum.OrganismType;
import com.playgon.resources.Food;
import com.playgon.resources.Oxygen;
import com.playgon.resources.Water;

public class ServerConstants {

	// Show errors etc?
	public static boolean DEBUG = true;
	// Allow user input?
	public static boolean USER_INPUT = true;
	// Store data... not practical but scope of assignment is small
	public static final List<Object> DIET = new LinkedList<>();

	/**
	 * The order here MUST be the same as com.playgon.inhabitants classes
	 * i.e. Parrot, Pigeon, Pine, Rose, Salmon, Trout
	 * If new classes are made, re-arrange the order	
	 */
	public static List<Object> loadandGetData() {
		if (DIET.isEmpty()) {
			/* Initialize organisms with data
			 * For simplicity, we will store static values in a list
			 * Alternatives: 
			 * 1 ) store final variables in parent class of inhabitants
			 * 2 ) Grab user input
			 * 3 ) Pull from DB
			 * 
			 * IMPORTANT: Entires must be in ALPHABETICAL order like in your package com.playgon.inhabitants
			 */

			// Because some organisms will not have certain resources, we're using objects
			DIET.add(new Object[] {new Food(3), new Oxygen(18), new Water(2), OrganismType.BIRD});
			DIET.add(new Object[] {new Food(4), new Oxygen(15), new Water(3), OrganismType.BIRD});
			DIET.add(new Object[] {new Oxygen(8), new Water(10), OrganismType.PLANT});
			DIET.add(new Object[] {new Oxygen(2), new Water(1), OrganismType.PLANT});
			DIET.add(new Object[] {new Food(1), new Oxygen(6), new Water(4), OrganismType.FISH});
			DIET.add(new Object[] {new Food(2), new Oxygen(3), new Water(6), OrganismType.FISH});

		} else {
			DIET.clear();
			loadandGetData();
		}
		return DIET;
	}



}
