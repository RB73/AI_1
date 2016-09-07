import java.util.ArrayList;

public class Result {

    int nodesExpanded;
    int depth;
    ArrayList<Integer> operations;	// sequence of indexes for operations leading to the goal value
    long searchTime;

    public Result(int nodesExpanded, int depth, ArrayList<Integer> operations, long searchTime){
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

    public void setSearchTime(int searchTime) {
        this.searchTime = searchTime;
    }
    
}
