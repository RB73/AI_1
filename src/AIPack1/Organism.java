package AIPack1;

import java.util.ArrayList;

public class Organism {
	private float start;
	private float goal;
	private ArrayList<Function> operations;
	private float fitnessFunction;
	private float error;
	private int size; //number of operations the organism has

	public Organism (float start, float goal, ArrayList<Function> operations){
		this.start = start;
		this.goal = goal;
		this.operations = operations;
		this.size = operations.size();
	}
	
	
	
	//TODO write the calculations of the error from the the operations and start
	public float calcError(){
		return 0;
	}
	
	//TODO write up the calculation for this function using the error and size 
	public float calcFitnessFunction() {
		return 0;
	}
	
	
	// getters
	
	public ArrayList<Function> getOperations() {
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

	public Organism mutate(){ // mutate this organism
		return new Organism(0,0,null);
	}
	
	public Organism parent(Organism parent){ // create new organism based on this and one other
		return new Organism(0,0,null);
	}
	
	
	
}
