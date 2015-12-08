package guis;

import entities.*;
import runnables.*;

public class Controller {
	private GUISemaphore gui;
	private Thread[] factoryThreads;
	private Storage storage;

	public Controller(GUISemaphore guiSemaphore) {
		this.gui = guiSemaphore;
		this.storage = new Storage(100, 100, 300);
	}

	public void startFactory(int i) {
		factoryThreads[i] = new Thread(new Factory(storage));
		factoryThreads[i].start();
	}
	
	public void stopFactory(int i) {
		factoryThreads[i].interrupt();
		factoryThreads[i] = null;
	}

	public void startDelivering() {
		int maxW = 20;
		int maxV = 20;
		new Thread(new Truck(storage, maxW, maxV)).start();
		gui.setTruckLimits(maxW, maxV, 0);
		
	}
	
	public void updateTruckCargo(FoodItem item){
		gui.updateTruckCargo(item.getName());
	}
	
	/**
	 * @param nbr -1 or +1 
	 */
	public void updateStorageProgress(int percentage){
		gui.updateStorageProgress(percentage);
	}
	
	public void updateFactoryStatus(int factoryNbr, String status){
		gui.updateFactoryStatus(factoryNbr, status);
	}
	
	public void updateTruckStatus(String status){
		gui.updateTruckStatus(status);
	}
}
