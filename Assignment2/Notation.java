public class Notation {

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException, StackOverflowException, QueueOverflowException, StackUnderflowException {
		MyStack<String> coolStack = new MyStack<String>(infix.length());
		MyQueue<String> solution = new MyQueue<String>(infix.length());
		
		
		for(int i = 0; i<infix.length();i++) {
			String sub = infix.substring(i,i+1);
			
			if(sub.equals("(")) {
				coolStack.push("(");
			}
			
			else if(sub.equals(")")) {
				
			}
			
			else if(sub.equals("+") || sub.equals("*") || sub.equals("-") || sub.equals("/")) {
				String top = coolStack.top();
				if(top.equals("+") || top.equals("*") || top.equals("-") || top.equals("/")) {
					coolStack.pop();
				}
			}
		
			
			for(int x=0; x<10; x++) {
				if(sub.equals(String.valueOf(x))) {
					solution.enqueue(sub);
				}
			}//inner for loop ends
		}//outer for loop ends
		return null;
	}//method ends

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		return null;
	}

	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		return 0;
	}

	
}