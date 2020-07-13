package inversepolishcalculator.calculate;

/**
 * 使用数组实现栈类
 * 
 * @author 独孤猿1998
 *
 */
public class Stack<E> {

	private int maxSize;//栈的最大容量
	private Object[] elements;
	private int top;//指向栈顶元素
	
	public Stack() {
		this(30);
	}
	
	public Stack(int maxSize) {
		this.maxSize = maxSize;
		elements = new Object[this.maxSize];
		top = -1;
	}
	
	/**
	 * 判断栈是否为空的方法
	 * @return 判断是否栈空的结果
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * 判断栈是否已满的方法
	 * @return 判断是否栈满的结果
	 */
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	/**
	 * 元素入栈的方法
	 * @param element 即将入栈的元素
	 */
	public void push(E element) {
		if (isFull()) {
			System.out.println("栈满，元素无法入栈！");
			return ;
		}
		elements[++top] = element;
	}
	
	/**
	 * 取栈顶元素的方法，栈顶元素不出栈
	 * @return 栈顶元素
	 */
	public E peek() {
		if (isEmpty()) {
			throw new RuntimeException("栈空，无法取栈顶元素！");
		}
		return (E)elements[top];
	}
	
	/**
	 * 栈顶元素出栈的方法
	 * @return 栈顶出栈的元素
	 */
	public E pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空，栈顶元素无法出栈！");
		}
		return (E)elements[top--];
	}
	
}
