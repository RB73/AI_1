package AIPack1;
import java.util.*;
import AIPack1.*;
public class GreedySearch {
	
    AIMath AImath;
    int currentVal;
    int goal;
    int branchFactor;
    ArrayList<Integer> operationSequence;
    Result result;
    long startTime;
    long timeLimit;
	
    /**
     * Constructor
     */
    public GreedySearch(AIMath math, int start, int goal, long timeLimit, long startTime){
        this.AImath = math;
        this.currentVal = start;
        this.goal = goal;
        this.startTime = startTime;
        this.timeLimit = timeLimit;
        operationSequence = new ArrayList<Integer>();
        branchFactor = math.operations.size();  
        result = new Result(currentVal, 0, 0, operationSequence, 0);

    }
    
    
	public Result runSearch(){
		int size = branchFactor;
		int[] nodes = new int[size];
//		System.out.println("Current val is: " + currentVal);
		
		// Initialize array
		for(int i=0; i<size; i++)
			nodes[i] = currentVal;
		
		int best = 0;
		long currentTime = System.currentTimeMillis();
		result.setSearchTime((int)(currentTime - startTime));
		
		// IF GOAL REACHED or NO TIME
		while(nodes[best]!= goal && currentTime - startTime < timeLimit){
//			System.out.println("Current val is: " + currentVal);
//			System.out.println("Best node is: " + best);

			
			// EXPAND NODE
			for(int i=0; i<size; i++){
				nodes[i] = AImath.Op(i, currentVal);
//				System.out.println("The node " + i + " has a value of: " + nodes[i]);
			}
			
			// FIND THE BEST NODE TO EXPAND
			best = findBestNode(nodes, size);
			
			// UPDATE DATA
			currentVal = nodes[best];
			operationSequence.add(best);
			result.setOperations(operationSequence);
			result.setDepth(result.getDepth()+1);
			result.setNodesExpanded(result.getNodesExpanded()+1);
			result.setBest(currentVal);
			// UPDATE TIME
			currentTime = System.currentTimeMillis();
			result.setSearchTime((int)(currentTime - startTime));
//			System.out.println("Current search time is: " + ((long)(currentTime - startTime)));
			
			
			}
		// IF SEARCH CONTINUES
			return result;
	}
	
	
	public int findBestNode(int[] nodes, int size){
		// array to gold the nodes' distances from goal
		int[] distance = new int[size];
		
		// initialize distances from goal
		for(int i=0; i<size; i++){
			// find the distance from the goal
			distance[i] = goal - nodes[i];
			// take the absolute value
			if(distance[i]<0) distance[i]*=-1;
		}
		
		// find the best node to expand
		int bestIndex = 0, minDistance = 99999;
		// find the minimum distance from the goal
		for(int i=0; i<size; i++){
			if(distance[i]< minDistance){
				minDistance = distance[i];
				bestIndex = i;
			}
		}
		// return the index of the node to expand
		return bestIndex;
	}
	
}
