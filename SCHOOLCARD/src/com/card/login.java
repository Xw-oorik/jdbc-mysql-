package com.card;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class login  implements ActionListener,KeyListener	{		
         		GridBagLayout  	LoginPaneLayout  =  new GridBagLayout();//������������
         		JPanel        	welconePane      = 	new JPanel();//��ӭ���
         		JPanel         	LoginPane     	 =  new JPanel();//��¼��� 		
         		JLabel 			welcomeLabel     =  new JLabel("��ӭ��½У԰��ʹ��ϵͳ");					
         		JLabel 			choseUserLabel   =  new JLabel("ѡ�������¼��Ϣ��");
         		JLabel 			userNameLabel    =  new JLabel("�û���");
         		JLabel 			passwordLabel    =  new JLabel("����");
         		JTextField  	userNameText  	 =  new JTextField(10);  //�û��������
	private 	JPasswordField 	passwordField    =  new JPasswordField(10);//���������
		 		JButton     	loginBtn         =  new JButton("��¼");
		 		
		 		JFrame login=new JFrame();         //�ܿ��������
		 		String Accounts="";  //= (String)userNameText.getText(); //�ʺŻ�÷�����
	private	 	String Password="";//=new String(passwordField.getPassword())//�����÷�����
/*---------------------------------����ඨ�忪ʼ-------------------------------*/
		public login(){				
		login.setTitle("��¼");		//��ܱ�ǩ
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����رհ�ťʱ�رճ���   
		login.setResizable(false);//��ֹ�Ŵ�ť
		login.setBounds(500,300,500,200); //���ÿ�ܴ�ʱ����Ļλ�úͿ�ܴ�С��x,y,width,hight����
		login.setVisible(true);//���ÿ�ܿɼ�		
		LoginPane.setLayout(LoginPaneLayout);//Ϊ���������������					  		
		loginBtn.addActionListener(this);  //Ϊ��¼��ť���ü�����
		
		login.getRootPane().setDefaultButton(loginBtn); //���õ�ǰ��������ȱʡ��ť��
		
	
				
		//ΪchoseUserLabel���ò���
		addComponent(choseUserLabel,1,2,8,1,100,100,GridBagConstraints.NONE,GridBagConstraints.EAST);
		LoginPane.add(choseUserLabel);//��Ӵ���������		
		//ΪuserNameLabel���ò���
		addComponent(userNameLabel,5,4,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(userNameLabel);//��Ӵ���������
		//ΪuserNameText���ò���
		addComponent(userNameText,7,4,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(userNameText);//��Ӵ���������
		//ΪpasswordLabel���ò���
		addComponent(passwordLabel,7,6,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(passwordLabel);//��Ӵ���������
		//ΪpasswordField���ò���
		addComponent(passwordField,11,6,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(passwordField);//��Ӵ���������
		//ΪloginBtn���ò���
		addComponent(loginBtn,7,9,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.EAST);
		LoginPane.add(loginBtn);//��Ӵ���������	
		welconePane.add(welcomeLabel);//��Ӵ���������
		
		JPanel loginFatherPane =  new  JPanel();	//��������壻	
		loginFatherPane.add(welconePane,BorderLayout.NORTH);//���welcome��嵽����岢Ϊ��������������е�λ��
		loginFatherPane.add(LoginPane,BorderLayout.CENTER);//���LoginPane��嵽����岢Ϊ��������������е�λ��
		login.add(loginFatherPane);//�������嵽�ܿ��
	}
	
/*---------------------------------����ඨ�����-------------------------------*/
	
	 // ����Ϊ������������������
		private void addComponent(Component componet,int gridx,int gridy,int gridwidth,int gridheight,
				int weightx,int weighty, int fill,int anchor){		
		GridBagConstraints constraints =new GridBagConstraints();
		constraints.gridx=gridx;//������Ͻǵ�x ��λ�ã�
		constraints.gridy=gridy;//������Ͻǵ�y ��λ�ã�
		constraints.gridwidth=gridwidth;//�������ռ�ĵ�Ԫ��
		constraints.gridheight=gridheight;//�������ռ�ĵ�Ԫ��
		constraints.weightx=weightx;//����������ڵ�Ȩ�أ�
		constraints.weighty=weighty;//����������ڵ�Ȩ�أ�
		constraints.fill=fill;//����Ƿ�����������Ԫ��
		constraints.anchor=anchor;//���λ�ڵ�Ԫ���λ�ã�
		LoginPaneLayout.setConstraints(componet,constraints);//Ϊ���������е����componet������������constraints;
		LoginPane.add(componet);//����������壻
		}
     // ����Ϊ������������������		
		
	//�ʺź�����õ��¼�
		public void getUserNameAndPassword(){
			
	          Accounts = (String)userNameText.getText(); //����û�������ʺ���Ϣ
	          Password =  new String(passwordField.getPassword());//����û������������Ϣ	        
			
		}
	 								
		/* ---------------�����¼�--------------*/
	//	@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	          Object source = e.getSource();
	      	QuerySql  userQuery =new QuerySql();
	          //�����ť��¼����
	          if(source == loginBtn)
	            	{	
	        	  		KeyAndBtnAction();	
	        		
	        			if(userQuery.Query(Accounts,Password,1)){
	        				System.out.println("��¼�ɹ�����");
	        						new admin();
	        						login.dispose();
	        			}else{new loginError();};
	        	  		
	            	}
				}
		
		//��Ϊ���̼�����������������
		
	 public void keyReleased(KeyEvent ke){}		 
		 public void keyPressed(KeyEvent key){
		 	if( KeyEvent.VK_ENTER== key.getKeyChar())
			 		{
			 			KeyAndBtnAction();
		 		}
		 }
		 public void keyTyped(KeyEvent ke){} 
		  	
		
		
		
		//���س������¼��ť����ʱ���øü����ʺ����뺯��
		public void KeyAndBtnAction(){			
		  getUserNameAndPassword();	        	  	        	         	      	
		  
		}
		
					
		
		/*----------------- �����¼�---------------*/
	
		
		public static void main(String[] arguments){
			  login login =new login();		
			  
		  }
		

}

