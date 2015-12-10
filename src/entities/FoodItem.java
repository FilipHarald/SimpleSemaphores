package entities;
import java.util.Random;

/**Represents a food item.
 * @author Filip
 *
 */
public class FoodItem {
	private double weight;
	private double volume;
	private String name;
	

	public FoodItem(double weight, double volume, String name){
		this.weight = weight;
		this.volume = volume;
		this.name = name;
	}
	 
	/**
	 * @return The weight of the food item.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @return The volume of the food item
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * @return The name of the food item
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}	

}
