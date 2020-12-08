package ks;


public class Item {
    int index;
	int value;
	int weight;
	
	public Item(int index, int value, int weight) {
		this.index = index;
		this.value = value;
		this.weight = weight;
	}

	public int getIndex() {
		return index;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}

	public int compareToWeight(Item pivot) {
		if(this.getWeight()>pivot.getWeight()) {
			return -1;
		}else if(this.getWeight()<pivot.getWeight()) {
			return 1;
		}else {
			return 0;			
		}
	}
	
	public int compareToLightest(Item pivot) {
		if(this.getWeight()>pivot.getWeight()) {
			return -1;
		}else if(this.getWeight()<pivot.getWeight()) {
			return 1;
		}else {
			return 0;			
		}
	}
	
	public int compareToValue(Item pivot) {
		if(this.getValue()>pivot.getValue()) {
			return -1;
		}else if(this.getValue()<pivot.getValue()) {
			return 1;
		}else {
			return 0;			
		}
	}
	
	public int compareToRelation(Item pivot) {
		float thisRelation = (float) this.getValue()/ (float) this.getWeight();
		float pivotRelation = (float) pivot.getValue()/(float) pivot.getWeight();
		if(thisRelation>pivotRelation) {
			return -1;
		}else if(thisRelation<pivotRelation) {
			return 1;
		}else {
			return 0;			
		}
	}
}
