package entities;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import guis.Controller;


/**Represents a storage for adding and getting food items. using semaphores to not overextend the limit of the storage.
 * @author Filip
 *
 */
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
	
	/**
	 * @return how many percentages of max limit the storage is.
	 */
	public int getStorageProgress(){
		return read.availablePermits();
	}
	
	/**Adds a food item. If the storage is full this blocks the thread until there is room in the storage.
	 * @param item
	 * @throws InterruptedException
	 */
	public void addFoodItem(FoodItem item) throws InterruptedException {
		write.acquire();
		synchronized(lock){					
			items.add(item);
		}
		controller.updateStorageProgress(getStorageProgress());
		read.release();
	}
	
	/**Gets the first food item in the storage and removes it from storage. If the storage is empty this blocks the thread until there is a food item available.
	 * @return The first food item in storage
	 * @throws InterruptedException
	 */
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

	/**Peeks at the first available item in the list. If the storage is empty this blocks the thread until there is a food item available.
	 * @return The first food item in storage
	 * @throws InterruptedException
	 */
	public FoodItem peekNextItem() throws InterruptedException {
		read.acquire();
		FoodItem temp = items.peek();
		read.release();
		return temp;
	}
	
}
