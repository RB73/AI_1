package AIPack1;
import java.util.ArrayList;
import AIPack1.*;
public class Result {

    private int best;
    private int nodesExpanded;
    private int depth;
    private ArrayList<Integer> operations;	// sequence of indexes for operations leading to the goal value
    private long searchTime;

    public Result(int best, int nodesExpanded, int depth, ArrayList<Integer> operations, long searchTime){
        this.best = best;
        this.nodesExpanded = nodesExpanded;
        this.depth = depth;
        this.operations = operations;
        this.searchTime = searchTime;
    }
    
    
    // getters and setters here

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public void setNodesExpanded(int nodesExpanded) {
        this.nodesExpanded = nodesExpanded;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public ArrayList<Integer> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Integer> operations) {
        this.operations = operations;
    }
    
    public long getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(long searchTime) {
        this.searchTime = searchTime;
    }

    public int getBest() {
        return best;
    }

    public void setBest(int best) {
        this.best = best;
    }
}
