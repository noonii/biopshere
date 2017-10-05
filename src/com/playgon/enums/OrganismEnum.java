package com.playgon.enums;

public enum OrganismEnum {

	ANIMAL(null),  
	PLANT(OrganismType.PLANT),
	OBJECT(OrganismType.INANIMATE_OBJECT); 
	
	private OrganismType type;
	
	private OrganismEnum(OrganismType type) {
		this.type = type;
	}
	
	public boolean isA(OrganismType type) {
		return this.type == type;
	}
	
	public void setOrganismType(OrganismType type) {
		this.type = type;		
	}	
	    
	public enum OrganismType {
		HUMAN,
		ALIEN,
		BIRD, 
		FISH,
		PLANT,
		HORSE,
		OTHER,
		INANIMATE_OBJECT;		
	}	
	
}
