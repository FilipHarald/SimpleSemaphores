package runnables;
import java.util.Random;

import entities.*;
import guis.Controller;


/**Representing a factoy producing random food items.
 * @author Filip
 *
 */
public class Factory implements Runnable{
	private static FoodItem[] items = new FoodItem[10];
	private String name;
	private Storage storage;
	private Random rand;
	private Controller controller;
	
	public Factory(Controller controller, Storage storage, String name){
		rand = new Random();
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
		this.controller = controller;
	}
	
	/**Produces a random food item.
	 * @return
	 * @throws InterruptedException
	 */
	public FoodItem produceFoodItem() throws InterruptedException{
		Thread.sleep(rand.nextInt(1000));
		FoodItem item = items[rand.nextInt(items.length)];
		System.out.println("Factory " + name + " producing " + item);
		return item;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				controller.updateFactoryStatus(Integer.parseInt(name), "Working");
				FoodItem item = this.produceFoodItem();
				controller.updateFactoryStatus(Integer.parseInt(name), "Waiting");
				storage.addFoodItem(item);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
