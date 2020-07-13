package inversepolishcalculator.calculate;

/**
 * 中缀表达式转换为后缀表达式算法及后缀表达式的计算算法的实现
 * 
 * @author 独孤猿1998
 *
 */
public class InversePolishCalculator {
	
	/**
	 * 中缀表达式转换为后缀表达式的方法
	 * @param nifixExpression 中缀表达式
	 * @return 后缀表达式（逆波兰表达式）
	 */
	public static String nifixTransformPostfix(String nifixExpression) {
		Stack<Character> operatorStack = new Stack<Character>();//初始化存储操作符的操作符栈
		Stack<String> postfixExpressionStack = new Stack<String>();//初始化存储逆波兰表达式的逆波兰表达式栈
		int length = nifixExpression.length();//中缀表达式的字符串长度
		int index = 0;//用于指向扫描到中缀表达式的位置
		char element = ' ';//保存当前扫描到中缀表达式的字符
		String number = null;//用于存储多位数字
		while(index < length) {//从左至右扫描中缀表达式
			number = "";
			element = nifixExpression.charAt(index);
			if (element >= '0' && element <= '9') {//若读取到的字符为数字
				while(element >= '0' && element <= '9') {//则向后分析直到数字串的结束
					number = number + element;
					index++;
					if (index == length) {
						break;
					}
					element = nifixExpression.charAt(index);
				}
				postfixExpressionStack.push(number);//并将数字串压入逆波兰表达式栈
				continue;
			}else if (element == '(') {//若读取到的字符为'('
				operatorStack.push(element);//则直接将'('压入操作符栈
			}else if (element == ')') {//若读取到的字符为')'
				while (operatorStack.peek() != '(') {//则将操作符栈中的栈顶操作符依次出栈并压入逆波兰表达式栈，直到遇到左括号'('为止
					postfixExpressionStack.push(operatorStack.pop().toString());
				}
				operatorStack.pop();//将操作符栈中栈顶的左括号'('出栈
			}else if (element == '+' || element == '-' || element == '*' || element == '/') {//若读取到的字符为运算符'+', '-', '*', '/'
				if (operatorStack.isEmpty() || operatorStack.peek() == '(') {//如果操作符栈为空或栈顶元素为'('
					operatorStack.push(element);//则将读取到运算符直接压入操作符栈
				}else if (Operator.getOperatorPriority(element) > Operator.getOperatorPriority(operatorStack.peek())) {//如果读取的运算符的优先级高于操作符栈栈顶运算符的优先级
					operatorStack.push(element);//则将读取的运算符直接压入操作符栈
				}else {//如果读取的运算符的优先级低于或等于操作符栈栈顶运算符的优先级
					//则将操作符栈栈顶运算符出栈并压入逆波兰表达式栈（注意，此处优先级的比较是不断比较操作符栈栈顶运算符的优先级直到读取到的运算符的优先级高于操作符栈栈顶运算符的优先级或遇到'('或栈空）
					while(!operatorStack.isEmpty() && operatorStack.peek() != '(' && Operator.getOperatorPriority(element) <= Operator.getOperatorPriority(operatorStack.peek())) {
						postfixExpressionStack.push(operatorStack.pop().toString());
					}
					operatorStack.push(element);//将读取到的运算符压入操作符栈。
				}
			}else {
				throw new RuntimeException("扫描到未知字符！");
			}
			index++;
		}
		while(!operatorStack.isEmpty()) {//中缀表达式扫描完毕，若操作符栈中仍然存在运算符
			postfixExpressionStack.push(operatorStack.pop().toString());//则将操作符栈中栈顶的运算符依次出栈并压入逆波兰表达式栈直到操作符栈为空
		}
		//逆波兰表达式栈的出栈元素的逆序为中缀表达式转换后的后缀表达式(用空格隔开)
		String postfixExpression = postfixExpressionStack.pop();
		while(!postfixExpressionStack.isEmpty()) {
			postfixExpression = postfixExpressionStack.pop() + " " + postfixExpression;
		}
		return postfixExpression;
	}
	
	/**
	 * 计算后缀表达式结果的方法
	 * @param postfixExpression 后缀表达式（逆波兰表达式）
	 * @return 后缀表达式的计算结果
	 */
	public static int calculatePostfixExpression(String postfixExpression) {
		Stack<Integer> operandStack = new Stack<Integer>();//初始化一个操作数栈
		String[] elements = postfixExpression.split(" ");//将后缀表达式分解成一个个单元
		String element = null;//后缀表达式扫描到的单元
		char elementHead = ' ';
		int operand = 0, operand1 = 0, operand2 = 0;
		for (int i = 0; i < elements.length; i++) {//从左至右依次扫描后缀表达式的单元
			element = elements[i];
			elementHead = element.charAt(0);
			if (elementHead >= '0' && elementHead <= '9') {//如果扫描的单元是操作数串
				operand = Integer.parseInt(element);//则将其转换为数字
				operandStack.push(operand);//并压入操作数栈
			}else if(elementHead == '+' || elementHead == '-' || elementHead == '*' || elementHead == '/') {// 如果扫描的单元是一个运算符
				operand1 = operandStack.pop();//取操作数栈栈顶
				operand2 = operandStack.pop();//取操作数栈栈顶
				operand = calculateResult(operand1, operand2, elementHead);//则对操作数栈栈顶上的两个操作数执行该运算
				operandStack.push(operand);//将运算结果重新压入操作数栈
			}else {
				throw new RuntimeException("扫描到未知单元！");
			}
		}
		return operandStack.pop();
	}
	
	/**
	 * 用于计算给定操作数及操作符的结果的方法
	 * @param operand1
	 * @param operand2
	 * @param operator
	 * @return 计算结果
	 */
	public static int calculateResult(int operand1, int operand2, char operator) {
		int result = 0;
		switch (operator) {
		case '+':
			result = operand2 + operand1;
			break;
		case '-':
			result = operand2 - operand1;
			break;
		case '*':
			result = operand2 * operand1;
			break;
		case '/':
			result = operand2 / operand1;
			break;
		default:
			throw new RuntimeException("未定义运算符：" + operator);
		}
		return result;
	}

}
