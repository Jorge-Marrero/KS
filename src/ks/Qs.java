package ks;
import java.util.*;

public class Qs {    
    public List<Item> quickSortHeaviest(List<Item> items,int low, int high){
        if (low >= high || low < 0 || high > items.size()-1)
            return null;
    	int pivotIndex = new Random().nextInt(high - low + 1) + low;
        Item pivot = items.get(pivotIndex);
        Collections.swap(items, pivotIndex, low);
        int i = low;
        int j = high;
        while (i <= j) {
            while (items.get(i).compareToWeight(pivot) <= 0 && i < j) {
                i++;
            }
            while (items.get(j).compareToWeight(pivot) > 0 && j >= i) {
                j--;
            }
            // break here when two pointers meet since the pivot position has been found
            if (i >= j)
                break;
            // switches the two values reached through the while loops
            Collections.swap(items, i, j);
        }
        // moves the pivot to where j is
        Collections.swap(items, low, j);
        // calls the method recursively
        quickSortHeaviest(items, low, j - 1);
        quickSortHeaviest(items, j + 1, high);
        return items;
    }
    
    public List<Item> quickSortLightest(List<Item> items,int low, int high){
        if (low >= high || low < 0 || high > items.size()-1)
            return null;
    	int pivotIndex = new Random().nextInt(high - low + 1) + low;
        Item pivot = items.get(pivotIndex);
        Collections.swap(items, pivotIndex, low);
        int i = low;
        int j = high;
        while (i <= j) {
            while (items.get(i).compareToLightest(pivot) <= 0 && i < j) {
                i++;
            }
            while (items.get(j).compareToLightest(pivot) > 0 && j >= i) {
                j--;
            }
            // break here when two pointers meet since the pivot position has been found
            if (i >= j)
                break;
            // switches the two values reached through the while loops
            Collections.swap(items, i, j);
        }
        // moves the pivot to where j is
        Collections.swap(items, low, j);
        // calls the method recursively
        quickSortLightest(items, low, j - 1);
        quickSortLightest(items, j + 1, high);
        return items;
    }
    
    public List<Item> quickSortValue(List<Item> items,int low, int high){
        if (low >= high || low < 0 || high > items.size()-1)
            return null;
    	int pivotIndex = new Random().nextInt(high - low + 1) + low;
        Item pivot = items.get(pivotIndex);
        Collections.swap(items, pivotIndex, low);
        int i = low;
        int j = high;
        while (i <= j) {
            while (items.get(i).compareToValue(pivot) <= 0 && i < j) {
                i++;
            }
            while (items.get(j).compareToValue(pivot) > 0 && j >= i) {
                j--;
            }
            // break here when two pointers meet since the pivot position has been found
            if (i >= j)
                break;
            // switches the two values reached through the while loops
            Collections.swap(items, i, j);
        }
        // moves the pivot to where j is
        Collections.swap(items, low, j);
        // calls the method recursively
        quickSortValue(items, low, j - 1);
        quickSortValue(items, j + 1, high);
        return items;
    }
    
    public List<Item> quickSortRelation(List<Item> items,int low, int high){
        if (low >= high || low < 0 || high > items.size()-1)
            return null;
    	int pivotIndex = new Random().nextInt(high - low + 1) + low;
        Item pivot = items.get(pivotIndex);
        Collections.swap(items, pivotIndex, low);
        int i = low;
        int j = high;
        while (i <= j) {
            while (items.get(i).compareToRelation(pivot) <= 0 && i < j) {
                i++;
            }
            while (items.get(j).compareToRelation(pivot) > 0 && j >= i) {
                j--;
            }
            // break here when two pointers meet since the pivot position has been found
            if (i >= j)
                break;
            // switches the two values reached through the while loops
            Collections.swap(items, i, j);
        }
        // moves the pivot to where j is
        Collections.swap(items, low, j);
        // calls the method recursively
        quickSortRelation(items, low, j - 1);
        quickSortRelation(items, j + 1, high);
        return items;
    }
}
