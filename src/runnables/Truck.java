package runnables;
import java.util.LinkedList;

import entities.*;


public class Truck implements Runnable{
	private Storage storage;
	private LinkedList<FoodItem> cargo;
	private final double maxWeight;
	private final double maxVolume;
	private double currentWeight;
	private double currentVolume;
	
	public Truck(Storage s, double maxWeight, double maxVolume){
		storage = s;
		cargo = new LinkedList<FoodItem>();
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		currentWeight = 0;
		currentVolume = 0;
	}
	
	private void fillTruck() throws InterruptedException{
		boolean full = false;
		FoodItem item = storage.getFoodItem();
		while(!full){
			cargo.add(item);
			currentWeight += item.getWeight();
			currentVolume += item.getVolume();
			item = storage.peekNextItem();
			if(currentWeight + item.getWeight() > maxWeight || currentVolume + item.getVolume() > maxVolume){
				full = true;
			}else{				
				item = storage.getFoodItem();
			}
		}
	}
	
	@Override
	public void run() {
		try {
			fillTruck();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
