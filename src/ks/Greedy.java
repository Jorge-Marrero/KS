package ks;

import java.util.List;

public class Greedy {
    public static int [] solveGreedyHeaviest(List<Item> items, int capacity) {
		int weight = 0;
		int value = 0;
		int[] taken= new int[items.size()];
		Qs random = new Qs();
		items = random.quickSortHeaviest(items, 0, items.size()-1);
		for (Item item: items){
	        if (weight + item.getWeight() <= capacity) {
	        	taken[item.getIndex()] = 1;
    			value  += item.getValue();
    			weight += item.getWeight();	        	
	        }
		}
		return taken;
	}
	
	public static int [] solveGreedyLightest(List<Item> items, int capacity) {
		int weight = 0;
		int value = 0;
		int[] taken= new int[items.size()];
		Qs random = new Qs();
		items = random.quickSortLightest(items, 0, items.size()-1);
		for (int i = items.size()-1; i > 0;i--) {
			if (weight + items.get(i).getWeight() <= capacity) {
				taken[items.get(i).getIndex()] = 1;
    			value  += items.get(i).getValue();
    			weight += items.get(i).getWeight();
			}
		}
		return taken;
	}
	
	public static int [] solveGreedyValue(List<Item> items, int capacity) {
		int weight = 0;
		int value = 0;
		int[] taken= new int[items.size()];
		Qs random = new Qs();
		items = random.quickSortValue(items, 0, items.size()-1);
		for (Item item: items){
	        if (weight + item.getWeight() <= capacity) {
	        	taken[item.getIndex()] = 1;
    			value  += item.getValue();
    			weight += item.getWeight();	        	
	        }
		}
		return taken;
	}
	
	public static int [] solveGreedyRelation(List<Item> items, int capacity) {
		int weight = 0;
		int value = 0;
		int[] taken= new int[items.size()];
		Qs random = new Qs();
		items = random.quickSortRelation(items, 0, items.size()-1);
		for (int i = items.size()-1; i > 0;i--) {
			if (weight + items.get(i).getWeight() <= capacity) {
				taken[items.get(i).getIndex()] = 1;
    			value  += items.get(i).getValue();
    			weight += items.get(i).getWeight();
			}
		}
		return taken;
	}
}
