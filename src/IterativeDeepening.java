
import java.util.ArrayList;

public class IterativeDeepening {


    AIMath math;
    int start;
    int goal;
    int branchingFactor;
    int nodesExpanded;
    long timeLimit;
    long startTime;
    

    ArrayList<Integer> goalOperations;

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
     * @return	meta-data about the search 
     */
    public Result runSearch(){
        boolean done = false;
        int depth = 0;
        nodesExpanded = 0;

        while(!done){	// runs the search infinitely, with increasing depth
            if(searchBranch(depth, start) == goal){
            	return new Result(depth, nodesExpanded, goalOperations, System.currentTimeMillis() - startTime);
            }
                
            depth ++;
        }
        
        return null; // shouldn't happen due to timeouts
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
        if(currentTime - startTime > timeLimit)
        	return -2;

        if(depth == 0 && node == goal)	// success case
            return node;

        // recursive loop until leaf end or solution is found
        if(depth > 0)
            for(int i = 0; i < branchingFactor; i++){
                result = searchBranch(depth - 1, math.Op(i, node));	// recursion here
                if(result == goal){
                    goalOperations.add(depth - 1, i);   // adds current operation to final sequence
                    return result;
                }
                if(result == -2)
                	return -2;
            }
        
        return -1; // didn't find goal
    }

}



