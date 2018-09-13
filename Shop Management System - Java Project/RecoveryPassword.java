import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RecoveryPassword extends JFrame implements ActionListener
{
 
	Container c;
	JLabel userlabel=new JLabel("UserName");
	JLabel emaillabel=new JLabel("Email");
	JTextField ut=new JTextField();
	JTextField et=new JTextField();
 
	
	JButton Ok=new JButton("Get Password");
	

	JButton back=new JButton("Back");
	
	RecoveryPassword()
	{
		c=this.getContentPane();
		c.setLayout(null);
		
		userlabel.setBounds(390,70,350,40);
		emaillabel.setBounds(390,170,350,40);
		
		Font f=new Font("Times New Roman",Font.BOLD,20);
	    userlabel.setFont(f);
		emaillabel.setFont(f);
		ut.setFont(f);
		et.setFont(f);
		
		
		ut.setBounds(490,70,150,40);
		et.setBounds(490,170,150,40);
		Ok.setBounds(475,240,150,46);
		back.setBounds(10,0,72,71);
		
		c.add(userlabel);
		c.add(emaillabel);
		c.add(ut);
		c.add(et);
		c.add(Ok);
		c.add(back);
		
		back.addActionListener(this);
		
		ImageIcon ic=new ImageIcon("forgotpassword.jpg");
		JLabel label =new JLabel(ic);
		label.setBounds(40,75,283,208);
		c.add(label);
		
		c.setBackground(Color.ORANGE);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(300,150,750,500);
		this.setTitle("RecoveryPassword Page");
		
		
		this.setResizable(false);
			
			
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==back)
		{
			new LoginFrame();
			this.setVisible(false);
		}
	}
	
	
}
