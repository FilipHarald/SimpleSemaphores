package guis;

import entities.*;
import runnables.*;

public class Controller {
	private GUISemaphore gui;
	private Factory[] factories;
	private Thread[] factoryThreads;
	private Storage storage;
	private TruckThread truckThread;

	public Controller(GUISemaphore guiSemaphore) {
		this.gui = guiSemaphore;
		this.storage = new Storage(100, 100, 300);
		factories = new Factory[2];
		factoryThreads = new Thread[factories.length];
		factories[0] = new Factory("A", storage);
		factories[1] = new Factory("B", storage);
	}

	public void startFactory(int i) {
		factoryThreads[i] = new Thread(factories[i]);
		factoryThreads[i].start();
	}
	
	public void stopFactory(int i) {
		factoryThreads[i].interrupt();
		factoryThreads[i] = null;
	}

	public void startDelivering() {
		
	}
	
	private class TruckThread extends Thread{

		@Override
		public void run() {
			try {
				while(!Thread.interrupted()){
					new Thread(new Truck(storage, 10, 10)).start();
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			super.run();
		}
		
	}

}
