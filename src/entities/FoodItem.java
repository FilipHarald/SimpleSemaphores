package entities;
import java.util.Random;

public class FoodItem {
	private double weight;
	private double volume;
	private String name;
	

	public FoodItem(double weight, double volume, String name){
		this.weight = weight;
		this.volume = volume;
		this.name = name;
	}
	 
	public double getWeight() {
		return weight;
	}

	public double getVolume() {
		return volume;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}	

}
