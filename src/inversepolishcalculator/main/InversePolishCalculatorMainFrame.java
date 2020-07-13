package inversepolishcalculator.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inversepolishcalculator.calculate.InversePolishCalculator;

public class InversePolishCalculatorMainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel backgroundPanel;
	private JLabel nifixExpressionLabel;
	private JTextField nifixExpressionTextField;
	private JButton calculateButton;
	private JButton clearButton;
	private JLabel postfixExpressionLabel;
	private JTextField postfixExpressionTextField;
	private JLabel calculateResultLabel;
	private JTextField calculateResultTextField;
	private static Font textFont = new Font("微软雅黑", Font.PLAIN, 18);
	private static Font buttonFont = new Font("微软雅黑", Font.PLAIN, 16);
	
	public InversePolishCalculatorMainFrame() {
		initialization();
	}
	
	/**
	 * 窗口初始化的方法
	 */
	public void initialization() {
		this.setTitle("逆波兰计算器");//设置窗口标题
		this.setSize(550, 320);//设置窗口大小
		this.setLocationRelativeTo(null);//设置窗口居中
		this.setResizable(false);//设置窗口大小不可改变
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		addViewElement();//窗口添加组件
		this.setVisible(true);
	}
	
	/**
	 * 窗口添加组件的方法
	 */
	public void addViewElement() {
		backgroundPanel = new JPanel(null);
		nifixExpressionLabel = new JLabel("请输入中缀表达式：");
		nifixExpressionLabel.setSize(300, 20);
		nifixExpressionLabel.setLocation(40, 50);
		nifixExpressionLabel.setFont(textFont);
		backgroundPanel.add(nifixExpressionLabel);
		
		nifixExpressionTextField = new JTextField();
		nifixExpressionTextField.setSize(300, 30);
		nifixExpressionTextField.setLocation(40, 75);
		nifixExpressionTextField.setFont(textFont);
		backgroundPanel.add(nifixExpressionTextField);
		
		calculateButton = new JButton("计算");
		calculateButton.setSize(70, 30);
		calculateButton.setLocation(350, 75);
		calculateButton.setFont(buttonFont);
		calculateButton.setBorder(BorderFactory.createRaisedBevelBorder());
		calculateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				postfixExpressionTextField.setText(InversePolishCalculator.nifixTransformPostfix(nifixExpressionTextField.getText()));
				calculateResultTextField.setText("" + InversePolishCalculator.calculatePostfixExpression(postfixExpressionTextField.getText()));
			}
		});
		backgroundPanel.add(calculateButton);
		
		clearButton = new JButton("清空");
		clearButton.setSize(70, 30);
		clearButton.setLocation(430, 75);
		clearButton.setFont(buttonFont);
		clearButton.setBorder(BorderFactory.createRaisedBevelBorder());
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nifixExpressionTextField.setText("");
				postfixExpressionTextField.setText("");
				calculateResultTextField.setText("");
			}
		});
		backgroundPanel.add(clearButton);
		
		postfixExpressionLabel = new JLabel("中缀表达式转换后的后缀表达式：");
		postfixExpressionLabel.setSize(300, 20);
		postfixExpressionLabel.setLocation(40, 115);
		postfixExpressionLabel.setFont(textFont);
		backgroundPanel.add(postfixExpressionLabel);
		
		postfixExpressionTextField = new JTextField();
		postfixExpressionTextField.setSize(300, 30);
		postfixExpressionTextField.setLocation(40, 140);
		postfixExpressionTextField.setFont(textFont);
		backgroundPanel.add(postfixExpressionTextField);
		
		calculateResultLabel = new JLabel("计算结果：");
		calculateResultLabel.setSize(300, 20);
		calculateResultLabel.setLocation(40, 180);
		calculateResultLabel.setFont(textFont);
		backgroundPanel.add(calculateResultLabel);
		
		calculateResultTextField = new JTextField();
		calculateResultTextField.setSize(300, 30);
		calculateResultTextField.setLocation(40, 205);
		calculateResultTextField.setFont(textFont);
		backgroundPanel.add(calculateResultTextField);
		
		this.setContentPane(backgroundPanel);
	}
	
	public static void main(String[] args) {
		new InversePolishCalculatorMainFrame();
	}

}
