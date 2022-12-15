package com.card;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class register implements ActionListener,KeyListener{
	JFrame mainFrame = new JFrame();
	JPanel northPane = new JPanel();
	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();
	
	JLabel nLabel = new JLabel("		注册功能			");
	
	JLabel 	cCardNumLabel = new JLabel("卡号：");
	JLabel 	cStudentNumLabel = new JLabel("学号:");
	JLabel 	cStudentNameLabel = new JLabel("姓名:");
	
	JLabel 	pass = new JLabel("重置密码:");
	
	JTextField CardNumField = new JTextField("0",8);
	JTextField StudentNumField = new JTextField("0",8);
	JTextField StudentNameField = new JTextField("NULL",8);
	
	JPasswordField passField = new JPasswordField("0000",4);
	
	JButton centrueBtn = new JButton("注册");
	
	JLabel 	sLabel = new JLabel("注册信息：");
	JTextArea showArea = new JTextArea(10,50);
	
	String   CNu,SNu,SNa,Pa,sentence;
	
	
	public register(){
		mainFrame.setTitle("充值");
		mainFrame.setBounds(400,250,700,300);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
		
		northPane.add(nLabel);
		  	
		centerPane.add(cCardNumLabel);
		centerPane.add(CardNumField); 
		centerPane.add(cStudentNumLabel); 
		centerPane.add(StudentNumField); 
		centerPane.add(cStudentNameLabel); 
		centerPane.add(StudentNameField); 
		centerPane.add(pass);
		centerPane.add(passField);
		centerPane.add(centrueBtn); 
		
		southPane.add(sLabel);
		southPane.add(showArea);
		
		mainFrame.add(northPane,BorderLayout.NORTH);
		mainFrame.add(centerPane,BorderLayout.CENTER);
		mainFrame.add(southPane,BorderLayout.SOUTH);
		mainFrame.getRootPane().setDefaultButton(centrueBtn); 
		showArea.setEditable(false);
		showArea.setText("卡号"+"		    "+"学号"+"		   "+"姓名"+"\r\n");
		centrueBtn.addActionListener(this);
	}
	//构造一个错误提示框
	public void messageFrame(String str){
		JOptionPane.showMessageDialog(mainFrame,str);
	}
	//构造一个显示结果
	public void show(String c ,String s,String r){
		sentence=c+"		"+s+"		"+r+"\r\n";
		showArea.append(sentence);
		sentence="";
	}
	
	//得到卡号等输入信息
	public boolean getMessage(){
		boolean flag = false;
		CNu=CardNumField.getText();
		SNu=StudentNumField.getText();
		SNa=StudentNameField.getText();
		Pa=	new String(passField.getPassword());
		if(CNu.length()==8||SNu.length()==8){
			flag = true;
			
		}else {
			messageFrame("对不起，输入的卡号或学号错误，请重新输入");
			clear();
			}
		return flag;
	}
	
	//清空结果；
	public void clear(){
		
		CardNumField.setText("0");
		StudentNumField.setText("0");
		StudentNameField.setText("NULL");
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();	
	p1:	if(source==centrueBtn){
		showArea.setText("卡号"+"		    "+"学号"+"		   "+"姓名"+"\r\n");
			if(getMessage()){//02
					if (Query(CNu)){
						messageFrame("对不起，卡号已存在，请重新输入！");
							clear();
							break p1;
					}else{
						//创建帐号
						createAccount(CNu,SNu,SNa,Pa);
						show(CNu,SNu,SNa);
						messageFrame("新增成功！");
						clear();
					}
			}//02
			
		}
		
	}
	//查询是否已存在卡号，
	public boolean Query(String cd)
	{
		boolean pass=false;
		try {
			String url="jdbc:mysql://localhost:3306/school";//"jdbc:sqlserver://localhost:1433;
			String sql="select cardNumber from cardTable where cardNumber='"+cd+"'";
			Class.forName("com.mysql.cj.jdbc.Driver"); //com.microsoft.sqlserver.jdbc.SQLServerDriver
			
			Connection con=DriverManager.getConnection(url,"root","033018");
		
			Statement state=con.createStatement();
			ResultSet ret=state.executeQuery(sql);
			if(ret.next())
				{
					pass=true;
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return pass;
	}	
	

	
//创建帐号
	public    void createAccount(String cn,String sn,String s,String p){
		
	
		try {
			String url="jdbc:mysql://localhost:3306/school";
			String sql="insert into cardTable values('"+cn+"','"+sn+"','"+s+"','0','0.0','0.0','"+p+"')";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,"root","033018");
		
			Statement state=con.createStatement();
			state.execute(sql);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		   
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
