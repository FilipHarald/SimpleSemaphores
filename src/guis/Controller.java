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
		new Thread(new Truck(storage, 20, 20)).start();
	}
}
