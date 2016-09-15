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
		this.fitnessFunction = this.error + this.size;
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

	public Organism mutate(){ // mutate this organism
		return new Organism(0,0,null, math);
	}
	
	public Organism parent(Organism parent){ // create new organism based on this and one other
		return new Organism(0,0,null, math);
	}
	
	
	
}
