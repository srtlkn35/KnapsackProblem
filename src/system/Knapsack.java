package system;

import java.util.ArrayList;
import java.util.Collections;

import system.Item;

public class Knapsack {

	private double capacity;
	private ArrayList<Item> Items;
	
	public double getCapacity() {
		return capacity;
	}
	
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	public ArrayList<Item> getItems() {
		return Items;
	}
	
	public void setItems(ArrayList<Item> items) {
		Items = items;
	}
	
	public Knapsack(double capacity) {
		super();
		this.capacity = capacity;
		Items = new ArrayList<Item>();
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean pushItem(Item item) {
		
		ArrayList<Item> items = new ArrayList<Item>();
		double currentWeight = 0;
		
		items.addAll(getItems());
		for (Item _item : items) {
			currentWeight += _item.getWeight();
		}
		
		if ((currentWeight + item.getWeight()) > getCapacity()) {
			return false;
		}
		
		items.add(item);
		
		setItems(items);
		
		return true;
	}
	
	/**
	 * 
	 * @param newItems
	 * @return
	 */
	public double getMaximumValue(ArrayList<Item> newItems) {
		
		double currentValue = 0;
		double currentCapacity = 0;
		ArrayList<Item> items = new ArrayList<Item>();

		items.addAll(getItems());
		Collections.sort(items);
		for (Item _item : items) {
			currentCapacity += _item.getWeight();
		}

		items.addAll(solve((this.capacity - currentCapacity), newItems));
		
		for (Item _item : items) {
			currentValue += _item.getValue();
		}

		return currentValue;
	}

	/**
	 * 
	 * @param newItems
	 * @return
	 */
	public ArrayList<Item> getMaximalItemSet(ArrayList<Item> newItems) {
		
		double currentCapacity = 0;
		ArrayList<Item> items = new ArrayList<Item>();

		items.addAll(getItems());
		Collections.sort(items);
		for (Item _item : items) {
			currentCapacity += _item.getWeight();
		}

		items.addAll(solve((this.capacity - currentCapacity), newItems));
		
		return items;
	}

	/**
	 * 
	 * @param capacity
	 * @param newItems
	 * @return
	 */
	public ArrayList<Item> solve(double capacity, ArrayList<Item> newItems) {

		Collections.sort(newItems);
		Item[] _items = new Item[newItems.size()];
		for (int idx=0; idx<newItems.size(); idx++) {
			_items[idx] = newItems.get(idx);
		}
	    
		int NB_ITEMS = _items.length;
		double[][] matrix = new double[NB_ITEMS + 1][(int) (Math.ceil(capacity) + 1)];

		for (int i = 0; i <= capacity; i++) {
			matrix[0][i] = 0;
		}

		for (int i = 1; i <= NB_ITEMS; i++) {
			for (int j = 0; j <= capacity; j++) {
				if (_items[i - 1].getWeight() > j) {
					matrix[i][j] = matrix[i - 1][j];
				}
				else {
					matrix[i][j] = (int) Math.max(matrix[i - 1][j], matrix[i - 1][(int) (j - _items[i - 1].getWeight())] + _items[i - 1].getValue());
				}
			}
		}

		double res = matrix[NB_ITEMS][(int) Math.ceil(capacity)];
		int w = (int) Math.ceil(capacity);
		
		
		ArrayList<Item> itemsSolution = new ArrayList<Item>();
		for (int i = NB_ITEMS; i > 0  &&  res > 0; i--) {
			if (res != matrix[i - 1][w]) {
				itemsSolution.add(_items[i - 1]);
				res -= _items[i - 1].getValue();
				w -= _items[i - 1].getWeight();
			}
		}

		return itemsSolution;
	}	  
}
