package com.card;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class loginError implements ActionListener{
		JFrame errorFrame = new JFrame();
		JPanel errorFatherPane =new JPanel();
		JButton ensureErrorBtn = new JButton("ȷ��");
	    JLabel  errorLable =new JLabel(); 
	public loginError(){
		errorFrame.setTitle("���󣡣�");		//��ܱ�ǩ
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//����رհ�ťʱע���ô��ڣ�   
		errorFrame.setResizable(false);//��ֹ�Ŵ�ť
		errorFrame.setBounds(540,300,300,150); //���ÿ�ܴ�ʱ����Ļλ�úͿ�ܴ�С��x,y,width,hight����
		errorFrame.setVisible(true);//���ÿ�ܿɼ�
		errorFrame.addWindowListener(new CloseHandler());		
		ensureErrorBtn.addActionListener(this);		
		errorFatherPane.add(errorLable);
		errorLable.setText("�Բ�����������ʺŻ�����������������룡");
		
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
