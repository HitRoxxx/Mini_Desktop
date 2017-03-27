import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;

class DemoFrame extends JFrame implements ActionListener
{
	Button b[]= new Button [20];
	TextField tf;
	String[] str={"+/-","^","%","CE","7","8","9","/","4","5","6","X","1","2","3","-","0",".","=","+"};
	String str1="",result ="" ,answer="",temp="";
	Font fo1 ,fo2;
	int flagArthmetic =0,flagResult=0,percentButton=0, decimalCheck=0, numberCheck =0,negateCheck=0,negateCheck1=0;
	Label lb;
	int s,e;
	NumberFormat nf = new DecimalFormat("##.###");
	DemoFrame(String s)
	{
		super(s);
		fo1 =new Font("Times New Roman",Font.BOLD,55);
		fo2 =new Font("Times New Roman",Font.BOLD,20);
		tf =new TextField();
		tf.setBounds(40,60,320,60);
		tf.setFont(fo1);
		//tf.setEnabled(false);
		tf.setEditable(false);
		//tf.setHorizontalAlignment(TextField.RIGHT);
		add(tf);
		lb = new Label("");
		lb.setBounds(40,30,300,40);
		lb.setFont(fo2);
		add(lb);
		for(int i=0,k=0;i<5;i++)
		{
			for(int j=0;j<4;j++,add(b[k++]))
			{	
				b[k]=new Button(str[k]);
				b[k].setBounds((40+(j*83)),(150+(i*55)),70,40);
				b[k].addActionListener(this);
				if(k==18)
				{
					b[k].setBackground(Color.red);
					b[k].setForeground(Color.white);
				}
				if(k<4 || k==7 || k==11||k==15||k==19)
				{
					b[k].setBackground(Color.gray);
					b[k].setForeground(Color.white);
				}	
			}
			
		}
		
		//addWindowListener(new WindowEventListener());
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		//setLocationRelativeTo(null);
		setSize(400,450);
		setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e)
	{
		
		check(e);		
		
	}
	
