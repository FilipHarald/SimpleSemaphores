package entities;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import guis.Controller;


public class Storage {

	private final Semaphore write;
	private final Semaphore read;
	private Queue<FoodItem> items = new LinkedList<FoodItem>();
	private Object lock = new Object();
	private Controller controller;
	private int maxItems;


	public Storage(int maxItems, Controller controller) {
		write = new Semaphore(maxItems, true);
		read = new Semaphore(0, true);
		this.controller = controller;
		this.maxItems = maxItems;
	}
	
	public int getStorageProgress(){
		return read.availablePermits();
	}
	
	public void addFoodItem(FoodItem item) throws InterruptedException {
		write.acquire();
		synchronized(lock){					
			items.add(item);
		}
		controller.updateStorageProgress(getStorageProgress());
		read.release();
	}
	
	public FoodItem getFoodItem() throws InterruptedException{
		FoodItem temp;
		read.acquire();
		synchronized(lock){
			temp = items.poll();
			System.out.println("Truck takes " + temp + " from storage");
		}
		controller.updateStorageProgress(getStorageProgress());
		write.release();
		return temp;
	}

	public FoodItem peekNextItem() throws InterruptedException {
		read.acquire();
		FoodItem temp = items.peek();
		read.release();
		return temp;
	}
	
}
