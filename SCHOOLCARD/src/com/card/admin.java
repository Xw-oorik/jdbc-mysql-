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
     JLabel  	 welcomeLabel =new JLabel("��ֵ���ܽ���");
	 JLabel 	 cardNumLabel =new JLabel("���ţ�");
	 JLabel 	 studentNumLabel =new JLabel("ѧ�ţ�");
	 JLabel  	 addMoneyLabel =new JLabel("��ֵ��");
	 JLabel  	 ensureAgainLabel = new JLabel("�ٴ�ȷ��:");
	 JLabel  	 show    = new JLabel("�����ʾ��");
	 JTextField  cardNumField = new JTextField("0",8);
	 JTextField  studentNumField = new JTextField("0",8);
	 JTextField  addMoneyField = new JTextField("0",5);
	 JTextField  ensureAgainField = new JTextField("0",5);
	 JButton   	 ensureBtn   = new JButton("ȷ��");
	 JButton   	 clearBtn   = new JButton("����");
	 JButton   	 registerBtn   = new JButton("ע��");
	 JTextArea   resultArea  = new JTextArea(10,50);
	 
	 String  cardNum;
	 String  studentNum;
	 String  addMoney;
	 String  ensureAgain;
	 String  sentence;
	 
	
	
	public admin(){
		mainFrame.setTitle("��ֵ");
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
		resultArea.setText("����                          ѧ��                            ����                          ��ֵ���               ���               �����ܶ�"+"\r\n");
		
		
	}
	//����һ��������ʾ��
	public void messageFrame(String str){
		JOptionPane.showMessageDialog(mainFrame,str);
	}

	//�õ�������Ϣ ����һ���ж���Ϣ�ĺϷ��ԣ�
	public boolean getMessage(){
		          boolean flag=false;
		  cardNum=cardNumField.getText() ;
		  studentNum =studentNumField.getText() ;
		  addMoney =addMoneyField.getText() ;
		  ensureAgain =ensureAgainField.getText() ;	 
		  
		  
		 //СBUG  ����ѧ�Ż򿨺ţ����ֵ���Ϊ��ʱ�����ᱨ�� ��Ķ���
		  int  i=Integer.valueOf(addMoney);
		  int  j=Integer.valueOf(ensureAgain);	
		  
		  
		  
		  System.out.print("�õ����ź�ѧ��");
		  boolean g=(cardNum.length()==8)&&(studentNum.length()==8)&&(i==j);   		  
		  System.out.println(g);
		  if(g)
			{flag=true;}
		  else{
			  	flag=false;		
				 messageFrame("�Բ��𣬿��Ż�ѧ�Ż����������������룡");
				 System.out.print("���� 1111������");
				 clear(); 
			  }
			
			return flag;		  		
	}
	//��ʾ����
	public void showResult(String cNu,String sNu,String SNa,String aM,String rM,String cons){
		{
			 sentence=cNu+"            "+sNu+"                    "+ SNa+"	                "+aM+"                     "+rM+"                      "+cons+"\r\n";
						 				 
			 resultArea.append(sentence);
			 sentence="";
		}
		
	}
	
	//���㷽��
	public void clear(){
		
		cardNumField.setText("0");
		studentNumField.setText("0");
		addMoneyField.setText("0");
		ensureAgainField.setText("0");
		resultArea.setText("����                          ѧ��                            ����                          ��ֵ���               ���               �����ܶ�"+"\r\n");
	}
	
	//������
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 QueryCardAndNum query = new  QueryCardAndNum();
		Object source = e.getSource();
		if(source==ensureBtn)
			{
			  System.out.print("���Ե�");
				if(getMessage())
					{   System.out.print("���Ե�2");
						if(query.Query(cardNum,studentNum))
							{
								int j=Integer.valueOf(addMoney);
								update(j,cardNum);
								
							}else{
								System.out.print("����22221������");
								
								messageFrame("�Բ��𣬿��Ż�ѧ���������������룡");
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
	
	//�޸����ݿ�������
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