	public void check(ActionEvent e)
	{
		
		for(int i=0;i<str.length;i++)
		{
			if(i>3 && i!=3 && i!=7 && i!=11 && i!=15 && i!=19 && i!=18 )
			{	
				if(e.getSource() == b[i] )
				{
					if((i== 17 && decimalCheck == 0) ||(i!= 17))
					{
						if(percentButton==0 )
						{	
							if(flagResult == 1)
							{
								str1="";
								lb.setText("");
								flagArthmetic=0;
								flagResult=0;
								numberCheck =0;
								b[3].setLabel("CE");
								str1 +=str[i];
								tf.setText(str1);
								System.out.println (numberCheck);
							}
							else
							{
								
								flagArthmetic=0;
								numberCheck=0;
								negateCheck1=0;
								str1 +=str[i];
								tf.setText(str1);
							}
							if(i== 17)
							{
								decimalCheck = 1;
								numberCheck =1;
								System.out.println (numberCheck);
							}
						}
						if(str1.length()>=10)
						{
							tf.setFont(fo2);
						}
					}
				}
			}
			else
			{	
				if(e.getSource() == b[i] && i==2 )
				{
					if(str1.length() > 0 && numberCheck == 0)
					{
						if((flagArthmetic==0 || flagResult ==1 )&& negateCheck1 == 0)
						{	if(negateCheck ==1 )
							{
								str1 += ")";
								negateCheck=0;
							}
							flagArthmetic=0;
							flagResult=0;
							percentButton=1;
							decimalCheck =0;
							str1 +=str[i];
							tf.setText(str1);
						}
					}
					if(str1.length()>=10)
					{
						tf.setFont(fo2);
					}
					
				}
				else
				{		
					if(e.getSource() == b[i] && i==0 )
					{
						if(flagResult == 1)
						{
								str1="";
								lb.setText("");
								numberCheck=0;
						}
						if(str1.length() == 0)
						{
							flagArthmetic =1;
							flagResult=0;
							str1 +="-";
							tf.setText(str1);
							negateCheck1=1;
							
						}
						else
						{
							if(str1.length() > 0 && numberCheck == 0 && negateCheck ==0 && negateCheck1 == 0)
							{
								if(flagArthmetic==1)
								{	
									flagArthmetic=0;	
									str1 +="(-";
									tf.setText(str1);
									negateCheck=1;
								}
							}
							if(str1.length()>=10)
							{
								tf.setFont(fo2);
							}
						}
					}
					
					else
					{
						
							if(e.getSource() == b[i] && i!=18 && i!=3 )
							{
								if(str1.length() > 0 && numberCheck == 0 && negateCheck1 == 0)
								{
									if(flagArthmetic==0 || flagResult ==1)
									{	
										if(negateCheck ==1 )
										{
											str1 += ")";
											negateCheck=0;
										}
										flagArthmetic=1;
										flagResult=0;
										str1 +=str[i];
										tf.setText(str1);
										percentButton=0 ;
										decimalCheck=0;
									}
								
									if(str1.length()>=10)
									{
										tf.setFont(fo2);
									}
								}
							}
						
						if(e.getSource() == b[i]&& i!=3)
						{
							if(str1.length() >0 && numberCheck == 0 && negateCheck1 == 0)
							{
								int aC = aCount();
								if(aC == 1)
								{
									if(flagArthmetic==0)
									{	
										if(negateCheck ==1 )
										{	
												str1 += ")";
												negateCheck=0;
										}
										result =str1;
										lb.setText(result+str[i]);
										tf.setFont(fo1);
										flagArthmetic=0;
										tf.setText(calculation());
										flagResult=1;
										b[3].setLabel("AC");
										str1=answer;
										percentButton=0;
										decimalCheck=0;
										
									}
								}
							}
						}
						else
						{
							if(e.getSource() == b[i] && i== 3)
							{
								if(flagResult==1)
								{	
									lb.setText("");
									tf.setText("");
									answer="";
									str1="";
									flagArthmetic=0;
									flagResult=0;
									percentButton=0;
									decimalCheck=0;
									b[3].setLabel("CE");
								}
								else
								{	if(str1.length() > 0)
									{	
										
										char c[] = str1.toCharArray();
										int m = str1.length();
										str1="";
										int n =m-1;
										
										if(negateCheck ==1 )
										{	
											if(c[m-1] == '-')
											{
												n=m-2;
											}
											negateCheck=0;
											flagArthmetic=1;
										}
										for(int j=0;j<n;j++)
										{
											str1 += c[j];
										}
										
										if(c[m-1] == '+'||c[m-1] == 'X'||c[m-1] == '-'||c[m-1] == '/'||c[m-1] == '^'||c[m-1] == '%'|| c[m-1]== '.')
										{
											flagArthmetic=0;
											decimalCheck=0;
											for(int l=m-2;l>0;l--)
											{
													if(str1.charAt(l) == 'X'|| str1.charAt(l) == '+' || str1.charAt(l) == '-'|| str1.charAt(l) == '/'|| str1.charAt(l) == '^'|| str1.charAt(l) == '%') 
													{
														break;
													}
													else
													{
														 if( str1.charAt(l)== '.');
														 {
															 decimalCheck=1;
															 break;
														 }
													}
											}
											if(str1.length() > 1)
											{
												if(c[m-2]== '%')
												{
													percentButton =1;
												}
											}
										}
										if(str1.length() >1)
										{	
											if(c[m-2]== '.')
											{
												numberCheck =1;
												System.out.println (numberCheck+"  werf" );
											}
										}
										if(c[0]== '.')
											numberCheck =1;
										if(c[0]== '-')
											negateCheck1=1;
										b[3].setLabel("CE");
										tf.setText(str1);
									}
								}
							}
						}
					}
				}
			}
		}
		
	}
	public String calculation()
	{	
		Double x, y,z;
		for(int i=0;i<str1.length();i++)
		{	
			if( str1.charAt(i) == '(')
			{
				for( e= i+2;e<str1.length();e++)
				{
					if(str1.charAt(e) == ')') 
					{
						break;
					}
					else
					{	
						temp +=  str1.charAt(e);
					}
				}
				y = Double.parseDouble(temp);
				answer = nf.format(y);
				String negate = "~"+answer;
				s=i-1;
				e++;
				temp ="";
				str1 = consize(negate);
				System.out.println(str1);
				i=0;			
			}
		}
		
		for(int i=0;i<str1.length();i++)
		{	int flag = 0;
			if( str1.charAt(i) == '%')
			{
				e= i+1;
				y = 100.00 ;
				
				temp="";
				for( s= i-1;s>=0;s--)
				{	
					if(str1.charAt(s) == 'X'|| str1.charAt(s) == '+' || str1.charAt(s) == '-'|| str1.charAt(s) == '/'|| str1.charAt(s) == '^') 
					{
						break;
					}
					else
					{
						if(str1.charAt(s)== '~')
						{
							flag =1;
						}
						else
							temp +=  str1.charAt(s);
					}
				}
				temp = reverse(temp);
				x = Double.parseDouble(temp);
				temp="";
				if (flag == 1)
				{
					y =y *(-1.00);
					flag =0;
				}
				z=  x/y;
				answer=nf.format(z);
				System.out.println(answer+"de");
				str1 = consize(answer);
				i=0;
			}	
		}
		for(int i=0;i<str1.length();i++)
		{	int flag =0;
			if( str1.charAt(i) == 'X' || str1.charAt(i) == '/' || str1.charAt(i)== '^')
			{
				for( e= i+1;e<str1.length();e++)
				{
					if((str1.charAt(e) == 'X'|| str1.charAt(e) == '+' || str1.charAt(e) == '/'||str1.charAt(e) == '^') ||(str1.charAt(e) == '-' && e !=(i+1) )) 
					{
						
						break;
					}
					else
					{	if(str1.charAt(e)== '~')
						{
							flag= 1;
							
						}
						else
						{
							temp +=  str1.charAt(e);
							
						}
					}
				}
				System.out.println(temp);
				y = Double.parseDouble(temp);
				System.out.println(y);
				if (flag == 1)
				{
					y =y *(-1.00);
					flag =0;
				}
				System.out.println(y);
				temp="";
				for( s= i-1;s>=0;s--)
				{	
					if((str1.charAt(s) == 'X'|| str1.charAt(s) == '+' || str1.charAt(s) == '/'||str1.charAt(s) == '^') ||(str1.charAt(s) == '-' && s !=(i+1) )) 
					{
						break;
					}
					else
					{
						if(str1.charAt(s)== '~')
						{
							flag =1;
						}
						else
							temp +=  str1.charAt(s);
					}
				}
				temp = reverse(temp);
				x = Double.parseDouble(temp);
				if (flag == 1)
				{
					x *=-1.00;	
				}
				temp="";
				System.out.println(x+" thdht" );
				if(str1.charAt(i) == 'X')
				{
					z=  x*y;
					answer=nf.format(z);
					//answer =Double.toString(z);
					System.out.println(answer+"mul");
					str1 = consize(answer);
					i=0;
				}
				if(str1.charAt(i) == '/')
				{
					if(y!= 0)
					{
						System.out.println(x+"   "+y);
						z=  x/y;
						answer=nf.format(z);
						System.out.println(answer+"de");
						str1 = consize(answer);
						i=0;
				
					}
					else
					{
						answer ="error";
						str1 = answer;
					}
						
				}
				if(str1.charAt(i) == '^')
				{
					z = Math.pow(x, y);
					answer=nf.format(z);
					str1 = consize(answer);
					i=0;
				}
				
				
				
			}
		}	
		for(int i=0;i<str1.length();i++)
		{	int flag = 0;
				if( str1.charAt(i) == '+' || str1.charAt(i) == '-')
				{
					System.out.println(i +"jsfuyjfdvjsfhjdyfsjydj");
					for( e= i+1;e<str1.length();e++)
					{
						if(str1.charAt(e) == '+' ||  (str1.charAt(e) == '-' && e !=(i+1) )) 
						{
							break;
						}
						else
						{	
							if(str1.charAt(e)== '~')
							{
								flag =1;
							}
							else
								temp +=  str1.charAt(e);
						}
					}
					y = Double.parseDouble(temp);
					if (flag == 1)
					{
						y =y *(-1.00);
						flag =0;
					}
					temp="";
					
						for( s= i-1;s>=0;s--)
						{	
							if( str1.charAt(s) == '+' || str1.charAt(s) == '-') 
							{
								break;
							}
							else
							{
								if(str1.charAt(s)== '~')
								{
									flag =1;
								}
								else
									temp +=  str1.charAt(s);
							}
						}
						if(temp != "")
						{
							temp = reverse(temp);
							x = Double.parseDouble(temp);
							if (flag == 1)
							{
								x *=-1.00;	
							}
							System.out.println(x +"   123");
						}
						else
						{
							x=0.0;
							System.out.println(x +"jsfuyjfdvjsfhjdyfsjydj");
						}
						if(str1.charAt(0)== '-')
						{
							if(x!= 0)
							{
								x *= -1.00;
								System.out.println(x +"   negate");
							}
							s--;
						}
						temp="";
						if(str1.charAt(i) == '+')
						{
							z=  x+y;
							answer=nf.format(z);
							System.out.println(answer+"sum   " + x +" "+ y);
							//str1 = consize(answer);
							System.out.println(str1+"jhfjkvjkfjfjhfjmfjdgfxngfjtmh");
							
						}
						if(str1.charAt(i) == '-')
						{
							z=x-y;
							answer=nf.format(z);
							System.out.println(answer+"sub1");
							//str1 = consize(answer);
							
						}	
						str1 = consize(answer);
						i=1	;			
				}
			}
		
			
		
		if(answer.length()>=10)
		{
			tf.setFont(fo2);
		}
		
		return str1;
	}

	String reverse(String temp)
	{
		int m = temp.length();
		char[] arr = temp.toCharArray();
		for(int i=0;i<temp.length()/2;i++)
		{
			char c = temp.charAt(i);
			arr[i]=temp.charAt(m-1);
			arr[m-1]= c;
			m--;
		}
		return new String(arr);
	}
	
	String consize(String an)
	{
		char c[] = str1.toCharArray();
		String str3="";
		for(int i=0;i<=s;i++)
		{	
			str3 +=str1.charAt(i);
		}
		str3 +=an;
		for(int i=e;i<str1.length();i++)
		{	
			str3 +=str1.charAt(i);
		}
		return str3;
	}
	
	public int aCount()
	{
		String st="+-X/%^";
		for(int i=0;i<str1.length();i++)
		{
			for(int j =0;j<st.length();j++)
			{
				if(str1.charAt(i) == st.charAt(j))
				{
					return 1;
					
				}
			}
		}
		return 2;
		
	}
			
				
	
}


	
