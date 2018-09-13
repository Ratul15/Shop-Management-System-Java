import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AdminLogin extends JFrame implements ActionListener
{
	ImageIcon ic1=new ImageIcon("index.jpg");
	private JLabel labelimage=new JLabel(ic1);
	
	ImageIcon ic2=new ImageIcon("indexl.gif");
	private JLabel label=new JLabel(ic2);
	private AddProducts paneladdProducts = new AddProducts();
	//private SellProduct sellproduct;
	private ManageProduct manageProducts = new ManageProduct();
	private ManageCustomer managecustomer = new ManageCustomer();
	private SalesReport salesReport = new SalesReport();
	private Inventory inventory = new Inventory();
	//private BackupDataBase backupDataBase = new BackupDataBase();
	private ManageEmployee manageEmployee = new ManageEmployee();
	//private AddCustomer addCustomer = new AddCustomer();
	
	Container c1;	
	ImageIcon ic=new ImageIcon("logout.png");
	JButton Logout = new JButton(ic);
	
	
	JButton btnAddProduct = new JButton("AddProduct");
	//JButton btnAddCustomer = new JButton("AddCustomer");
	//JButton btnSellProduct = new JButton("SellProduct");
	JButton btnSalesReport = new JButton("SalesReport");
	JButton btnManageProduct = new JButton("ManageProduct");
	JButton btnManageEmployee = new JButton("ManageEmployee");
	JButton btnManageCustomer = new JButton("ManageCustomer");
	JButton btnInventory = new JButton("Inventory");
	//JButton btnBackupDataBase = new JButton("Backup DataBase");
	
	JPanel panelButton = new JPanel();
	JPanel panellogoutButton=new JPanel();
	JPanel panelMainPanel = new JPanel();
	JPanel panelAddProduct = new JPanel();
	//JPanel panelAddCustomer = new JPanel();
	//JPanel panelSellProduct = new JPanel();
	JPanel panelSalesReport = new JPanel();
	JPanel panelManageProduct = new JPanel();
	JPanel panelManageEmployee = new JPanel();
	JPanel panelManageCustomer = new JPanel();
	JPanel panelInventory = new JPanel();
	//JPanel panelBackupdatabase = new JPanel();
	
	private String userName;
	
	public AdminLogin(String userName)
	{
		this.userName = userName;
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 700));
		this.setLocationRelativeTo(null);
		this.setTitle("Admin Login Page");
		this.setResizable(true);
		//sellproduct= new SellProduct(userName);
		panelButton.setBackground(Color.black);
			
		c1= this.getContentPane();
		c1.setLayout(new BorderLayout());
		c1.setBackground(Color.RED);
	    
		Logout.setBackground(new Color(66, 226, 240));
		Logout.setBorder(null);
	    Logout.addActionListener(this);
		
		
	    panelMainPanel.setLayout(new BorderLayout());
		panelAddProduct.setLayout(new BorderLayout());
		//panelAddCustomer.setLayout(new BorderLayout());
		//panelSellProduct.setLayout(new BorderLayout());
		panelSalesReport.setLayout(new BorderLayout());
		panelManageProduct.setLayout(new BorderLayout());
		panelManageEmployee.setLayout(new BorderLayout());
		panelManageCustomer.setLayout(new BorderLayout());
		panelInventory.setLayout(new BorderLayout());
		//panelBackupdatabase.setLayout(new BorderLayout());
			
		
		
	    this.setVisible(true);
		panellogoutButton.setLayout(new BorderLayout());
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
		panelButton.add(btnAddProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		//panelButton.add(btnAddCustomer);
		//panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		//panelButton.add(btnSellProduct);
		//panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		panelButton.add(btnSalesReport);
		panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		panelButton.add(btnManageProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		panelButton.add(btnManageEmployee);
		panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		panelButton.add(btnManageCustomer);
		panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		panelButton.add(btnInventory);
		panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		//panelButton.add(btnBackupDataBase);
		//panelButton.add(Box.createRigidArea(new Dimension(0, 40)));
		
		panellogoutButton.add(Logout, BorderLayout.EAST);
		c1.add(panellogoutButton, BorderLayout.PAGE_START);
		c1.add(panelButton, BorderLayout.WEST);
		panelMainPanel.add(labelimage,BorderLayout.CENTER);
		panelMainPanel.add(label,BorderLayout.PAGE_START);
		
		label.setFont(new Font("Times New Roamn", Font.BOLD, 30));
		
		c1.add(panelMainPanel, BorderLayout.CENTER);
		
		
		btnAddProduct.addActionListener(this);
		//btnAddCustomer.addActionListener(this);
		//btnSellProduct.addActionListener(this);
		btnSalesReport.addActionListener(this);
		btnManageProduct.addActionListener(this);
		btnManageEmployee.addActionListener(this);
		btnManageCustomer.addActionListener(this);
		btnInventory.addActionListener(this);
		//btnBackupDataBase.addActionListener(this);
		
		}
	public void actionPerformed(ActionEvent ud){
		if(ud.getSource() == Logout){
			
			 new LoginFrame();
			this.setVisible(false);
		}
		if(ud.getSource() == btnAddProduct){
			
			panelMainPanel.removeAll();
			panelMainPanel.add(paneladdProducts, BorderLayout.CENTER);
			repaint();
			revalidate();
			
		}
           /*if(ud.getSource() == btnAddCustomer){
			
			panelMainPanel.removeAll();
			panelMainPanel.add(addCustomer, BorderLayout.CENTER);
			repaint();
			revalidate();
			
		}*/
		/*if(ud.getSource() == btnSellProduct){
			panelMainPanel.removeAll();
			panelMainPanel.add(sellproduct, BorderLayout.CENTER);
			repaint();
			revalidate();
			
		}*/
		if(ud.getSource() == btnSalesReport){
			panelMainPanel.removeAll();
			panelMainPanel.add(salesReport, BorderLayout.CENTER);
			repaint();
			revalidate();
			
		}
		if(ud.getSource() == btnManageProduct){
			panelMainPanel.removeAll();
			panelMainPanel.add(manageProducts, BorderLayout.CENTER);
			repaint();
			revalidate();
			
		}
		if(ud.getSource() == btnManageEmployee){
			panelMainPanel.removeAll();
			panelMainPanel.add(manageEmployee, BorderLayout.CENTER);
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
       /*if(ud.getSource() == btnBackupDataBase){
    		
  	     panelMainPanel.removeAll();
  	     panelMainPanel.add(backupDataBase, BorderLayout.CENTER);
  	     repaint();
  	     revalidate();
  	
  }*/
	}
	
	    
}



