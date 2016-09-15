package AIPack1;

import java.util.ArrayList;

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
		}
		for(int i = 0; i < length; i = i + 2){ // add mutated offspring to new gen
			newGenList.add(gen.get(i).parent(gen.get(i+1)));
		}
		Generation newGen = new Generation(newGenList); // creatre new generation
		return newGen;
	}
	

}
