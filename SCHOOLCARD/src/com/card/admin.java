package com.card;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class admin  implements ActionListener,KeyListener {
     JFrame  	 mainFrame = new JFrame();
	 JPanel  	 northPane  = new JPanel();
     JPanel  	 centerPane  = new JPanel();
     JPanel  	 southPane  = new JPanel();
     JPanel  	 fatherPane = new JPanel();
     JLabel  	 welcomeLabel =new JLabel("充值功能界面");
	 JLabel 	 cardNumLabel =new JLabel("卡号：");
	 JLabel 	 studentNumLabel =new JLabel("学号：");
	 JLabel  	 addMoneyLabel =new JLabel("充值金额：");
	 JLabel  	 ensureAgainLabel = new JLabel("再次确认:");
	 JLabel  	 show    = new JLabel("结果显示：");
	 JTextField  cardNumField = new JTextField("0",8);
	 JTextField  studentNumField = new JTextField("0",8);
	 JTextField  addMoneyField = new JTextField("0",5);
	 JTextField  ensureAgainField = new JTextField("0",5);
	 JButton   	 ensureBtn   = new JButton("确定");
	 JButton   	 clearBtn   = new JButton("清零");
	 JButton   	 registerBtn   = new JButton("注册");
	 JTextArea   resultArea  = new JTextArea(10,50);
	 
	 String  cardNum;
	 String  studentNum;
	 String  addMoney;
	 String  ensureAgain;
	 String  sentence;
	 
	
	
	public admin(){
		mainFrame.setTitle("充值");
		mainFrame.setBounds(400,250,800,300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(resultArea ,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		northPane.add(welcomeLabel);
		
		centerPane.add(cardNumLabel);
		centerPane.add(cardNumField);
		centerPane.add(studentNumLabel);
		centerPane.add(studentNumField);
		centerPane.add(addMoneyLabel);
		centerPane.add(addMoneyField);
		centerPane.add(ensureAgainLabel);
		centerPane.add(ensureAgainField);
		centerPane.add(ensureBtn);
		centerPane.add(registerBtn);
		southPane.add(show);
		southPane.add(scroll);
		southPane.add(clearBtn);
		
		
		mainFrame.add(northPane,BorderLayout.NORTH);
		mainFrame.add(centerPane,BorderLayout.CENTER);
		mainFrame.add(southPane,BorderLayout.SOUTH);
		
		registerBtn.addActionListener(this);
		ensureBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		mainFrame.getRootPane().setDefaultButton(ensureBtn); 
		resultArea.setEditable(false);
		resultArea.setText("卡号                          学号                            姓名                          充值金额               余额               消费总额"+"\r\n");
		
		
	}
	//构造一个错误提示框
	public void messageFrame(String str){
		JOptionPane.showMessageDialog(mainFrame,str);
	}

	//得到输入信息 并第一次判断信息的合法性；
	public boolean getMessage(){
		          boolean flag=false;
		  cardNum=cardNumField.getText() ;
		  studentNum =studentNumField.getText() ;
		  addMoney =addMoneyField.getText() ;
		  ensureAgain =ensureAgainField.getText() ;	 
		  
		  
		 //小BUG  ，当学号或卡号，或充值金额为空时，不会报错！ 许改动！
		  int  i=Integer.valueOf(addMoney);
		  int  j=Integer.valueOf(ensureAgain);	
		  
		  
		  
		  System.out.print("得到卡号和学号");
		  boolean g=(cardNum.length()==8)&&(studentNum.length()==8)&&(i==j);   		  
		  System.out.println(g);
		  if(g)
			{flag=true;}
		  else{
			  	flag=false;		
				 messageFrame("对不起，卡号或学号或金额有误，请重新输入！");
				 System.out.print("错误 1111！！！");
				 clear(); 
			  }
			
			return flag;		  		
	}
	//显示方法
	public void showResult(String cNu,String sNu,String SNa,String aM,String rM,String cons){
		{
			 sentence=cNu+"            "+sNu+"                    "+ SNa+"	                "+aM+"                     "+rM+"                      "+cons+"\r\n";
						 				 
			 resultArea.append(sentence);
			 sentence="";
		}
		
	}
	
	//清零方法
	public void clear(){
		
		cardNumField.setText("0");
		studentNumField.setText("0");
		addMoneyField.setText("0");
		ensureAgainField.setText("0");
		resultArea.setText("卡号                          学号                            姓名                          充值金额               余额               消费总额"+"\r\n");
	}
	
	//监听器
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 QueryCardAndNum query = new  QueryCardAndNum();
		Object source = e.getSource();
		if(source==ensureBtn)
			{
			  System.out.print("测试点");
				if(getMessage())
					{   System.out.print("测试点2");
						if(query.Query(cardNum,studentNum))
							{
								int j=Integer.valueOf(addMoney);
								update(j,cardNum);
								
							}else{
								System.out.print("错误22221！！！");
								
								messageFrame("对不起，卡号或学号有误，请重新输入！");
								 clear();
								}
					}
			}else if(source==clearBtn)
					{
						clear();
					}else if(source==registerBtn)
						{
						
						 new register();
						 
						
						
						}
		
	}
	
	//修改数据库中数据
	public void update(int i,String strCardNum){
		try {
		String sqlUpdate="update cardTable set addMoney = '"+i+"', remainMoney ='"+i+"' +remainMoney where cardNumber='"+strCardNum+"'";
		String sqlQuery="select * from cardTable where cardNumber='"+strCardNum+"'";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","033018");
		Statement state=con.createStatement();
		state.execute(sqlUpdate);	
										
		ResultSet echo = state.executeQuery(sqlQuery);
			if(echo.next()){
			
				String ShowCNu =echo.getString(1);
				String ShowSNu =echo.getString(2);
				String ShowSNa =echo.getString(3);
				String ShowAM =echo.getString(4);				
				String ShowRM =echo.getString(5);
				String ShowCon = echo.getString(6);
				showResult(ShowCNu,ShowSNu,ShowSNa,ShowAM,ShowRM,ShowCon);
			}
		
	}catch (ClassNotFoundException e) {
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

	public static void main(String[] arguments){
		  admin login =new admin();		
		  
	  }
	
	
}
