package com.playgon.biopshere;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.playgon.constants.ServerConstants;
import com.playgon.main.main;

public class Biosphere {
	
	private HashMap<Object, Integer> resourceIndex;
	private List<Resource> resources;
		
	/**
	 * 	Your code should return the minimum resources needed in order for the supplied
	 *  organisms to survive the specified number of days. Output to the console
	 *  the required quantity of each resource
	 * @param organisms - com.playgon.inhabitants
	 * @param numDays - Days in ecosystem
	 * @return
	 */
	
	public List<Resource> getResources(List<Organism> organisms, int numDays) {
		return getResources(organisms, numDays, false);
	}
	
	public List<Resource> getResources(List<Organism> organisms, int numDays, boolean overwrite) {
		if (!overwrite && resources != null && !resources.isEmpty()) {
			return resources;
		}
		
		// Load all resource classes
		resources = (LinkedList<Resource>) main.loadAndGetClasses("com.playgon.resources", ResourceImpl.class);
		
		// Indexing for faster lookup 
		resourceIndex = new HashMap<>();
		for (int x = 0; x < resources.size(); x++) {			
			resourceIndex.put(resources.get(x).getClass(), x);
		}
		
		// Load all resource values
		ServerConstants.loadandGetData();	
				
		/* Initializing values
	 	 * Note: Values are how much organism consumes NOT have
	 	 */		
		int n = ServerConstants.DIET.size();
		for (int x = 0; x < n && x < organisms.size(); x++) {
			// Initialize values
			organisms.get(x).init(ServerConstants.DIET.get(x));				
			
			// OPTIMIZE THIS SHIT MORE. I DON'T HAVE TIME ATIQ.
			List<Resource> temp = organisms.get(x).getOrganismDiet();
			for (int i = 0; i < temp.size(); i++) { // i.e. 2-3 iterations
				// Dynamic class loading means this should never be null when grabbing index
				// Accumulating resources necessary to feed stock
				resources.get(resourceIndex.get(temp.get(i).getClass())).addAmount(temp.get(i).getAmount());				
			}
		}
		
		int deduct = 0;
		if (ServerConstants.USER_INPUT) {
			deduct = deductResources(); 
		}		
				
		// Multiply resources needed by number of days
		for (Resource res : resources) {
			res.setAmount(res.getAmount() * numDays - deduct);
		}
						
		System.out.println();
		printResources(resources, numDays, deduct, "Required resources for");					
		
		return resources;
	}


	/**
	 *	Your code should output to the console the organisms still alive at the end of
	 *	each day. If an organism's full resource requirements cannot be satisfied at the
	 *	beginning of the day, that organism will have expired by the end of the day
	 * @param organisms - com.playgon.inhabitants
	 * @param resources - com.playgon.resources
	 * @param numDays - Days in ecosystem
	 */
	public void runSimulation(List<Organism> organisms, List<Resource> resources, int numDays) {
		if (organisms.isEmpty() || resources.isEmpty()) {
			System.out.println("Looks like you have no organisms or resources.");
			return;
		}
		
		System.out.println("Running simulation...\r\n");
		
		// Loop through days
		for (int x = 0; x < numDays; x++) {			
						
			// Recording organisms
			StringBuffer sb = new StringBuffer();
			
			// Each iteration, each organism begin to consume from overall resource
			for (int y = 0; y < organisms.size(); y++) {		
										
				if (organisms.get(y).isAlive()) // If alive, append 
					sb.append(organisms.get(y).getClass().getSimpleName()).append(", ");
				else {// else remove from list
					System.out.println("---------------Your " + organisms.get(y).getClass().getSimpleName() + " is dead.");										
				}
														
				// OPTIMIZE THIS SHIT MORE. I DON'T HAVE TIME ATIQ.
				List<Resource> temp = organisms.get(y).getOrganismDiet();				
				for (int z = 0; z < temp.size(); z++) { // i.e. 2-3 iterations
					// Dynamic class loading means this should never be null when grabbing index
					// Stock is consuming our resources each day
					boolean full = resources.get(resourceIndex.get(temp.get(z).getClass())).consume(temp.get(z).getAmount());
					
					if (!full) { // did not eat their fill, dies next morning
						organisms.get(y).setAlive(false);
					}
				}
			}
										
			// Prints organism names that are alive
			if (sb.length() == 0) sb.append("YOU KILLED EVERYONE."); 
			else sb.deleteCharAt(sb.length()-2);
						 
			System.out.println("Day : " + (x+1) + " - " + sb.toString());
		}
					
		System.out.println();
		printResources(resources, numDays, 0, "Remaining resources after");
	}

	/**
	 * Grabs user input for remving resources
	 * @param deduct
	 * @return
	 */
	private int deductResources() {
		int deduct = 0;
		// Just cause
		Scanner scan = new Scanner(System.in);			
		do {			
			System.out.println("Enter [ -1 ] to ignore and continue.");
			System.out.println("Hiding Resources can kill your organisms.");
			System.out.print("How much resources would you like to remove from each? : ");

			try {
				deduct = Integer.parseInt(scan.nextLine());
				if (deduct >= 0) {					
					return deduct;
				}
			} catch (NumberFormatException e) {				
				System.out.println("Please enter a number.");
			}	

		} while (deduct != -1);
		scan.close();
		return 0;		
	}

	/**
	 * Print current stock of resources
	 * @param list - resources
	 * @param numDays - Number of days in ecosystem
	 */
	private void printResources(List<Resource> list, int numDays, int deduct, String message) {		
		// Loops and printing to console slows operation down a lot 
		if (ServerConstants.DEBUG  && !list.isEmpty()) {
			System.out.println(message + " " + numDays + " days...\r\n");
			for (Resource re : list) {
				// I don't want to create a new object twice just for this so this saves memory/time
				re.addAmount(deduct); // add for sake of printing				
				System.out.println(re);
				re.remAmount(deduct); // remove to get back to original state	
			}
			System.out.println();
		}		
	}

}
