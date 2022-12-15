package com.card;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class login  implements ActionListener,KeyListener	{		
         		GridBagLayout  	LoginPaneLayout  =  new GridBagLayout();//声明网袋布局
         		JPanel        	welconePane      = 	new JPanel();//欢迎面板
         		JPanel         	LoginPane     	 =  new JPanel();//登录面板 		
         		JLabel 			welcomeLabel     =  new JLabel("欢迎登陆校园卡使用系统");					
         		JLabel 			choseUserLabel   =  new JLabel("选择输入登录信息：");
         		JLabel 			userNameLabel    =  new JLabel("用户名");
         		JLabel 			passwordLabel    =  new JLabel("密码");
         		JTextField  	userNameText  	 =  new JTextField(10);  //用户名输入框
	private 	JPasswordField 	passwordField    =  new JPasswordField(10);//密码输入框
		 		JButton     	loginBtn         =  new JButton("登录");
		 		
		 		JFrame login=new JFrame();         //总框架声明；
		 		String Accounts="";  //= (String)userNameText.getText(); //帐号获得方法；
	private	 	String Password="";//=new String(passwordField.getPassword())//密码获得方法；
/*---------------------------------框架类定义开始-------------------------------*/
		public login(){				
		login.setTitle("登录");		//框架标签
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击关闭按钮时关闭程序；   
		login.setResizable(false);//禁止放大按钮
		login.setBounds(500,300,500,200); //设置框架打开时的屏幕位置和框架大小（x,y,width,hight）；
		login.setVisible(true);//设置框架可见		
		LoginPane.setLayout(LoginPaneLayout);//为面板设置网袋布局					  		
		loginBtn.addActionListener(this);  //为登录按钮设置监听器
		
		login.getRootPane().setDefaultButton(loginBtn); //设置当前总容器的缺省按钮！
		
	
				
		//为choseUserLabel设置布局
		addComponent(choseUserLabel,1,2,8,1,100,100,GridBagConstraints.NONE,GridBagConstraints.EAST);
		LoginPane.add(choseUserLabel);//添加此组件到面板		
		//为userNameLabel设置布局
		addComponent(userNameLabel,5,4,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(userNameLabel);//添加此组件到面板
		//为userNameText设置布局
		addComponent(userNameText,7,4,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(userNameText);//添加此组件到面板
		//为passwordLabel设置布局
		addComponent(passwordLabel,7,6,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(passwordLabel);//添加此组件到面板
		//为passwordField设置布局
		addComponent(passwordField,11,6,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.WEST);
		LoginPane.add(passwordField);//添加此组件到面板
		//为loginBtn设置布局
		addComponent(loginBtn,7,9,8,2,100,100,GridBagConstraints.NONE,GridBagConstraints.EAST);
		LoginPane.add(loginBtn);//添加此组件到面板	
		welconePane.add(welcomeLabel);//添加此组件到面板
		
		JPanel loginFatherPane =  new  JPanel();	//声明总面板；	
		loginFatherPane.add(welconePane,BorderLayout.NORTH);//添加welcome面板到总面板并为其声明在总面板中的位置
		loginFatherPane.add(LoginPane,BorderLayout.CENTER);//添加LoginPane面板到总面板并为其声明在总面板中的位置
		login.add(loginFatherPane);//添加总面板到总框架
	}
	
/*---------------------------------框架类定义结束-------------------------------*/
	
	 // 以下为网袋限制条件函数；
		private void addComponent(Component componet,int gridx,int gridy,int gridwidth,int gridheight,
				int weightx,int weighty, int fill,int anchor){		
		GridBagConstraints constraints =new GridBagConstraints();
		constraints.gridx=gridx;//组件左上角的x 轴位置；
		constraints.gridy=gridy;//组件左上角的y 轴位置；
		constraints.gridwidth=gridwidth;//组件横向占的单元格；
		constraints.gridheight=gridheight;//组件纵向占的单元格
		constraints.weightx=weightx;//组件横向相邻的权重；
		constraints.weighty=weighty;//组件纵向相邻的权重；
		constraints.fill=fill;//组件是否扩大填满单元格；
		constraints.anchor=anchor;//组件位于单元格的位置；
		LoginPaneLayout.setConstraints(componet,constraints);//为网袋布局中的组件componet设置限制条件constraints;
		LoginPane.add(componet);//添加组件到面板；
		}
     // 以上为网袋限制条件函数；		
		
	//帐号和密码得到事件
		public void getUserNameAndPassword(){
			
	          Accounts = (String)userNameText.getText(); //获得用户输入的帐号信息
	          Password =  new String(passwordField.getPassword());//获得用户输入的密码信息	        
			
		}
	 								
		/* ---------------监听事件--------------*/
	//	@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	          Object source = e.getSource();
	      	QuerySql  userQuery =new QuerySql();
	          //如果按钮登录激活
	          if(source == loginBtn)
	            	{	
	        	  		KeyAndBtnAction();	
	        		
	        			if(userQuery.Query(Accounts,Password,1)){
	        				System.out.println("登录成功！！");
	        						new admin();
	        						login.dispose();
	        			}else{new loginError();};
	        	  		
	            	}
				}
		
		//此为键盘监听器的三个方法：
		
	 public void keyReleased(KeyEvent ke){}		 
		 public void keyPressed(KeyEvent key){
		 	if( KeyEvent.VK_ENTER== key.getKeyChar())
			 		{
			 			KeyAndBtnAction();
		 		}
		 }
		 public void keyTyped(KeyEvent ke){} 
		  	
		
		
		
		//当回车键或登录按钮激活时调用该检验帐号密码函数
		public void KeyAndBtnAction(){			
		  getUserNameAndPassword();	        	  	        	         	      	
		  
		}
		
					
		
		/*----------------- 监听事件---------------*/
	
		
		public static void main(String[] arguments){
			  login login =new login();		
			  
		  }
		

}

