package AIPack1;

import java.util.ArrayList;
import java.util.Collections;
import AIPack1.*;

public class IterativeDeepening {


    private AIMath math;
    private int start;
    private int goal;
    private int best;
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
    public IterativeDeepening(AIMath math, int start, int goal, long timeLimit, long startTime){
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
        System.out.println("Branches: " + branchingFactor);
        while(!done){	// runs the search infinitely, with increasing depth
            int result = searchBranch(depth, start);
            if(result == goal){
                Collections.reverse(goalOperations);

            	return new Result(goal, nodesExpanded, depth, goalOperations, System.currentTimeMillis() - startTime);
            }
            if(result == TIMEOUT){
                done = true;
            }

            else depth ++;
        }
        
        Collections.reverse(bestOperations);
        //System.out.println("Size of Best OP: "   );
        return new Result(best, nodesExpanded, depth, bestOperations, timeLimit); // timeout
    }

    //referenced pseudo code on wikipedia https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
    /**
     * Runs the search recursively 
     * @param depth	current depth relative to node
     * @param node	current node
     * @return		value of goal if goal is reached; -1 otherwise; -2 if time limit
     */
    public int searchBranch(int depth, int node){
    	nodesExpanded++;
        int result;

        long currentTime = System.currentTimeMillis();

        if(currentTime - startTime > timeLimit)         // timeout case
        	return TIMEOUT;
        if(depth == 0 && node == goal)	                // success case
            return node;

        // recursive loop until leaf end or solution is found
        if(depth > 0)
            for(int i = 0; i < branchingFactor; i++){
            	//System.out.println("Current Branch = " + i);
            	//System.out.println("Current BranchingFactor = " + branchingFactor);
            	
            	if(currentOperations.size() - (depth) >=0){
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
        
        double bestError = Math.abs(best - goal);
        double currentError = Math.abs(node - goal);
        if(bestError > currentError && currentError > Integer.MIN_VALUE){
            best = node;
            System.out.println("BEST: " + best);
            bestOperations = new ArrayList<Integer>();
            for(int i =0; i<currentOperations.size(); i++){
            	bestOperations.add(currentOperations.get(i));
            }
            return BEST_FOUND;
        }


        return NOT_FOUND; // didn't find goal
    }

}



