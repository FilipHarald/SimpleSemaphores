package guis;

import entities.*;
import runnables.*;

/**Represents a controller which links the GUI with the rest of the classes.
 * @author Filip
 *
 */
public class Controller {
	private GUISemaphore gui;
	private Thread[] factoryThreads;
	private Storage storage;

	public Controller(GUISemaphore guiSemaphore) {
		this.gui = guiSemaphore;
		this.storage = new Storage(100, this);
		factoryThreads = new Thread[2];
	}

	/**Starts the factory with the specified number. (0 = A, 1 = B)
	 * @param i The specified factory
	 */
	public void startFactory(int i) {
		factoryThreads[i] = new Thread(new Factory(this, storage, "" + i));
		factoryThreads[i].start();
	}
	
	/**Stops the factory with the specified number. (0 = A, 1 = B)
	 * @param i The specified factory
	 */
	public void stopFactory(int i) {
		factoryThreads[i].interrupt();
		factoryThreads[i] = null;
	}

	/**Starts the truck to load and deliver.
	 * 
	 */
	public void startDelivering() {
		int maxW = 20;
		int maxV = 8;
		new Thread(new Truck(storage, maxW, maxV, this)).start();
		gui.setTruckLimits(maxW, maxV, 0);
		
	}
	
	/**Updates the cargo list in the gui with the specified value
	 * @param itemName
	 */
	public void updateTruckCargo(String itemName){
		gui.updateTruckCargo(itemName);
	}
	
	/**Updates the gui with the percentage of the storage max limit reached.
	 * @param the percentage
	 */
	public void updateStorageProgress(int percentage){
		gui.updateStorageProgress(percentage);
	}
	
	/**Updates the factory status in the gui.
	 * @param factoryNbr
	 * @param status
	 */
	public void updateFactoryStatus(int factoryNbr, String status){
		gui.updateFactoryStatus(factoryNbr, status);
	}
	
	/**Updates the truck status in the gui.
	 * @param status
	 */
	public void updateTruckStatus(String status){
		gui.updateTruckStatus(status);
	}
	/**Sets the second status in the gui to delivering or hidden.
	 * @param delivering
	 */
	public void setTruckDelivering(boolean delivering){
		gui.setTruckDelivering(delivering);	
	}
}
