package inversepolishcalculator.calculate;

/**
 * 运算符类，用于比较运算符的优先级
 * 
 * @author 独孤猿1998
 *
 */
public class Operator {
	
	private static final int ADD = 1;//'+' 的优先级定义
	private static final int SUB = 1;//'-' 的优先级定义
	private static final int MUL = 2;//'*' 的优先级定义
	private static final int DIV = 2;//'/' 的优先级定义
	
	/**
	 * 获取给定运算符的优先级的犯法
	 * @param operator 给定的运算符
	 * @return 给定运算符的优先级
	 */
	public static int getOperatorPriority(char operator) {
		int priority = 0;
		switch (operator) {
		case '+':
			priority = ADD;
			break;
		case '-':
			priority = SUB;
			break;
		case '*':
			priority = MUL;
			break;
		case '/':
			priority = DIV;
			break;
		default:
			throw new RuntimeException("未定义运算符：" + operator);
		}
		return priority;
	}

}
