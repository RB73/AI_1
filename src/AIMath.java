import java.util.LinkedList;

public class AIMath {
	LinkedList<function> operations = new LinkedList<function>(); // All allowed operations for the search
	
	public int Add(int input, int num){ // Math functions, all return int
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
		operations.add(new function(operation, num));
	}
	public function returnOp(int num){ // Returns operation at num
		return operations.get(num);
	}
	public int Op(int num, int input){ // Returns value of operation at num
		function op = operations.get(num); // Get operation at num
		int func = op.getFunc(); // get function
		int opNum = op.getNum(); // get num for operation
		
		switch(func){ // return appropriate math function
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
		return -999999999;	// No operation at num
	}
}

class function {
	private int func; // 0 = add, 1 = sub, 2 = mul, 3 = div, 4 = exp
	private int num; // number for functions
	
	public function(int input_func, int input_num){
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