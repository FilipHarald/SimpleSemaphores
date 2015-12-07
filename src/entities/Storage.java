package entities;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;


public class Storage {

	private final Semaphore loadingDocks = new Semaphore(1, true);
	private Queue<FoodItem> items = new LinkedList<FoodItem>();
	private final double maxWeight;
	private final double maxVolume;
	private final int maxItems;
	private double currentWeight;
	private double currentVolume;
	private int currentItems;


	public Storage(double maxWeight, double maxVolume, int maxItems) {
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.maxItems = maxItems;
		currentWeight = 0;
		currentVolume = 0;
		currentItems = 0;
	}
	
	public boolean addFoodItem(FoodItem item) throws InterruptedException {
		boolean itemAdded = false;
		loadingDocks.acquire();
		if(currentWeight + item.getWeight() < maxWeight && currentVolume + item.getVolume() < maxVolume && currentItems + 1 < maxItems){			
			items.add(item);
			currentWeight += item.getWeight();
			currentVolume += item.getVolume();
			currentItems++;
			itemAdded =true;
		}
		loadingDocks.release();
		return itemAdded;
	}
	
	/**Checks if there if an FoodItem in the queue and if so, returns the item. Does not remove the item from the list.
	 * @return
	 */
	
	public FoodItem peekNextItem(){
		return items.peek();
	}
	
	public FoodItem getFoodItem() throws InterruptedException{
		loadingDocks.acquire();
		FoodItem temp = items.poll();
		currentWeight -= temp.getWeight();
		currentVolume -= temp.getVolume();
		loadingDocks.release();
		return temp;
	}
	
}
