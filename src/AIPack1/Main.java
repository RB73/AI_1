package AIPack1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import AIPack1.*;


/*
 * To run the program from command line and read inputs from a text and have the output be a text file do the following:
 * go to the src directory
 * create a text file that have some input, say you called it input.txt
 * compile the program by writing the following in the command line:
 * 			javac -d . *.java
 * then run the program by writing the following in the command line:
 * 			java AIPack1.Main < input.txt
 * open the output.txt file and it will contain the result of running the program
 */

public class Main {
	public int operationLimit = 30;//the max number of operation creating and recreating the organisms 
	
	static ArrayList<String> inputList = new ArrayList<String>();
    public static String searchType;
    public static int startNum;
    public static int targetNum;
    public static long timeLimit;
    public static ArrayList<String> inputOperations = new ArrayList<String>();
    public static ArrayList<Function> funs = new ArrayList<Function>();
    public static long startTime; //in milliseconds
  

    
	public static void main(String[] args) throws FileNotFoundException {

		//read in the input from a text file given through command line, and add the inputs to an array list
	    Scanner scn = new Scanner(System.in);
	    while (scn.hasNext()){
	        inputList.add(scn.next());
	    }
	    scn.close();

	    
	    //set the globals with the values from the arguments given in the input list
	    searchType = inputList.get(0);
	    startNum = Integer.parseInt(inputList.get(1));
	    targetNum = Integer.parseInt(inputList.get(2));
	    timeLimit = (long)(Double.parseDouble(inputList.get(3))*1000); //in milliseconds
	    for(int i = 4; i < inputList.size(); i++){
	    	inputOperations.add(inputList.get(i));
	    }
	    //convert the list of operations to list of Functions
	    funs = operationsToFunctions(inputOperations);
	    
	    
		System.out.println("STARTING");
		startTime = System.currentTimeMillis();//initialize the timer when the program starts
		
	    // Create AIMath object, add operations list to it
	    AIMath Math = new AIMath();
	    Math.AddOps(funs);
	    Result result;
	    
	    if(searchType.equals("iterative")){ 	
	    	IterativeDeepening itr= new IterativeDeepening(Math, startNum, targetNum, timeLimit, startTime);
	    	result = itr.runSearch();
		    printOutput(result, Math, targetNum);

	    }
	    else if(searchType.equals("greedy")){
	    	GreedySearch grd = new GreedySearch(Math, startNum, targetNum, timeLimit, startTime);
	    	result = grd.runSearch();
		    printOutput(result, Math, targetNum);
	    }
	    
	    System.out.println("DONE");
	   

		
	}
	
	

	/*
	 * A Function that takes a list of operations as strings and translates them to list of Functions
	 */
	public static ArrayList<Function> operationsToFunctions(ArrayList<String> ops){
		//initialize variables
		ArrayList<Function> funcs = new ArrayList<Function>();
		char funcChar;
		int funcNum = 0;
		int inputNum = 0; 
		//loops through the list of operations
		for(int i = 0; i<ops.size(); i++){
			funcChar = ops.get(i).charAt(0);//gets the first character of the operation which is the operator character, i.e (+,-,*...)
			inputNum = Integer.parseInt(ops.get(i).substring(1)); //gets the number that follows the operator and stores it into an integer
			
			//find the Function number corresponding to each operator
			// 0 = add, 1 = sub, 2 = mul, 3 = div, 4 = exp
			switch(funcChar){	
			case '+':
				funcNum = 0;
				break;
			case '-':
				funcNum = 1;
				break;
			case '*':
				funcNum = 2;
				break;
			case '/':
				funcNum = 3;
				break;
			case '^':
				funcNum = 4;
				break;
			default:
				throw new RuntimeException("Unknown function " + funcChar);
			}
		//create a Function
		Function fun = new Function (funcNum, inputNum);
		//add it to the list of Functions
		funcs.add(fun);
		}
		//return the list of Functions when done
		return funcs;
	}
	
	
	// A function to print the final results, 
	public static void printOutput(Result res, AIMath math, int target){
		
		float current = startNum;
		float next; 
		String currentStr;
		String toPrint;
		//Print the operations
		for(int i = 0; i < res.getOperations().size(); i++){
			currentStr = Float.toString(current) + getOpFromIndex(res.getOperations().get(i));
			next = math.Op((int) res.getOperations().get(i),current);
			toPrint = currentStr + "=" + Float.toString(next);
			System.out.println(toPrint);
			current = next;
		}
		float error = Math.abs(current - target);
		//System.out.println(res.getBest());
		System.out.println("");
		System.out.println("Error = " + error);
		System.out.println("Number of steps required: " + res.getOperations().size());//TODO find what the steps required entitles
		double doneTime = (double)res.getSearchTime()/1000.0;
		System.out.println("Search required: " + doneTime + " seconds");
		System.out.println("Nodes expanded: " + res.getNodesExpanded());
		System.out.println("Maximum search depth: " + res.getDepth());
		
		
	}

	// Returns input operations as a string at the given index
	public static String getOpFromIndex(int index){
		return inputOperations.get(index);
	}
	
	

}