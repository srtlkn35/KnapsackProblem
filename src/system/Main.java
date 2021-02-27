package system;

import java.util.ArrayList;
import java.util.Collections;

import system.Item;
import system.Knapsack;

public class Main {

    public static void main(String[] args) {
    	
    	ArrayList<Item> items = new ArrayList<Item>();
    	
//    	Integer totalCapacity = 15;
//    	Integer[] values = {4, 2, 2, 1, 10};
//    	Integer[] weights = {12, 1, 2, 1, 4};

    	Integer totalCapacity = 50;
    	Integer[] values = {60, 100, 120};
    	Integer[] weights = {10, 20, 30};

    	Knapsack knapsack = new Knapsack(totalCapacity);
    	for (int i = 0; i < weights.length; i++) {
        	items.add(new Item(values[i], weights[i]));
		}
    	
		System.out.println("Example Items List: ");
    	for (Item item : items) {
    		System.out.println(String.format("\t\t Idx: %03d, Value: %f, Weight: %f", items.indexOf(item), item.getValue(), item.getWeight()));
    	}
    	System.out.println("");
    	
    	double maximumValue = knapsack.getMaximumValue(items);
    	ArrayList<Item> insertedItems = knapsack.getMaximalItemSet(items);
		Collections.sort(insertedItems);
		System.out.println("KNAPSACK Capacity: " + knapsack.getCapacity());
		System.out.println("\t getMaximumValue() method returns: " + maximumValue);
		System.out.println("\t getMaximalItemSet() method returns: ");
    	for (Item item : insertedItems) {
    		System.out.println(String.format("\t\t Idx: %03d, Value: %f, Weight: %f", insertedItems.indexOf(item), item.getValue(), item.getWeight()));
    	}
    	if (insertedItems.size() <= 0) {
    		System.out.println("\t\t None!");
    	}
    }
}
