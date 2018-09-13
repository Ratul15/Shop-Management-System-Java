import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class EmployeeLogin extends JFrame implements ActionListener
{
	ImageIcon ic1=new ImageIcon("index2.jpg");
	private JLabel labelimage=new JLabel(ic1);
	
	ImageIcon ic2=new ImageIcon("employee2.gif");
	private JLabel label=new JLabel(ic2);
	private SellProduct sellproduct;
	private ManageCustomer managecustomer = new ManageCustomer();
	private SalesReport salesReport = new SalesReport();
	private Inventory inventory = new Inventory();
	//private ManageCustomer managecustomer = new ManageCustomer();
	private AddCustomer addcustomer = new AddCustomer();
	
	Container c1;
	
	ImageIcon ic=new ImageIcon("logout.png");
	JButton Logout = new JButton(ic);
	JButton btnAddCustomer = new JButton("AddCustomer");
	JButton btnSellProduct = new JButton("SellProduct");
	JButton btnSalesReport = new JButton("SalesReport");
	JButton btnManageCustomer = new JButton("ManageCustomer");
	JButton btnInventory = new JButton("Inventory");
	
	JPanel panelButton = new JPanel();
	JPanel panellogoutButton=new JPanel();
	JPanel panelMainPanel = new JPanel();
	JPanel panelAddCustomer = new JPanel();
	JPanel panelSellProduct = new JPanel();
	JPanel panelSalesReport = new JPanel();
	JPanel panelManageCustomer = new JPanel();
	JPanel panelInventory = new JPanel();
	
	private String userName;
	
	EmployeeLogin(String userName)
	{
		this.userName = userName;
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 700));
		this.setTitle("Employee Login Page");
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		sellproduct= new SellProduct(userName);
			c1= this.getContentPane();
			c1.setLayout(new BorderLayout());
			Logout.setBackground(new Color(66, 226, 244));
			Logout.setBorder(null);
			Logout.addActionListener(this);
			this.setVisible(true);
			
			    panelMainPanel.setLayout(new BorderLayout());
				panelAddCustomer.setLayout(new BorderLayout());
				panelSellProduct.setLayout(new BorderLayout());
				panelSalesReport.setLayout(new BorderLayout());
				panelManageCustomer.setLayout(new BorderLayout());
				panelInventory.setLayout(new BorderLayout());
				
					
				panelButton.setBackground(Color.black);
				
			    this.setVisible(true);
				panellogoutButton.setLayout(new BorderLayout());
				panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
				
				panelButton.add(Box.createRigidArea(new Dimension(0, 50)));
				panelButton.add(btnSellProduct);
				panelButton.add(Box.createRigidArea(new Dimension(0, 50)));
				panelButton.add(btnAddCustomer);
		        panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
				panelButton.add(btnSalesReport);
				panelButton.add(Box.createRigidArea(new Dimension(0, 50)));
				panelButton.add(btnManageCustomer);
				panelButton.add(Box.createRigidArea(new Dimension(0, 50)));
				panelButton.add(btnInventory);
				panelButton.add(Box.createRigidArea(new Dimension(0, 50)));
				
				panellogoutButton.add(Logout, BorderLayout.EAST);
				c1.add(panellogoutButton, BorderLayout.PAGE_START);
				c1.add(panelButton, BorderLayout.WEST);
				c1.add(panelMainPanel, BorderLayout.CENTER);
				panelMainPanel.add(labelimage,BorderLayout.CENTER);
		        panelMainPanel.add(label,BorderLayout.PAGE_START);
		
		label.setFont(new Font("Times New Roamn", Font.BOLD, 30));
				
				btnSellProduct.addActionListener(this);
				btnAddCustomer.addActionListener(this);
				btnSalesReport.addActionListener(this);
				btnManageCustomer.addActionListener(this);
				btnInventory.addActionListener(this);
				
				}
	
	public void actionPerformed(ActionEvent ud){
		if(ud.getSource() == Logout){
			
			new LoginFrame();
			this.setVisible(false);
		}
		 if(ud.getSource() == btnAddCustomer){
			
			panelMainPanel.removeAll();
			panelMainPanel.add(addcustomer, BorderLayout.CENTER);
			repaint();
			revalidate();
			
		}
		if(ud.getSource() == btnSellProduct){
			panelMainPanel.removeAll();
			panelMainPanel.add(sellproduct, BorderLayout.CENTER);
			repaint();
			revalidate();
			
		}
		if(ud.getSource() == btnSalesReport){
			panelMainPanel.removeAll();
			panelMainPanel.add(salesReport, BorderLayout.CENTER);
			repaint();
			revalidate();
		}
		 if(ud.getSource() == btnManageCustomer){
				
				panelMainPanel.removeAll();
				panelMainPanel.add(managecustomer, BorderLayout.CENTER);
				repaint();
				revalidate();
				
			}
		 if(ud.getSource() == btnInventory){
				
		     panelMainPanel.removeAll();
		     panelMainPanel.add(inventory, BorderLayout.CENTER);
		     repaint();
		     revalidate();
		
	}
		 
		 
		
			
		
		
		
	}
	}
	
	
	    

