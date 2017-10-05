package com.playgon.main;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.reflections.Reflections;

import com.playgon.biopshere.Biosphere;
import com.playgon.biopshere.Organism;
import com.playgon.biopshere.Resource;
import com.playgon.constants.ServerConstants;

public class main {
	// Class Variable 
	private static main instance;
	// Instance Variable
	private Biosphere biosphere;	
	private List<Organism> organisms;
	
	public static main getInstance() {
		if (instance == null)
			instance = new main();
		return instance;
	}	
	
	public void setBiosphere(Biosphere bio) {
		this.biosphere = bio;
	}
	
	public Biosphere getBiosphere() {
		return biosphere;
	}
	
	public void setOrganisms(List<Organism> list) {
		this.organisms = list;
	}
	
	public List<Organism> getOrganisms() {
		return organisms;
	}
	
	/**
	 * Run Simulation 
	 */
	private void run() {
		// Initialize instance variables
		setBiosphere(new Biosphere());
		setOrganisms((LinkedList<Organism>) loadAndGetClasses("com.playgon.inhabitants", Organism.class));												 							
		
		// Get the the resources
		List<Resource> resources = getBiosphere().getResources(getOrganisms(), 5);
											
		// Run the Biosphere
		getBiosphere().runSimulation(getOrganisms(), resources, 5);				
	}
	
	/**
	 * Starts application
	 * @param args
	 */
	public static void main(String[] args) {
		long start = 0;
		if (!ServerConstants.USER_INPUT) 
			start = System.currentTimeMillis();
		
		/* Starting instance
		 * Can be useful if I want to grab it from anywhere */
		main.getInstance().run();
				
		if (!ServerConstants.USER_INPUT) {
			long end = System.currentTimeMillis() - start;
			System.out.println("Time taken(ms): " + end);
			System.out.println("Time Taken(s): " + TimeUnit.MILLISECONDS.toSeconds(end));			
		} else {			
			System.out.println("\r\nPer instructions, the organisms die the next morning and NOT during that day.");
			System.out.println("So... that's why you won't see deaths at day 5 if your removed resource amount is low.");
		}
	}

	/**
	 * Reads all the classes in given package and instantiates them with
	 * no parameter values passed
	 * 
	 * @return - list of objects
	 */
	public static List<?> loadAndGetClasses(String packagename, Class<?> classType) {
		List<Object> organisms = new LinkedList<>();
		// Get classes from package
		Reflections reflections = new Reflections(packagename);
		// Get subclasses if inheritance is in play
		Set<?> classes = reflections.getSubTypesOf(classType);
		// Loop through each child-class and add to list
		for (Object cs : classes) {
			try {				
				// Instantiate and initialize class into list
				organisms.add(((Class<?>) cs).newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				if (ServerConstants.DEBUG) {
					//e.printStackTrace();
					System.out.println("ERROR: INCORRECT FORMAT for class - " + ((Class<?>) cs));					
					System.err.println("ERROR: You can add more content, as long as you have the basic format.");					
				}				
			}
		}
		// Likely never empty though 
		if (organisms.isEmpty()) return null;
		
		// Sort classes similar to order under their package
		organisms.sort(new Comparator<Object>() { // Java 7
			@Override
			public int compare(Object a, Object b) {
				return a.getClass().getName().compareTo(b.getClass().getName());
			}			
		});		
		
		return organisms;
	}




}
