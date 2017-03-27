import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.*;


public class TwentyFourty extends JFrame implements ActionListener ,KeyListener
{
		JPanel jpMain,jpNorth,jpSouth,jpImage, jpGame;
		JButton start,close,back,scoreCard;
		CardLayout cl;
		JLabel score , image ,highestScore;
		int startFlag=0,k=2,m=0,playerScore=0, highest=0 ,flag=0;
		int[] arr ={4,8,16,32,64,128,256,512,1024,2048}; 
		JPanel[][] jp;
		JLabel[][] jl;
		Game g ;
		
			
		TwentyFourty(String s)
		{
			super(s);
			jpMain=new JPanel();
			jpNorth =new JPanel(new FlowLayout(FlowLayout.CENTER , 10 ,10));
			jpImage = new JPanel();
			jpSouth = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 ,10));
			cl=new CardLayout();
			score = new JLabel("SCORE - " + playerScore);
			highestScore = new JLabel("HIGHEST SCORE - "+highest);
			image = new JLabel("", new ImageIcon("2048_.png"), JLabel.CENTER);
			score.setVisible(false);
			jpMain.setLayout(cl);
			start = new JButton("START");
			close = new JButton("CLOSE");
			back = new JButton("BACK");
			scoreCard = new JButton("HELP");
			start.addActionListener(this);
			close.addActionListener(this);
			back.addActionListener(this);
			scoreCard.addActionListener(this);
			jpSouth.add(start);
			jpSouth.add(back);
			jpSouth.add(close);
			jpNorth.add(scoreCard);
			jpNorth.add(scoreCard);
			jpNorth.add(highestScore);
			jpNorth.add(score);
			
			jpImage.add(image,BorderLayout.CENTER);
			jpMain.add(jpImage,"jp1");
			jpGame = new JPanel();
			game();
			highestScore.setText("HIGHEST SCORE - "+highest);
			jpMain.add(jpGame,"jp2");
			back.setVisible(false);
			add(jpMain,BorderLayout.CENTER);
			add(jpNorth,BorderLayout.NORTH);
			add(jpSouth,BorderLayout.SOUTH);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			setLocation(450, 150);
			setResizable(false);
			setVisible(true);
			pack();
		}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== start)
			{
				score.setVisible(true);
				back.setVisible(true);
				start.setVisible(false);
				close.setVisible(false);
				playerScore=0;
				startFlag =1;
				cl.last(jpMain);
				randomPlay();
				randomPlay();
			}
			if(e.getSource()== close)
			{
				//System.exit(0);
				dispose();
			}
			if(e.getSource()== back)
			{
				back();
				
			}
			if(e.getSource()== scoreCard)
			{
				System.out.println("score card 4 - 1\n 8 - 3 \n 16 - 7\n 32 - 15 \n 64 - 31\n 128 - 63 \n 256 - 127 \n 512 - 255 \n 1024 - 511\n 2048 - 1023 \n");
						JOptionPane.showMessageDialog(null, 
						" Use Arrow Key To Play\nCombination - Point\n4 - 1\n 8 - 3 \n 16 - 7\n 32 - 15 \n 64 - 31\n 128 - 63 \n 256 - 127 \n 512 - 255 \n 1024 - 511\n 2048 - 1023 \n",
						"HELP", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
		public void keyReleased(KeyEvent e) 
		{
			if(startFlag == 1)
			{
				if(e.getKeyCode()== KeyEvent.VK_RIGHT)
				{
					System.out.println("right");
					flag=1;
					right(k,m);
					setup();
				}
				else if(e.getKeyCode()== KeyEvent.VK_LEFT)
				{
					System.out.println("left");
					flag=1;
					left(m,k);
					setup();
				}
				else if(e.getKeyCode()== KeyEvent.VK_DOWN)
				{
					System.out.println("down");
					flag=1;
					down(k,m);
					setup();
				}
				else if(e.getKeyCode()== KeyEvent.VK_UP)
				{
					System.out.println("up");
					flag=1;
					up(m,k);
					setup();
				}
				playerScore=0;
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						System.out.print(g.board[i][j]+"   ");
						
						for(int a=0;a<10;a++)
						{
							if(g.board[i][j] == arr[a] )
							{
								playerScore += (arr[a]-2)/2;
							}
						}
					}
					System.out.println("");
				}
				score.setText("SCORE - "+playerScore);
				scoreInt();
				while(g.hasWon())
				{
					scoreInt();
					int result = JOptionPane.showConfirmDialog(null, "YOU WON \n your score "+playerScore+"\n Want To Try Again","WON", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) 
						{
							System.out.println("won");
							refresh();
							randomPlay();
							randomPlay();
						}
						else
						{
							back();
						}
				}
				
				if(g.getAvailableStates().isEmpty())
				{
					scoreInt();
					if(g.isMovePossible())
					{
						int result = JOptionPane.showConfirmDialog(null, "Your Score "+playerScore+"\nTry again","Loose", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) 
						{
							System.out.println("Loose");
							refresh();
							randomPlay();
							randomPlay();
						}
						else
						{
							back();
						}
					}
				}
				else
				{
					if(flag==1)
					{
						System.out.println("else chala");
						scoreInt();
						randomPlay();
						flag=0;
					}
				}
			}
		}
		public void back()
		{
			score.setVisible(false);
			back.setVisible(false);
			start.setVisible(true);
			close.setVisible(true);
			playerScore=0;
			score.setText("SCORE - "+playerScore);
			refresh();
			startFlag =0;
			cl.show(jpMain,"jp1");
		}
		
		public void scoreInt()
		{
			try
					{  
						StringBuffer str = new StringBuffer();
						FileInputStream fin=new FileInputStream("abc.txt");  
						int i=0;  
						while((i=fin.read())!=-1)
						{str.append((char)i); }
						String str1 = str.toString();
						highest=Integer.parseInt(str1);					
						fin.close();  
					}catch(Exception e){System.out.println(e);}
			if(highest<=playerScore)
					{	try
						{
							FileOutputStream fout=new FileOutputStream("abc.txt");  
							String s=Integer.toString(playerScore);  
							byte b[]=s.getBytes();
							fout.write(b);  
							highestScore.setText("HIGHEST SCORE - "+highest);
							fout.close(); 
						}catch(Exception y){}
					}
					
					
		}
		
		public void keyTyped(KeyEvent e) 
		{
			System.out.println("keyTyped");
			
		}
	
		public void keyPressed(KeyEvent e) 
		{
			System.out.println("keyPressed");
		}
		public void game()
		{
			jp = new JPanel[3][3];
			jl = new JLabel[3][3];
			jpGame.setLayout(new GridLayout(3,3,0,0));
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					jp[i][j]=new JPanel();
					jl[i][j]=new JLabel("", new ImageIcon("0_.png"), JLabel.CENTER);
					jp[i][j].add(jl[i][j],BorderLayout.CENTER);
					jpGame.add(jp[i][j]);
				}
			}  
			scoreInt();
			g = new Game();
			
		}
		
		
		public void randomPlay()
		{
			int indx = getRandom(g.getAvailableStates());
			Point p = g.availablePoints.get(indx);
			g.placeAMove(p,2);
			jl[p.x][p.y].setIcon(new ImageIcon("2_.png"));
			for (int i = 0; i < 3; i++) 
			{
				for (int j = 0; j < 3; j++) 
				{
					System.out.print(g.board[i][j]+"   ");
				}
				System.out.println("");
			}
		}
		public int getRandom(List<Point> array) 
		{
			int rnd = new Random().nextInt(array.size());
			return rnd;
		}
		
	public void refresh()
	{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					g.board[i][j]=0;
					jl[i][j].setIcon(new ImageIcon("0_.png"));
					playerScore =0;
				}
			}
			score.setText("SCORE - "+playerScore);
	}
	
	public void right(int k , int m)
	{
		for(int i=0;i<3;i++)
		{
			for(int j=k;j>m+1;j--)
			{
				if(g.board[i][j]== 0 || (g.board[i][j]== g.board[i][j-1]))
				{
					
					if(g.board[i][j] == 0)
					{
						if(g.board[i][j-1]== 0)
						{
							g.board[i][j] = g.board[i][j-2];
							g.board[i][j-1]=0;
							g.board[i][j-2]=0;
							break;
						}
						else
						{
							if(g.board[i][j-1] == g.board[i][j-2])
							{
								g.board[i][j] = g.board[i][j-1] + g.board[i][j-2];
								g.board[i][j-1]=0;
								g.board[i][j-2]=0;
								break;
							}
							else
							{
								g.board[i][j]=g.board[i][j-1];
								g.board[i][j-1]=g.board[i][j-2];
								g.board[i][j-2]=0;
								break;
							}
						}
					}
					else if((g.board[i][j]== g.board[i][j-1]))
					{	g.board[i][j] += g.board[i][j-1];
						g.board[i][j-1] = g.board[i][j-2];
						g.board[i][j-2] = 0;
						break;
					}
				}
				else
				{
					if(g.board[i][j-1] == 0)
					{
						g.board[i][j-1]=g.board[i][j-2];
						g.board[i][j-2]=0;
						if((g.board[i][j]== g.board[i][j-1]))
						{	
							g.board[i][j] += g.board[i][j-1];
							g.board[i][j-1] = g.board[i][j-2];
							break;
						}
						break;
					}
					if(g.board[i][j-1] == g.board[i][j-2])
					{
						g.board[i][j-1] += g.board[i][j-2];
						g.board[i][j-2] =0;
						break;
					}
					
				}
			}
		}
	}
	public void left(int k , int m)
	{
		
		for(int i=0;i<3;i++)
		{
			for(int j=k;j<m-1;j++)
			{
				if(g.board[i][j]== 0 || (g.board[i][j]== g.board[i][j+1]))
				{
					
					
					if(g.board[i][j] == 0)
					{
						if(g.board[i][j+1]== 0)
						{
							g.board[i][j] =g.board[i][j+2];
							g.board[i][j+1]=0;
							g.board[i][j+2]=0;
							break;
						}
						else
						{
							if(g.board[i][j+1] == g.board[i][j+2])
							{
								g.board[i][j] = g.board[i][j+1] + g.board[i][j+2];
								g.board[i][j+1]=0;
								g.board[i][j+2]=0;
								break;
							}
							else
							{
								g.board[i][j]=g.board[i][j+1];
								g.board[i][j+1]=g.board[i][j+2];
								g.board[i][j+2]=0;
								break;
							}
						}
					}
					else if((g.board[i][j]== g.board[i][j+1]))
					{	g.board[i][j] += g.board[i][j+1];
						g.board[i][j+1] = g.board[i][j+2];
						g.board[i][j+2] = 0;
						break;
					}
				}
				else
				{
					if(g.board[i][j+1] == 0)
					{
						g.board[i][j+1]=g.board[i][j+2];
						g.board[i][j+2]=0;
						if((g.board[i][j]== g.board[i][j+1]))
						{	
							g.board[i][j] += g.board[i][j+1];
							g.board[i][j+1] = g.board[i][j+2];
							break;
						}
						break;
					}
					if(g.board[i][j+1] == g.board[i][j+2])
					{
						g.board[i][j+1] += g.board[i][j+2];
						g.board[i][j+2] =0;
						break;
					}
					
				}
			}
		}
	}
	public void down(int k , int m)
	{
		for(int j=0;j<3;j++)
		{
			for(int i=k;i>m+1;i--)
			{
				if(g.board[i][j]== 0 || (g.board[i][j]== g.board[i-1][j]))
				{
					
					if(g.board[i][j] == 0)
					{
						if(g.board[i-1][j]== 0)
						{
							g.board[i][j] =g.board[i-2][j];
							g.board[i-1][j]=0;
							g.board[i-2][j]=0;
							break;
						}
						else
						{
							if(g.board[i-1][j] == g.board[i-2][j])
							{
								g.board[i][j] = g.board[i-1][j] + g.board[i-2][j];
								g.board[i-1][j]=0;
								g.board[i-2][j]=0;
								break;
							}
							else
							{
								g.board[i][j]=g.board[i-1][j];
								g.board[i-1][j]=g.board[i-2][j];
								g.board[i-2][j]=0;
								break;
							}
						}
					}
					else if((g.board[i][j]== g.board[i-1][j]))
					{	g.board[i][j] += g.board[i-1][j];
						g.board[i-1][j] = g.board[i-2][j];
						g.board[i-2][j] = 0;
						break;
					}
				}
				else
				{
					if(g.board[i-1][j] == 0)
					{
						g.board[i-1][j]=g.board[i-2][j];
						g.board[i-2][j]=0;
						if((g.board[i][j]== g.board[i-1][j]))
						{	
							g.board[i][j] += g.board[i-1][j];
							g.board[i-1][j] = g.board[i-2][j];
							break;
						}
						break;
					}
					if(g.board[i-1][j] == g.board[i-2][j])
					{
						g.board[i-1][j] += g.board[i-2][j];
						g.board[i-2][j] =0;
						break;
					}
					
				}
			}
		}
	}
	public void up(int k , int m)
	{
		for(int j=0;j<3;j++)
		{
			for(int i=k;i<m-1;i++)
			{
				if(g.board[i][j]== 0 || (g.board[i][j]== g.board[i+1][j]))
				{
					
					if(g.board[i][j] == 0)
					{
						if(g.board[i+1][j]== 0)
						{
							g.board[i][j] =g.board[i+2][j];
							g.board[i+1][j]=0;
							g.board[i+2][j]=0;
							break;
						}
						else
						{
							if(g.board[i+1][j] == g.board[i+2][j])
							{
								g.board[i][j] = g.board[i+1][j] + g.board[i+2][j];
								g.board[i+1][j]=0;
								g.board[i+2][j]=0;
								break;
							}
							else
							{
								g.board[i][j]=g.board[i+1][j];
								g.board[i+1][j]=g.board[i+2][j];
								g.board[i+2][j]=0;
								break;
							}
						}
					}
					if((g.board[i][j]== g.board[i+1][j]))
					{	g.board[i][j] += g.board[i+1][j];
						g.board[i+1][j] = g.board[i+2][j];
						g.board[i+2][j] = 0;
						break;
					}
				}
				else
				{
					if(g.board[i+1][j] == 0)
					{
						g.board[i+1][j]=g.board[i+2][j];
						g.board[i+2][j]=0;
						if((g.board[i][j]== g.board[i+1][j]))
						{	
							g.board[i][j] += g.board[i+1][j];
							g.board[i+1][j] = g.board[i+2][j];
							break;
						}
						break;
					}
					if(g.board[i+1][j] == g.board[i+2][j])
					{
						g.board[i+1][j] += g.board[i+2][j];
						g.board[i+2][j] =0;
						break;
					}
					
				}
			}	
		}
	}
	
	public void setup()
	{
		for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(g.board[i][j]== 0)
					{
						jl[i][j].setIcon(new ImageIcon("0_.png"));
					}
					else if(g.board[i][j]== 2)
					{
						jl[i][j].setIcon(new ImageIcon("2_.png"));
					}
					else if(g.board[i][j]== 4)
					{
						jl[i][j].setIcon(new ImageIcon("4_.png"));
					}
					else if(g.board[i][j]== 8)
					{
						jl[i][j].setIcon(new ImageIcon("8_.png"));
					}
					else if(g.board[i][j]== 16)
					{
						jl[i][j].setIcon(new ImageIcon("16.png"));
					}
					else if(g.board[i][j]== 32)
					{
						jl[i][j].setIcon(new ImageIcon("32.png"));
					}
					else if(g.board[i][j]== 64)
					{
						jl[i][j].setIcon(new ImageIcon("64.png"));
					}
					else if(g.board[i][j]== 128)
					{
						jl[i][j].setIcon(new ImageIcon("128.png"));
					}
					else if(g.board[i][j]== 256)
					{
						jl[i][j].setIcon(new ImageIcon("256.png"));
					}
					else if(g.board[i][j]== 512)
					{
						jl[i][j].setIcon(new ImageIcon("512.png"));
					}
					else if(g.board[i][j]== 1024)
					{
						jl[i][j].setIcon(new ImageIcon("1024.png"));
					}
					else if(g.board[i][j]== 2048)
					{
						jl[i][j].setIcon(new ImageIcon("2048.png"));
					}
					
				}
			}
	}
}

