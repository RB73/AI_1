package AIPack1;

import java.util.ArrayList;

public class Organism {
	private float start;
	private float goal;
	private ArrayList<Integer> operations;
	private float fitnessFunction;
	private float error;
	private int size; //number of operations the organism has
	private AIMath math;
	
	public Organism (float start, float goal, ArrayList<Integer> operations, AIMath math){
		this.start = start;
		this.goal = goal;
		this.operations = operations;
		this.size = operations.size();
		this.math = math;
	}
	
	
	
	//TODO write the calculations of the error from the the operations and start
	public void calcError(){
		float result = this.start;
		for (int i = 0; i < this.size; i++){
			result = this.math.Op(this.operations.get(i), result);
		}
		this.error = Math.abs(this.goal - result);
	}
	
	//TODO write up the calculation for this function using the error and size 
	public void calcFitnessFunction() {
		this.fitnessFunction =  this.error*-1;
	}
	
	
	// getters
	
	public ArrayList<Integer> getOperations() {
		return operations;
	}

	public float getFitnessFunction() {
		return fitnessFunction;
	}
	
	public float getError() {
		return error;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public Organism mutate(){ // mutate this organism
		int change = (int)(Math.random()*4); // randomly choose what changed
		int what2Change = (int)(Math.random()*size); // randomly choose which operation changes
		ArrayList<Integer> newOps = new ArrayList<Integer>();
		for(int i = 0; i < size; i++){
			newOps.add(operations.get(i));
		}
		if(newOps.size() == 1 && change == 2){
			change = 0;
		}
		switch(change){
			case 0: // Nothing changes
				break;
			case 1: // Add operator
				newOps.add(what2Change,((int)(Math.random()*math.Size())));
				break;
			case 2: // Remove operator
				newOps.remove(what2Change);
				break;
			case 3: // Change operator
				newOps.set(what2Change, ((int)(Math.random()*math.Size())));
				break;
		}
		Organism newOrg = new Organism(start, goal, newOps, math); // Make and return new organism
		newOrg.calcError();
		newOrg.calcFitnessFunction();
		return newOrg;
	}
	
	public Organism parent(Organism parent){ // create new organism based on this and one other
		ArrayList<Integer> tempOps1 = new ArrayList<Integer>();
		for(int i = 0; i < size; i++){
			tempOps1.add(operations.get(i));
		}
		ArrayList<Integer> tempOps2 = new ArrayList<Integer>();
		for(int i = 0; i < parent.getSize(); i++){
			tempOps2.add(parent.getOperations().get(i));
		}
		ArrayList<Integer> newOps = new ArrayList<Integer>();
		for(int i = 0; i < tempOps1.size()/2; i++){ // Add first half of first parent to offspring
			newOps.add(tempOps1.get(i));
		}
		for(int i = tempOps2.size()/2; i < tempOps2.size(); i++){ // Add second half of second parent to offspring
			newOps.add(tempOps2.get(i));
		}
		Organism newOrg = new Organism(start, goal, newOps, math); // Make and return new organism
		newOrg.calcError();
		newOrg.calcFitnessFunction();
		return newOrg;
	}
	
	
	
}
