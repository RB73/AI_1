package AIPack1;

import java.util.ArrayList;
import java.lang.Math;

public class Generation {
	
	
	private ArrayList<Organism> gen;
	private int genSize = 100;
	
	public Generation(ArrayList<Organism> gen){
		this.gen = gen;
	}
	
	public void genInitPopulation(AIMath math, int start, int goal, int limit){
		System.out.println("Generated intial population");
		int length = 0;
		
		for(int i=0; i<genSize; i++){	// generate genSize(100) organisms for initial population
			ArrayList<Integer> operations = new ArrayList<Integer>();
			length = (int)(Math.random()*limit+1); // generate random length L of the organism
			for(int k=0; k <length; k++){
				operations.add((int)(Math.random()*math.Size())); // generate L random operations
			}
			gen.add(new Organism((float)start, (float)goal, operations)); // add the randomly generated organism to population
		}
	}
	
	// for TESTING PURPOSES
	public String printPopulation(){
		ArrayList<Integer> operations;
		int OP;
		String population = new String();
		population+="**** POPULATION OF " + genSize + " NODES ****\n";
		for(int i=0; i<100; i++){
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
		
		
		
		
	}
	
