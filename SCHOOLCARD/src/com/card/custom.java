package com.card;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class custom implements ActionListener,KeyListener {
     JFrame  mainFrame = new JFrame();    
	 JPanel  northPane  = new JPanel();
     JPanel  centerPane  = new JPanel();
     JPanel  southPane  = new JPanel();
     JPanel  fatherPane = new JPanel();  
     JLabel  welcomeLabel =new JLabel("客户端功能界面");
	 JLabel  cardNumLabel =new JLabel("卡号：");	 
	 JLabel  subMoneyLabel =new JLabel("消费金额金额：");	 
	 JLabel  show    = new JLabel("结果显示：");	 
	 JTextField  cardNumField = new JTextField(8);
	 JTextField  subMoneyField = new JTextField(5);	 
	 JButton    ensureBtn   = new JButton("确定");
	 JButton    clearBtn   = new JButton("清零");	
	 JTextArea   resultArea  = new JTextArea(10,40);
	 String cardNum;//卡号
	 String pay;//消费金额
	 String  sentence;//用来存储显示用的字符串；
	 String response;//二级密码；
	
	public custom(){
		mainFrame.setTitle("消费");
		mainFrame.setBounds(400,250,800,300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(resultArea ,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultArea.setText("卡号"+"		      "+"消费金额"+"		            "+"余额"+"\r\n");
		northPane.add(welcomeLabel);
		
		centerPane.add(cardNumLabel);
		centerPane.add(cardNumField);
		centerPane.add(subMoneyLabel);
		centerPane.add(subMoneyField);
		centerPane.add(ensureBtn);
		
		southPane.add(show);
		southPane.add(scroll);
		southPane.add(clearBtn);
		
		
		mainFrame.add(northPane,BorderLayout.NORTH);
		mainFrame.add(centerPane,BorderLayout.CENTER);
		mainFrame.add(southPane,BorderLayout.SOUTH);
		resultArea.setEditable(false);
		ensureBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		mainFrame.getRootPane().setDefaultButton(ensureBtn);
		
	}
	//清零卡号和消费金额
	public void clear(){
		 cardNum=null;
		  pay=null;
		  cardNumField.setText(cardNum);
		  subMoneyField.setText(pay);		
	} 
	//清空显示区域
	public void clear1(){
		resultArea.setText(null);
		resultArea.setText("卡号"+"		      "+"消费金额"+"		            "+"余额"+"\r\n");
	}
	
	//结果显示	
	public void show(String c ,String s,String r){
		sentence=c+"	    	        "+s+"		           "+r+"\r\n";
		resultArea.append(sentence);
		sentence="";
	}
	//得到卡号的消费金额,并初步判断数据合法性
	public boolean getCardNumAndPay(){
		boolean flag = true;
		cardNum=cardNumField.getText();
		pay=subMoneyField.getText();
		
		if(cardNum.length()!=8||Integer.valueOf(pay)>40||Integer.valueOf(pay)<0){
			flag=false;	
			
		   }
		
		return flag;
	}

	
	//构造一个二级密码框的方法
	public void showInputFrame (){
		 response=JOptionPane.showInputDialog(mainFrame,"请输入二级密码");
		
	}
	//构造一个错误提示框
	public void messageFrame(String str){
		JOptionPane.showMessageDialog(null,str);
	}
	
	
	

	
	@Override	
	public void actionPerformed(ActionEvent e) {//01
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source==ensureBtn)         
			{//02
				clear1();
		p1:		if(getCardNumAndPay())  //b1
				{//03
		
		
					//如果卡号和消费金额初步判断正确
				
					System.out.print("卡号和消费金额初步判断正确");
					if( Query(cardNum))//查询是否存在此卡！a1
						{//04	 
							boolean bo=true;
					
						
					while(bo)	{//05
						showInputFrame ();
						if(response.length()==0||response==null)
							{	break p1;	}
						
						
						if(passWordQuery(response,cardNum))//查询二级密码是否正确的判断条件
							{//06
							 bo=false;
							  update(cardNum,pay);
							  clear();  //把卡号和金额清零
							  //显示结果；
							}//06
						else{messageFrame("二级密码输入错误，请重新输入：");}							         
					      }  //05
						} //04 
				}//03		
					else{messageFrame("此卡不存在请重新输入，请重新输入：");
					clear();} 
			}//02
				
		
				else   //B1
					{
					   messageFrame("此卡不存在或消费金额超出范围，请重新输入：");
					}					
					
	
				
				}//01
	
	
	
	
	public boolean Query(String CNu)
	{
		boolean pass=false;
		try {
			String url="jdbc:mysql://localhost:3306/school";
			String sql="select * from cardTable where cardNumber='"+CNu+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,"root","033018");
		
			Statement state=con.createStatement();
			ResultSet ret=state.executeQuery(sql);
			if(ret.next())
				{
						pass=true;
						System.out.println("存在此卡！！");
						
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
	
	
public boolean passWordQuery(String pw,String cnu)
	{
		boolean pass=false;
		try {
			String url="jdbc:mysql://localhost:3306/school";
			String sql="select Pass from cardTable where Pass= '"+pw+"'and cardNumber='"+cnu+"' ";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,"root","033018");
		
			Statement state=con.createStatement();
			ResultSet set=state.executeQuery(sql);
			if(set.next())
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
		
	public boolean update(String cd,String p)
	{
		boolean pass=false;
		double py = Double.valueOf(p);
		try {
			String url="jdbc:mysql://localhost:3306/school";
			
			String sql="update cardTable set Consumption =Consumption+'"+py+"',remainMoney =remainMoney-'"+py+"'  where cardNumber= '"+cd+"'";


			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,"root","033018");
			Statement state=con.createStatement();
			state.execute(sql);
			
			String sql2="select * from cardTable  where cardNumber='"+cd+"'";
			System.out.println("语法无错！！");
			ResultSet set =state.executeQuery(sql2);
			
			if(set.next())
				{
				String ShowCNu =set.getString(1);																	
				String ShowRe = set.getString(5);
				
				show(ShowCNu,pay,ShowRe);
			
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
	
	
	public static void main(String[] arguments){
		  custom admin =new custom();		
		  
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
