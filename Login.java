import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

class Login extends JFrame implements ActionListener
{
	static JPanel jpMain ;
	JPanel jpCenter, jpLogin;
	JTextField name;
	JButton logIn;
	CardLayout cl;
	Font fo1;
	String userName;
	Socket s;
	
	Login(String s)
	{
		super(s);
		
		jpLogin = new JPanel(new FlowLayout(FlowLayout.CENTER,10,30));
		jpCenter = new JPanel(new BorderLayout());
		jpMain = new JPanel();
		cl =new CardLayout();
		jpMain.setLayout(cl);
		
		fo1 =new Font("Times New Roman",Font.BOLD,30);
		name = new JTextField("",30);
		name.setFont(fo1);
		logIn = new JButton("logIn");
		logIn.addActionListener(this);
		logIn.setBackground(Color.red);
		
		jpLogin.add(name);
		jpLogin.add(logIn);
		jpCenter.add(jpLogin,BorderLayout.CENTER);
		jpMain.add(jpCenter,"JP1");
		add(jpMain);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(450, 150);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		//pack();		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== logIn)
		{
			new ChitChat(userName);
			cl.next(jpMain);
		}
	}
}