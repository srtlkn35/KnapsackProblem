package system;

import java.util.ArrayList;
import java.util.Collections;

public class Item implements Comparable<Item> {

	private double weight;
	private double value;
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int compareTo(Item arg0) {
		
		if (this.value == arg0.value) {
			if (this.weight == arg0.weight) {
				return 0;
			}
			else if (this.weight < arg0.weight) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else if (this.value < arg0.value) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	public Item(double value, double weight) {
		super();
		this.value = value;
		this.weight = weight;
	}
	
	/**
	 * 
	 * @param itemL
	 * @return
	 */
	public ArrayList<Item> sort(ArrayList<Item> itemL) {
		
		ArrayList<Item> sortedItems = new ArrayList<Item>();
				
		sortedItems.addAll(itemL);
		
		Collections.sort(sortedItems);
		
		return sortedItems;
	}
}
