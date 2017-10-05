package com.playgon.biopshere;

import java.util.LinkedList;
import java.util.List;

import com.playgon.enums.OrganismEnum;
import com.playgon.enums.OrganismEnum.OrganismType;

// hm... to make an instance or not to, i wonder
public class Organism {

	// Instance variables	
	private List<Resource> organismDiet; // How much resources this organism needs
	private OrganismEnum type;	// Animal, Plant, Object
	private boolean alive;

	public Organism() {
		organismDiet = new LinkedList<>();
		setAlive(true);
	}


	/**
	 * Initialize instance variables
	 * @param water
	 * @param oxygen
	 * @param food
	 * @param type
	 */
	public void init(Object resources) {
		// Just incase...
		if (resources != null && resources instanceof Object[]) {					
			// Looping through Object array in list					
			for (Object resource : (Object[])resources) {	
				if (resource instanceof OrganismType) {
					this.setOrganismEnum((OrganismType)resource);
				} else {
					// The beauty of polymorphism
					organismDiet.add((Resource)resource);
				}						
			}
		}		
	}

	/**
	 * @return the organismDiet
	 */
	public List<Resource> getOrganismDiet() {
		return organismDiet;
	}

	/**
	 * @param organismDiet the organismDiet to set
	 */
	public void setOrganismDiet(List<Resource> organismDiet) {
		this.organismDiet = organismDiet;
	}

	/**
	 * @return the type
	 */
	public OrganismEnum getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(OrganismEnum type) {
		this.type = type;
	}

	/**
	 * We set the parent enum based on the child enum
	 * @param type
	 */
	public void setOrganismEnum(OrganismType type) {
		// Set parent enum
		switch (type) {
		case BIRD:
		case FISH:
		case HUMAN:
			this.type = OrganismEnum.ANIMAL;
			break;
		case PLANT:
			this.type = OrganismEnum.PLANT;
			break;
		default:
			this.type = OrganismEnum.OBJECT;
			break;				
		}
		// Now initialize child enum (i.e fish, bird, plant, etc)
		this.type.setOrganismType(type);
	}


	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void kill() {
		alive = false;
	}
	
	






}
