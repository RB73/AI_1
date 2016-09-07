import java.util.ArrayList;

public class Result {

    int nodesExpanded;
    int depth;
    ArrayList<Integer> operations;	// final sequence of operations leading to the goal value

    public Result(int nodesExpanded, int depth, ArrayList<Integer> operations){
        this.nodesExpanded = nodesExpanded;
        this.depth = depth;
        this.operations = operations;
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
}
