package AIPack1;

import java.util.ArrayList;
import java.lang.Math;
/**
 * 
 * 
 *
 */
public class Generation {
	
	
	private ArrayList<Organism> gen;
	public int genSize = 100;//population max
	
	public Generation(ArrayList<Organism> gen){
		this.gen = gen;
	}
	
	
	// this function will create the initial population by creating random organisms and adding them to the population 
	public void genInitPopulation(AIMath math, float start, float goal, int limit){
		System.out.println("Generated intial population");
		int length = 0;
		
		for(int i=0; i< genSize; i++){	// generate genSize(100) organisms for initial population
			ArrayList<Integer> operations = new ArrayList<Integer>();
			length = (int)(Math.random()*limit+1); // generate random length L of the organism
			for(int k=0; k <length; k++){
				operations.add((int)(Math.random()*math.Size())); // generate L random operations
			}
			Organism org = new Organism(start, goal, operations, math);
			org.calcError();
			org.calcFitnessFunction();
			System.out.println(org.getFitnessFunction());
			gen.add(org); // add the randomly generated organism to population
		}
	}
	

	/*this function will create the newer generation, i.e. not the initial one
	 *this is done by adding the organisms from the previous generation and running mutate on them,
	 *and then run cross breed and create new organisms from that until reaching the count of the max population
	 */
	public Generation betterGen(){
		ArrayList<Organism> newGenList = new ArrayList<Organism>();
		newGenList.add(this.gen.get(0));//add the best organism without mutation
		for(int i =0; i< this.gen.size(); i++){
			newGenList.add(this.gen.get(i).mutate());//add the the previous population and possibly mutate them
		}

		while(newGenList.size() < genSize){//do this until reaching max population
			int rand1 = (int) Math.floor(Math.random()*(this.gen.size()));
			int rand2 = (int) Math.floor(Math.random()*(this.gen.size()));
			//select two random organisms and cross breed them, then add them to this generation
			newGenList.add(this.gen.get(rand1).parent(this.gen.get(rand2)));
			newGenList.add(this.gen.get(rand2).parent(this.gen.get(rand1)));
		}
		
		Generation newGen = new Generation(newGenList); // create new generation
		return newGen;

	}

	
	//for testing, this function will add and mutate the previous population to a new  generation 
	//and add randomly mutated organisms until reaching the desired population size
	public Generation mutateGen(){
		ArrayList<Organism> newGenList = new ArrayList<Organism>();
		for(int i =0; i< this.gen.size(); i++){
			newGenList.add(this.gen.get(i).mutate());
		}
		while(newGenList.size() < genSize){
			int rand = (int) Math.floor(Math.random()*(this.gen.size()));
			//System.out.println("RANDOM: " + rand);
			newGenList.add(this.gen.get(rand).mutate());
		}
		//System.out.println("NEW LIST: " + newGenList.size());
		Generation newGen = new Generation(newGenList); // create new generation
		return newGen;
		
	}
	
	
	
	//initial attempt at creating new generations 
	public Generation newGen(){ // return a new generation
		ArrayList<Organism> newGenList = new ArrayList<Organism>();
		int length = gen.size();
		newGenList.add(gen.get(length-1));
		for(int i = 0; i < length; i++){ // add mutations to new gen
			newGenList.add(gen.get(i).mutate());
		}
		for(int i = 0; i < length/2; i++){ // add offspring to new gen
			newGenList.add(gen.get(i).parent(gen.get(length-i-1)));
			newGenList.add(gen.get(length-i-1).parent(gen.get(i)));
		}
		while(newGenList.size() > genSize){ // Reduce population to size
			newGenList.remove(newGenList.size()-1);
		}
		int i = 0;
		while(newGenList.size() < genSize){ // Increase population to size
			newGenList.add(gen.get(i).parent(gen.get(i+1))); // Add offspring of different pairings
			if(i != gen.size() -1) i++;
			else i = 0;
		}
		Generation newGen = new Generation(newGenList); // create new generation
		return newGen;
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
			population+=" With fit " + gen.get(i).getFitnessFunction();
			population+="\n";
		}
			
		return population;
		}
		
	
		//getters and setters
		public ArrayList<Organism> getPopulation(){
			return this.gen;
		}
		
		public void setPopulation(ArrayList<Organism> gen){
			this.gen = gen;
		}
		
		//for testing purposes
		public ArrayList<Organism> mutatePopulation(){
			ArrayList<Organism> newGen = new ArrayList<Organism>();
			for(int k=0; k< this.gen.size(); k++){
				newGen.add(gen.get(k).mutate());
			}
			return newGen;
		}
}	
