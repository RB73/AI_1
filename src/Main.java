import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To run the program from command line and read inputs from a text and have the output be a text file do the following:
 * go the directory where the java file is located
 * create a text file that have some input , say you called it input.txt
 * compile the program by writing the following in the command line:
 * 			javac Main.java
 * then run the program by writiing the following in the command line:
 * 			java Main <input.txt >output.txt
 * open the output.txt file and it will contain the result of running the program
 */

public class Main {
    
	static ArrayList<String> inputList = new ArrayList<String>();
    public static String searchType;
    public static int startNum;
    public static int targetNum;
    public static long timeLimit;
    public static ArrayList<String> operations = new ArrayList<String>();
    public static long startTime; //in milliseconds
  

    
	public static void main(String[] args) throws FileNotFoundException {
		
		startTime = System.currentTimeMillis();//initialize the timer when the program starts
		
		/*uncomment this to read from file, and comment out the sample list
		
		//read in the input from a text file given through command line, and add the inputs to an array list
	    Scanner scn = new Scanner(System.in);
	    while (scn.hasNext()){
	        inputList.add(scn.next());
	    }
	    scn.close();
	    
	    */
		
	    // sample list of inputs, change it for different testing  purposes. 
		inputList.add(0, "iterative");//search type
		inputList.add(1, "2"); 		  //initial number
		inputList.add(2, "9");		  //target number
		inputList.add(3, "3.5");      //time limit in seconds
		inputList.add(4, "+4");       //list of operations
		inputList.add(5, "-2");       //.
		inputList.add(6, "*3");       //.
		inputList.add(7, "^3"); 	  //.
		
		
	    //set the globals with the values from the arguments given in the input list
	    searchType = inputList.get(0);
	    startNum = Integer.parseInt(inputList.get(1));
	    targetNum = Integer.parseInt(inputList.get(2));
	    timeLimit = (long)(Double.parseDouble(inputList.get(3))*1000); //in milliseconds
	    for(int i = 4; i < inputList.size(); i++){
	    	operations.add(inputList.get(i));
	    }
	    
	    //Choose which search to run and run it
	    /*
	     * while((System.currentTimeMillis() - startTime) < timeLimit || result found){
	     * search
	     * }
	     * return result
	     * 
	     */
	    
	    //print the given inputs 
	    for (int i =0; i < inputList.size(); i++){
	    	System.out.println(inputList.get(i));
	    }
	      
	}

}
