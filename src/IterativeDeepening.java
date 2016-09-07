
import java.util.ArrayList;

public class IterativeDeepening {


    AIMath math;
    int start;
    int goal;
    int branchingFactor;
    int nodesExpanded;

    ArrayList<Integer> goalOperations;	//final result

    /**
     * Constructor
     */
    public IterativeDeepening(AIMath math, int start, int goal){
        this.math = math;
        this.start = start;
        this.goal = goal;
        goalOperations = new ArrayList<Integer>();
        branchingFactor = math.operations.size();  	//TODO: change this
        //branchingFactor = math.Size();
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
            if(searchBranch(depth, start) == goal)
                return new Result(depth, nodesExpanded, goalOperations);
            depth ++;
        }
        
        return null; // shouldn't happen due to timeouts
    }

    //referenced pseudo code on wikipedia https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
    /**
     * Runs the search recursively 
     * @param depth	current depth relative to node
     * @param node	current node
     * @return		value of goal if goal is reached; -1 otherwise
     */
    public int searchBranch(int depth, int node){ 
    	// TODO: check the time here
    	nodesExpanded++;
    	
    	
        int result;

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
            }
        
        return -1; // didn't find goal
    }

}



