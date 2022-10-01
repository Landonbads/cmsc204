/**
* Notation program that converts an infix expression to a postfix expression, 
* a postfix expression to an infix expression, and evaluates a postfix expression
* through generic linked list implementation of stack and queue data structures
* @author Landon Badstibner
*/

public class Notation {
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix - the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException, StackOverflowException, QueueOverflowException, StackUnderflowException {
		MyStack<String> coolStack = new MyStack<String>(infix.length());
		MyQueue<String> solutionQueue = new MyQueue<String>(infix.length());
		//loop through all elements of infix parameter
		for(int i = 0; i<infix.length();i++) {
			String element = infix.substring(i,i+1);
			// check if element is digit
			for(int z = 0; z < 10;z++) {
				if(element.equals("" + z)) {
					solutionQueue.enqueue(element);
				}
			}
			//check if element is left parenthesis
			if(element.equals("(")) {
				coolStack.push(element);
			}
			//check if element is operator
			if(element.equals("/") || element.equals("*")|| element.equals("-")|| element.equals("+")) {
				if(!coolStack.isEmpty()) { 
					if(coolStack.top().equals("/") || coolStack.top().equals("*")) {
						solutionQueue.enqueue(coolStack.top());
						coolStack.pop();
						coolStack.push(element);
					}
					else if(coolStack.top().equals("+") || coolStack.top().equals("-")) {
						if(!element.equals("/") && !element.equals("*")) {
							solutionQueue.enqueue(coolStack.top());
							coolStack.pop();
							coolStack.push(element);
						}
						else {
							coolStack.push(element);
						}
					}
					else if(coolStack.top().equals("(")) {
						coolStack.push(element);
					}
				}
				else {
					coolStack.push(element);
				}
			}
			//check if element is right parenthesis
				if(element.equals(")")) {
					try {
					while(coolStack.top().equals("/") || coolStack.top().equals("*") || coolStack.top().equals("+") || coolStack.top().equals("-")) {
						solutionQueue.enqueue(coolStack.pop());
					}
						if(coolStack.top().equals("(")) {
							coolStack.pop();
						}
					}
					catch(StackUnderflowException e) {
						throw new InvalidNotationFormatException();
					}
			}			
		}
		// pop remaining operators and add to solution queue
		while(!coolStack.isEmpty()) {
			if(coolStack.top().equals("/") || coolStack.top().equals("*") || coolStack.top().equals("+") || coolStack.top().equals("-"))
				solutionQueue.enqueue(coolStack.pop());
		}
		
		//return solutionQueue as string
		return solutionQueue.toString();
	} //convertInfixToPostfix method ends
	
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix - the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		MyStack<String> coolStack = new MyStack<String>(postfix.length());
		// loop through all elements of infix parameter
		for(int i = 0; i<postfix.length();i++) {
			String element = postfix.substring(i,i+1);
			//check if digit
			for(int z = 0; z < 10;z++) {
				if(element.equals("" + z)) {
					try {
						coolStack.push(element);
					} catch (StackOverflowException e) {
						e.getMessage();
					}
				}
			} //digit loop ends
			
			// check if operator
			if(element.equals("/") || element.equals("*")|| element.equals("-")|| element.equals("+")) {
				try {
					String second = coolStack.pop();
					String first = coolStack.pop();
					String expression = ("(" + first + element + second + ")");
					coolStack.push(expression);
				}
				catch(Exception e){
					throw new InvalidNotationFormatException();
				}
				}
			}//outer for loop ends
		
		
			if(coolStack.size()==1) {
				return coolStack.toString();
			}
			else {
				throw new InvalidNotationFormatException();
			}
		} //convertPostfixToInfix method ends
		
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfixExpr - the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException, StackOverflowException{
		MyStack<String> coolStack = new MyStack<String>(postfixExpr.length());
		double expression = 0.0;
		// loop through all elements of infix parameter
		for(int i = 0; i<postfixExpr.length();i++) {
			String element = postfixExpr.substring(i,i+1);
			//for loop to check if element is a digit
			for(int z = 0; z < 10;z++) { 
				if(element.equals("" + z)) {
					try {
						coolStack.push(element);
					} catch (StackOverflowException e) {
						e.getMessage();
					}
				}
			} //digit loop ends
			
			//if element is operator, perform arithmetic calculation
			if(element.equals("/") || element.equals("*")|| element.equals("-")|| element.equals("+")) {
				try {
					double second = Double.parseDouble(coolStack.pop());
					double first = Double.parseDouble(coolStack.pop());
					switch (element) {
	                 case "*": expression = (first * second); break;
	                 case "/": expression = (first / second); break;
	                 case "+":  expression  = (first + second); break;
	                 case "-":  expression  =(first - second); break;
	            }
					coolStack.push(String.valueOf(expression));
				}
				catch(Exception e){
					throw new InvalidNotationFormatException();
				}
			}
		}
		return expression;
	} //evaluatePostfixExpression method ends
} //class ends

