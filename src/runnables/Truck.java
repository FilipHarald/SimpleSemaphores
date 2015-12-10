package runnables;
import java.util.LinkedList;

import entities.*;
import guis.Controller;


/**Represents a truck
 * @author Filip
 *
 */
public class Truck implements Runnable{
	private Storage storage;
	private LinkedList<FoodItem> cargo;
	private final double maxWeight;
	private final double maxVolume;
	private double currentWeight;
	private double currentVolume;
	private Controller controller;
	
	public Truck(Storage s, double maxWeight, double maxVolume, Controller controller){
		storage = s;
		cargo = new LinkedList<FoodItem>();
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		currentWeight = 0;
		currentVolume = 0;
		this.controller = controller;
	}
	
	/**Fills the truck with items from the storage. Ends when one of the trucks limit is reached. 
	 * @throws InterruptedException
	 */
	private void fillTruck() throws InterruptedException{
		boolean full = false;
		controller.updateTruckStatus("Waiting...");
		FoodItem item = storage.getFoodItem();
		while(!full){
			controller.updateTruckStatus("Loading...");
			Thread.sleep(500); // It takes time to load an item on to the truck...
			cargo.add(item);
			controller.updateTruckCargo(item.getName());
			currentWeight += item.getWeight();
			currentVolume += item.getVolume();
			controller.updateTruckStatus("Waiting...");
			item = storage.peekNextItem();
			if(currentWeight + item.getWeight() > maxWeight){
				controller.updateTruckStatus("Max weight");
				full = true;
			}else if(currentVolume + item.getVolume() > maxVolume){
				controller.updateTruckStatus("Max volume");
				full = true;
			}else{	
				item = storage.getFoodItem();
			}
		}
	}
	
	/**Empties the Truck
	 * 
	 */
	private void emptyTruck() {
		cargo = new LinkedList<FoodItem>();
		controller.updateTruckCargo("");
		currentWeight = 0; 
		currentVolume = 0;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				fillTruck(); // The driver fills the truck
				System.out.println("Truck driving to ICA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				controller.setTruckDelivering(true);
				Thread.sleep(300); //The truck drives to ICA 
				emptyTruck(); //The Truck empties at ICA
				controller.setTruckDelivering(false);
				Thread.sleep(300); //The truck drives back to storage
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
