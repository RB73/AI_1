
import java.util.ArrayList;
import java.util.Collections;

public class IterativeDeepening {


    private AIMath math;
    private int start;
    private int goal;
    private int branchingFactor;
    private int nodesExpanded;
    private long timeLimit;
    private long startTime;

    private static int NOT_FOUND = -1;
    private static int TIMEOUT = -2;


    private ArrayList<Integer> goalOperations;

    /**
     * Constructor
     */
    public IterativeDeepening(AIMath math, int start, int goal, long timeLimit, long startTime){
        this.math = math;
        this.start = start;
        this.goal = goal;
        goalOperations = new ArrayList<Integer>();
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
            int result = searchBranch(depth, start);
            if(result == goal){
                Collections.reverse(goalOperations);
            	return new Result(nodesExpanded, depth, goalOperations, System.currentTimeMillis() - startTime);
            }
            if(result == TIMEOUT)
                done = true;
            else depth ++;
        }
        
        return null; // timeout
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
                result = searchBranch(depth - 1, math.Op(i, node));	// recursion here

                if(result == goal){
                    goalOperations.add(i);
                    return result;
                }
                if(result == TIMEOUT)
                	return TIMEOUT;
            }
        
        return NOT_FOUND; // didn't find goal
    }

}



