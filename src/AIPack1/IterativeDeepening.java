package AIPack1;

import java.util.ArrayList;
import java.util.Collections;
import AIPack1.*;

public class IterativeDeepening {


    private AIMath math;
    private float start;
    private float goal;
    private float best;
    private int branchingFactor;
    private int nodesExpanded;
    private long timeLimit;
    private long startTime;

    private static int NOT_FOUND = Integer.MIN_VALUE;
    private static int TIMEOUT = Integer.MIN_VALUE + 1;
    private static int BEST_FOUND = Integer.MIN_VALUE + 2;


    private ArrayList<Integer> goalOperations;
    private ArrayList<Integer> currentOperations;
    private ArrayList<Integer> bestOperations;
    
    /**
     * Constructor
     */
    public IterativeDeepening(AIMath math, float start, float goal, long timeLimit, long startTime){
        this.math = math;
        this.start = start;
        this.goal = goal;
        goalOperations = new ArrayList<Integer>();
        currentOperations = new ArrayList<Integer>();

        branchingFactor = math.Size();
        this.timeLimit = timeLimit;
        this.startTime = startTime;
    }

    /**
     * Runs search once 
     * @return	meta-data about the search OR null if timeout
     */
    public Result runSearch(){
        boolean done = false;
        int depth = 0;
        nodesExpanded = 0;
        while(!done){	// runs the search infinitely, with increasing depth
            float result = searchBranch(depth, start);
            if(result == goal){     // found solution, end loop
                Collections.reverse(goalOperations);
            	return new Result(goal, nodesExpanded, depth, goalOperations, System.currentTimeMillis() - startTime);
            }
            if(result == TIMEOUT)  // couldn't find solution in time
                done = true;
            else depth ++;
        }
        
        Collections.reverse(bestOperations);
        return new Result(best, nodesExpanded, depth, bestOperations, timeLimit); // timeout
    }

    //referenced pseudo code on wikipedia https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
    /**
     * Runs the search recursively 
     * @param depth	current depth relative to node
     * @param node	current node
     * @return		value of goal if goal is reached; -1 otherwise; -2 if time limit
     */
    public float searchBranch(int depth, float node){
    	nodesExpanded++;
        float result;

        long currentTime = System.currentTimeMillis();

        if(currentTime - startTime > timeLimit)         // timeout case
        	return TIMEOUT;
        if(depth == 0 && node == goal)	                // success case
            return node;

        // recursive loop until leaf end or solution is found
        if(depth > 0)
            for(int i = 0; i < branchingFactor; i++){
            	
            	if(currentOperations.size() - (depth) >=0){ // update array with current operations for each path
            		currentOperations.remove(depth-1);
            	}
                currentOperations.add(depth - 1, i);
            	
                result = searchBranch(depth - 1, math.Op(i, node));	// recursion here
               
                if(result == goal){
                	if(goalOperations.size() - (depth) >=0){
                		goalOperations.remove(depth-1);
                	}
                    goalOperations.add(depth - 1, i);
                    return result;
                }

                if(result == TIMEOUT)
                    return TIMEOUT;
            }

        // check if current leaf is the current best solution
        double bestError = Math.abs(best - goal);
        double currentError = Math.abs(node - goal);
        if(bestError > currentError && currentError > Integer.MIN_VALUE){
            best = node;
            bestOperations = new ArrayList<Integer>();
            for(int i = 0; i < currentOperations.size(); i++){
            	bestOperations.add(currentOperations.get(i));
            }
            return BEST_FOUND;
        }


        return NOT_FOUND; // didn't find goal
    }

}



