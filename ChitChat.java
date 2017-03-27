import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

class ChitChat  implements ActionListener
{
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	JPanel broadCast ;
	JPanel	message , onLine ,jp1,jpNorth , jpOnline;
	JLabel userName ,firstImage,secondImage,thirdImage;
	TextArea txt ;
	Font fo1,fo2;
	JLabel onLineName;
	JButton send;
	ChitChat(String str)
	{
		
		broadCast = new JPanel();
		broadCast.setLayout(new BoxLayout(broadCast, BoxLayout.Y_AXIS));
		message = new JPanel();
		onLine = new JPanel();
		jpOnline = new JPanel();
		txt = new TextArea();
		send = new JButton("SEND");
		send.addActionListener(this);
		
		
		
		TitledBorder border1 = new TitledBorder("Group Message");
		border1.setTitleJustification(TitledBorder.LEFT);
		border1.setTitlePosition(TitledBorder.TOP);
		
		TitledBorder border2 = new TitledBorder("Online");
		border2.setTitleJustification(TitledBorder.LEFT);
		border2.setTitlePosition(TitledBorder.TOP);
		
		TitledBorder border3 = new TitledBorder("Type Message");
		border3.setTitleJustification(TitledBorder.LEFT);
		border3.setTitlePosition(TitledBorder.TOP);
		
		broadCast.setBorder(border1);
		onLine.setBorder(border2);
		message.setBorder(border3);
		
		
		fo1 =new Font("Times New Roman",Font.BOLD,15);
		fo2 =new Font("Times New Roman",Font.BOLD,30);
		txt.setFont(fo1);
		message.add(txt,FlowLayout.LEFT);
		message.add(send);
		
		jp1 = new JPanel(new BorderLayout());
		jpNorth = new JPanel();
		userName = new JLabel(str, new ImageIcon("we.png"),JLabel.CENTER);
		firstImage =new JLabel("",new ImageIcon("w.png"),JLabel.CENTER);
		secondImage = new JLabel("", new ImageIcon("c.png"),JLabel.CENTER);
		thirdImage = new JLabel("", new ImageIcon("o.png"),JLabel.CENTER);
		onLineName = new JLabel("",new ImageIcon("l.png"),JLabel.CENTER);
		jpNorth.add(userName,FlowLayout.LEFT);
		jpNorth.add(secondImage,FlowLayout.LEFT);
		jpNorth.add(thirdImage,FlowLayout.LEFT);
		jpOnline.add(firstImage);
		jpOnline.add(onLineName);
		onLine.add(jpOnline);
		
		
		jp1.add(jpNorth, BorderLayout.NORTH);
		jp1.add(onLine,BorderLayout.EAST);
		jp1.add(message,BorderLayout.SOUTH);
		JScrollPane pane = new JScrollPane(broadCast);
		//DefaultCaret caret = (DefaultCaret)pane.getCaret();
		//caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		jp1.add(pane,BorderLayout.CENTER);
		//jp1.add(broadCast,BorderLayout.CENTER);
		
		Login.jpMain.add(jp1,"JP2");
				
		try
		{
			//s=new Socket("192.168.43.108",10);
			s= new Socket("localhost",10);
			System.out.println(s);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			clientChat();
		}catch(Exception e){}
		
	}
	public void clientChat() throws IOException
	{
		My m = new My(din);
		Thread t1 = new Thread(m);
		t1.start();
		
	}
	
	class My implements Runnable
	{
		DataInputStream din;
		My(DataInputStream din)
		{
			this.din=din;
		}
		public void run()
		{
			String s2 ="";
			
				try{
					//server message
				do{	
					s2=din.readUTF();
					System.out.println(s2);
					JPanel bm = new JPanel();
					JTextArea ta =new JTextArea();
					ta.setFont(fo2);
					ta.setText(s2);
					ta.setBackground(Color.white);
					//ta.setEditable(false);
					
					bm.add(ta);
					broadCast.add(bm);
					Login.jpMain.revalidate();
					Login.jpMain.repaint();
				}while(!s2.equals("Stop"));	
					
				}catch(Exception e1e){System.out.println(e1e);}
			
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==send)
		{
			String mess = txt.getText();
			if(mess.length()==0)
			{
				//JOptionPane.showMessageDialog(null, "No Message To Send","Inane error",JOptionPane.ERROR_MESSAGE );
				JOptionPane.showMessageDialog(null,"No Message To Send.","Inane warning",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				try{
				//new MyClient(str,s);
						dout.writeUTF(mess);
						dout.flush();
						System.out.println("Server Message ");
						txt.setText("");
				}catch(Exception aer){}
			}
		}
	}

}