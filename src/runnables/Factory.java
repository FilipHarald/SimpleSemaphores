package runnables;
import java.util.Random;

import entities.*;


public class Factory implements Runnable{
	private static FoodItem[] items = new FoodItem[10];
	private String name;
	private Storage storage;
	private Random rand;
	
	public Factory(String name, Storage storage){
		if(Factory.items[0] == null){			
			items[0] = new FoodItem(1.1, 0.5, "Milk");
			items[1] = new FoodItem(0.6, 1.1, "Cream");
			items[2] = new FoodItem(1.1, 0.5, "Yoghurt");
			items[3] = new FoodItem(2.32, 0.66, "Butter");
			items[4] = new FoodItem(3.4, 1.2, "Flower");
			items[5] = new FoodItem(3.7, 1.8, "Sugar");
			items[6] = new FoodItem(1.55, 0.27, "Salt");
			items[7] = new FoodItem(0.6, 0.19, "Almonds");
			items[8] = new FoodItem(1.98, 0.75, "Bread");
			items[9] = new FoodItem(1.4, 0.5, "Donuts");
		}
		this.name = name;
		this.storage = storage;
	}
	
	public FoodItem produceFoodItem() throws InterruptedException{
		Thread.sleep(rand.nextInt(2000) + 1000);
		return items[rand.nextInt(items.length)];
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				storage.addFoodItem(this.produceFoodItem());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
