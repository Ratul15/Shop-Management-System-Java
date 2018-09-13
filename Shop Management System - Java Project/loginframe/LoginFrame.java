package loginframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class LoginFrame extends JFrame implements ActionListener{
	Container c;
	
	private JLabel userlabel=new JLabel("UserName : ");
	private JLabel passlabel=new JLabel("PassWord : ");
	
	
	private JTextField ut=new JTextField();
	private JPasswordField pt=new JPasswordField();
	
    ImageIcon ic=new ImageIcon("loginlogo.png");
	//JLabel labelimage =new JLabel();
	private JButton log=new JButton(ic);
	
	ImageIcon ic1=new ImageIcon("cancel.png");
	private JButton can=new JButton(ic1);
	
	private JButton Fp=new JButton("Forgot Password");
		
	public LoginFrame()
	{
		c=this.getContentPane();
		c.setLayout(null);
		
		c.revalidate();
		
	    this.setVisible(true);
				
		userlabel.setBounds(300,50,110,40);
		passlabel.setBounds(300,130,110,40);
	
		
		Font f=new Font("Times New Roman",Font.BOLD,20);
	    userlabel.setFont(f);
		passlabel.setFont(f);
		
		log.setFont(f);
		ut.setFont(f);
		Fp.setFont(f);
        
		ut.setBounds(390,50,150,40);
		ut.setBackground(Color.GREY);
		pt.setBounds(390,130,150,40);
		log.setBounds(345,320,149,51);
		can.setBounds(545,320,198,51);
		Fp.setBounds(555,0,180,20);
		
		
		
		log.addActionListener(this);
		can.addActionListener(this);
		Fp.addActionListener(this);
		
		
		c.add(userlabel);
		c.add(passlabel);
		c.add(ut);
		c.add(pt);
		
		c.add(log);
		c.add(can);
		c.add(Fp);
		
		
		
		ImageIcon ic=new ImageIcon("login.jpg.PNG");
		JLabel label =new JLabel(ic);
		label.setBounds(0,0,282,276);
		c.add(label);
		
	    this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100,50,800,450);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.setTitle("Super Shop Management System");
		ImageIcon ic3=new ImageIcon("images.jpg");
		this.setIconImage(ic3.getImage());
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==log)
		{
			check();
		}
		if(ae.getSource()==Fp)
		{
			new RecoveryPassword();
			this.setVisible(false);
		}
		if(ae.getSource()==can)
		{
			System.exit(0);
		}

		
	}
	
	public void check(){
		String UserName=ut.getText();
		String passWord=pt.getText();
		
		String query = "SELECT `username`, `password`, `account_type` FROM `person`;";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root"," ");
			st = con.createStatement();//create statement
			rs = st.executeQuery(query);//getting result
					
			while(rs.next())
			{
                String userName = rs.getString("username");
                String password = rs.getString("password");
				String accountType = rs.getString("account_type");
				
				if(userName.equals(ut.getText()))
				{
					
					if(password.equals(pt.getText()))
					{
						if(accountType.equals("Admin"))
						{
							new AdminLogin(UserName);
							this.setVisible(false);
							JOptionPane.showMessageDialog(this,"You Are Loggin as Admin");
				
						}
						else if(accountType.equals("Employee"))
						{
							new EmployeeLogin(UserName);
							this.setVisible(false);
							JOptionPane.showMessageDialog(this,"You Are Loggin as Employee ");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Access Denied"); 
					}
				}			
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}
}
