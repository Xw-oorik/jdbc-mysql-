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
	
	JLabel nLabel = new JLabel("		ע�Ṧ��			");
	
	JLabel 	cCardNumLabel = new JLabel("���ţ�");
	JLabel 	cStudentNumLabel = new JLabel("ѧ��:");
	JLabel 	cStudentNameLabel = new JLabel("����:");
	
	JLabel 	pass = new JLabel("��������:");
	
	JTextField CardNumField = new JTextField("0",8);
	JTextField StudentNumField = new JTextField("0",8);
	JTextField StudentNameField = new JTextField("NULL",8);
	
	JPasswordField passField = new JPasswordField("0000",4);
	
	JButton centrueBtn = new JButton("ע��");
	
	JLabel 	sLabel = new JLabel("ע����Ϣ��");
	JTextArea showArea = new JTextArea(10,50);
	
	String   CNu,SNu,SNa,Pa,sentence;
	
	
	public register(){
		mainFrame.setTitle("��ֵ");
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
		showArea.setText("����"+"		    "+"ѧ��"+"		   "+"����"+"\r\n");
		centrueBtn.addActionListener(this);
	}
	//����һ��������ʾ��
	public void messageFrame(String str){
		JOptionPane.showMessageDialog(mainFrame,str);
	}
	//����һ����ʾ���
	public void show(String c ,String s,String r){
		sentence=c+"		"+s+"		"+r+"\r\n";
		showArea.append(sentence);
		sentence="";
	}
	
	//�õ����ŵ�������Ϣ
	public boolean getMessage(){
		boolean flag = false;
		CNu=CardNumField.getText();
		SNu=StudentNumField.getText();
		SNa=StudentNameField.getText();
		Pa=	new String(passField.getPassword());
		if(CNu.length()==8||SNu.length()==8){
			flag = true;
			
		}else {
			messageFrame("�Բ�������Ŀ��Ż�ѧ�Ŵ�������������");
			clear();
			}
		return flag;
	}
	
	//��ս����
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
		showArea.setText("����"+"		    "+"ѧ��"+"		   "+"����"+"\r\n");
			if(getMessage()){//02
					if (Query(CNu)){
						messageFrame("�Բ��𣬿����Ѵ��ڣ����������룡");
							clear();
							break p1;
					}else{
						//�����ʺ�
						createAccount(CNu,SNu,SNa,Pa);
						show(CNu,SNu,SNa);
						messageFrame("�����ɹ���");
						clear();
					}
			}//02
			
		}
		
	}
	//��ѯ�Ƿ��Ѵ��ڿ��ţ�
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
	

	
//�����ʺ�
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
