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
     JLabel  welcomeLabel =new JLabel("�ͻ��˹��ܽ���");
	 JLabel  cardNumLabel =new JLabel("���ţ�");	 
	 JLabel  subMoneyLabel =new JLabel("���ѽ���");	 
	 JLabel  show    = new JLabel("�����ʾ��");	 
	 JTextField  cardNumField = new JTextField(8);
	 JTextField  subMoneyField = new JTextField(5);	 
	 JButton    ensureBtn   = new JButton("ȷ��");
	 JButton    clearBtn   = new JButton("����");	
	 JTextArea   resultArea  = new JTextArea(10,40);
	 String cardNum;//����
	 String pay;//���ѽ��
	 String  sentence;//�����洢��ʾ�õ��ַ�����
	 String response;//�������룻
	
	public custom(){
		mainFrame.setTitle("����");
		mainFrame.setBounds(400,250,800,300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(resultArea ,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultArea.setText("����"+"		      "+"���ѽ��"+"		            "+"���"+"\r\n");
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
	//���㿨�ź����ѽ��
	public void clear(){
		 cardNum=null;
		  pay=null;
		  cardNumField.setText(cardNum);
		  subMoneyField.setText(pay);		
	} 
	//�����ʾ����
	public void clear1(){
		resultArea.setText(null);
		resultArea.setText("����"+"		      "+"���ѽ��"+"		            "+"���"+"\r\n");
	}
	
	//�����ʾ	
	public void show(String c ,String s,String r){
		sentence=c+"	    	        "+s+"		           "+r+"\r\n";
		resultArea.append(sentence);
		sentence="";
	}
	//�õ����ŵ����ѽ��,�������ж����ݺϷ���
	public boolean getCardNumAndPay(){
		boolean flag = true;
		cardNum=cardNumField.getText();
		pay=subMoneyField.getText();
		
		if(cardNum.length()!=8||Integer.valueOf(pay)>40||Integer.valueOf(pay)<0){
			flag=false;	
			
		   }
		
		return flag;
	}

	
	//����һ�����������ķ���
	public void showInputFrame (){
		 response=JOptionPane.showInputDialog(mainFrame,"�������������");
		
	}
	//����һ��������ʾ��
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
		
		
					//������ź����ѽ������ж���ȷ
				
					System.out.print("���ź����ѽ������ж���ȷ");
					if( Query(cardNum))//��ѯ�Ƿ���ڴ˿���a1
						{//04	 
							boolean bo=true;
					
						
					while(bo)	{//05
						showInputFrame ();
						if(response.length()==0||response==null)
							{	break p1;	}
						
						
						if(passWordQuery(response,cardNum))//��ѯ���������Ƿ���ȷ���ж�����
							{//06
							 bo=false;
							  update(cardNum,pay);
							  clear();  //�ѿ��źͽ������
							  //��ʾ�����
							}//06
						else{messageFrame("������������������������룺");}							         
					      }  //05
						} //04 
				}//03		
					else{messageFrame("�˿����������������룬���������룺");
					clear();} 
			}//02
				
		
				else   //B1
					{
					   messageFrame("�˿������ڻ����ѽ�����Χ�����������룺");
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
						System.out.println("���ڴ˿�����");
						
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
			System.out.println("�﷨�޴���");
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
