import java.util.ArrayList;

public class AIMath {
	ArrayList<Function> operations = new ArrayList<Function>(); // All allowed operations for the search
	
	public int Add(int input, int num){ // Math Functions, all return int
		return input + num;
	}
	public int Sub(int input, int num){
		return input - num;
	}
	public int Mul(int input, int num){
		return input * num;
	}
	public int Div(int input, int num){
		return input / num;
	}
	public int Exp(int input, int num){
		return (int) Math.pow(input, num);
	}
	
	public AIMath(){ // constructor
	}
	
	public void AddOp(int operation, int num){ // Adds operations to allowed operations
		operations.add(new Function(operation, num));
	}
	public void AddOps(ArrayList<Function> opList){
		operations = opList;
	}
	public Function returnOp(int num){ // Returns operation at num
		return operations.get(num);
	}
	public int Size(){ // Returns size of operations list
		return operations.size();
	}
	public int Op(int num, int input){ // Returns value of operation at num
		Function op = operations.get(num); // Get operation at num
		int func = op.getFunc(); // get Function
		int opNum = op.getNum(); // get num for operation
		
		switch(func){ // return appropriate math Function
		case 0:
			return Add(input, opNum);
		case 1:
			return Sub(input, opNum);
		case 2:
			return Mul(input, opNum);
		case 3:
			return Div(input, opNum);
		case 4:
			return Exp(input, opNum);
		}
		return -999999999;	// No operation at num or function value outside 0-4
	}
}

class Function {
	private int func; // 0 = add, 1 = sub, 2 = mul, 3 = div, 4 = exp
	private int num; // number for Functions
	
	public Function(int input_func, int input_num){
		func = input_func;
		num = input_num;
	}
	public int getNum(){
		return num;
	}
	public int getFunc(){
		return func;
	}
}