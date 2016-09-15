package AIPack1;

import java.util.ArrayList;
import java.lang.Math;

public class Generation {
	
	
	private ArrayList<Organism> gen;
	private int genSize = 100;
	
	public Generation(ArrayList<Organism> gen){
		this.gen = gen;
	}
	

	public Generation newGen(){ // return a new generation
		ArrayList<Organism> newGenList = new ArrayList<Organism>();
		int length = gen.size();
		for(int i = 0; i < length; i++){ // add mutations to new gen
			newGenList.add(gen.get(i).mutate());
		}
		for(int i = 0; i < length/2; i++){ // add offspring to new gen
			newGenList.add(gen.get(i).parent(gen.get(length-i)));
			newGenList.add(gen.get(length-i).parent(gen.get(i)));
		}
		while(newGenList.size() > genSize){ // Reduce population to size
			newGenList.remove(newGenList.size()-1);
		}
		int i = 0;
		while(newGenList.size() < genSize){ // Increase population to size
			newGenList.add(gen.get(i).parent(gen.get(i+1))); // Add offspring of different pairings
			i = i + 2;
		}
		Generation newGen = new Generation(newGenList); // create new generation
		return newGen;
}



	public void genInitPopulation(AIMath math, float start, float goal, int limit){
		System.out.println("Generated intial population");
		int length = 0;
		
		for(int i=0; i<genSize; i++){	// generate genSize(100) organisms for initial population
			ArrayList<Integer> operations = new ArrayList<Integer>();
			length = (int)(Math.random()*limit+1); // generate random length L of the organism
			for(int k=0; k <length; k++){
				operations.add((int)(Math.random()*math.Size())); // generate L random operations
			}
			Organism org = new Organism(start, goal, operations, math);
			org.calcError();
			org.calcFitnessFunction();
			gen.add(org); // add the randomly generated organism to population
		}
	}
	
	// for TESTING PURPOSES
	public String printPopulation(int size){
		ArrayList<Integer> operations;
//		int OP;
		String population = new String();
		population+="**** POPULATION OF " + genSize + " NODES ****\n";
		for(int i=0; i<size; i++){
			operations = gen.get(i).getOperations();
			population+="Org#" + i + " : ";
			for(int k=0; k< operations.size(); k++){
				population+=operations.get(k) + "|";
//				OP = operations.get(k).getFunc();
//				switch(OP){
//				case 0:
//					population+="+";
//					break;
//				case 1:
//					population+="-";
//					break;
//				case 2:
//					population+="*";
//					break;
//				case 3: 
//					population+="/";
//					break;
//				case 4:
//					population+="^";
//					break;
//				default:
//					population+="error";
//					break;
//					
//				}
//				population+=operations.get(k).getNum();
//				population+="|";
			}
			population+="\n";
		}
			
		return population;
		}
		
		
		public ArrayList<Organism> getPopulation(){
			return this.gen;
		}
		
		public void setPopulation(ArrayList<Organism> gen){
			this.gen = gen;
		}
}	
