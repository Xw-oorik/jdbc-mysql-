package com.card;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class loginError implements ActionListener{
		JFrame errorFrame = new JFrame();
		JPanel errorFatherPane =new JPanel();
		JButton ensureErrorBtn = new JButton("确定");
	    JLabel  errorLable =new JLabel(); 
	public loginError(){
		errorFrame.setTitle("错误！！");		//框架标签
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//点击关闭按钮时注销该窗口；   
		errorFrame.setResizable(false);//禁止放大按钮
		errorFrame.setBounds(540,300,300,150); //设置框架打开时的屏幕位置和框架大小（x,y,width,hight）；
		errorFrame.setVisible(true);//设置框架可见
		errorFrame.addWindowListener(new CloseHandler());		
		ensureErrorBtn.addActionListener(this);		
		errorFatherPane.add(errorLable);
		errorLable.setText("对不起，你输入的帐号或密码错误，请重新输入！");
		
		errorFrame.getRootPane().setDefaultButton(ensureErrorBtn);
		
		
		JLabel blackLable = new JLabel("                      "+"                      "+"                      "
				+"                      ");
		errorFatherPane.add(blackLable,BorderLayout.NORTH);
		errorFatherPane.add(errorLable,BorderLayout.CENTER);
		errorFatherPane.add(ensureErrorBtn,BorderLayout.SOUTH);
		errorFrame.add(errorFatherPane,BorderLayout.CENTER);
	}

	
	
	
	public void actionPerformed(ActionEvent e) {
		Object sourse = e.getSource();
		// TODO Auto-generated method stub
		if(sourse.equals(ensureErrorBtn))
		{      
			login login =     new login();
			errorFrame.dispose();
		}
	}
	   public static class CloseHandler extends WindowAdapter{
			@Override
			public void windowClosing(WindowEvent arg0) {
				login login =     new login();
				}
		   	}

}
