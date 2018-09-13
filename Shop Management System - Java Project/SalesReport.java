import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class SalesReport extends JPanel{
	private JLabel lblMenuName = new JLabel("Sales Report Menu", SwingConstants.CENTER);
	private String [] COLUMN_HEADER = {"Invoice Number","Customer Name", "Employee Name", "Buy Date", "Product Name", "Quantity", "Per Unit Price", "Total Price"};
	private String [] [] COLUMN_VALUE = {};
	
	private JPanel panelLebel = new JPanel();
	private JPanel panelInput = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JPanel panelCenter=new JPanel();
	private JPanel paneltxtPanel = new JPanel();
	
	private JScrollPane scrollPane;
	
	private JLabel lblSearch = new JLabel("Search Text: ");
	private JLabel lblCustomerId = new JLabel("Search Type: ");
	private JLabel lblinvoiceNumber=new  JLabel("Invoice Number: ");
	
	private JTextField txtSearch = new JTextField();
	private JComboBox<String> comboBoxCatagory = new JComboBox<String>();
	
	private JButton btndeleteTransection = new JButton("Delete Transistion");
	private JButton btnLoad = new JButton("Load Data");
	
	private DefaultTableModel model = new DefaultTableModel(COLUMN_VALUE, COLUMN_HEADER);
	private JTable tableproductInfo = new JTable(model);
	
	public  SalesReport() 
	{
		setVisible(true);
		setLayout(new BorderLayout());
		
		panelCenter.setLayout(new BorderLayout());
		panelInput.setLayout(new BorderLayout());
		panelLebel.setLayout(new BoxLayout(panelLebel, BoxLayout.Y_AXIS));
		paneltxtPanel.setLayout(new BoxLayout(paneltxtPanel, BoxLayout.Y_AXIS));
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tableproductInfo);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		comboBoxCatagory.addItem("Invoice Number");
		comboBoxCatagory.addItem("Customer Name");
		comboBoxCatagory.addItem("Employee Name");
		comboBoxCatagory.addItem("Product Name");
		
		txtSearch.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			public void keyReleased(KeyEvent arg0) {
				if (comboBoxCatagory.getSelectedItem().equals("Invoice Number")) {
					if(txtSearch.getText() == ""){
						txtSearch.setText("0");
					}
					
					String query = txtSearch.getText();
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `sales_report_view` where invoice_number = " + query +"";     
			        Connection con=null;//for connection
			        Statement st = null;//for query execution
			        ResultSet rs = null; /// for getting data
			        try
					{
						Class.forName("com.mysql.jdbc.Driver");//load driver
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
						st = con.createStatement();//create statement
						rs = st.executeQuery(queryShow);//getting result
						
						while(rs.next())
						{
							String invoice = rs.getString("invoice_number");
						    String customerName = rs.getString("customer_name");
						    String employeeName = rs.getString("employee_name");
						    String buyDate = rs.getString("buy_date");
						    String productName = rs.getString("product_name");
						    String quantity = rs.getString("quantity");
						    String perUnitPrice = rs.getString("per_unit_price");
						    String totalPrice = rs.getString("total_price");
						    model.addRow(new Object[]{invoice, customerName, employeeName, buyDate, productName, quantity, perUnitPrice, totalPrice});
								
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
			            	if(rs != null)
			            	{
			            		rs.close();
			            	}
			            	
			                if(st!=null)
								st.close();

			                if(con!=null)
								con.close();
			            }
			            catch(Exception ex1){}
					}
				}else if(comboBoxCatagory.getSelectedItem().equals("Customer Name")) {
					
					if(txtSearch.getText() == ""){
						return;
					}
					
					String query = txtSearch.getText();
					
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `sales_report_view` where customer_name like '%" + query +"%'";     
			        Connection con=null;//for connection
			        Statement st = null;//for query execution
			        ResultSet rs = null; /// for getting data
			        try
					{
						Class.forName("com.mysql.jdbc.Driver");//load driver
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
						st = con.createStatement();//create statement
						rs = st.executeQuery(queryShow);//getting result
						
						while(rs.next())
						{
							String invoice = rs.getString("invoice_number");
						    String customerName = rs.getString("customer_name");
						    String employeeName = rs.getString("employee_name");
						    String buyDate = rs.getString("buy_date");
						    String productName = rs.getString("product_name");
						    String quantity = rs.getString("quantity");
						    String perUnitPrice = rs.getString("per_unit_price");
						    String totalPrice = rs.getString("total_price");
						    model.addRow(new Object[]{invoice, customerName, employeeName, buyDate, productName, quantity, perUnitPrice, totalPrice});
								
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
			            	if(rs != null)
			            	{
			            		rs.close();
			            	}
			            	
			                if(st!=null)
								st.close();

			                if(con!=null)
								con.close();
			            }
			            catch(Exception ex1){}
					}
				}else if(comboBoxCatagory.getSelectedItem().equals("Employee Name")) {
					
					if(txtSearch.getText() == ""){
						return;
					}
					
					String query = txtSearch.getText();
					
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `sales_report_view` where employee_name like '%" + query +"%'";     
			        Connection con=null;//for connection
			        Statement st = null;//for query execution
			        ResultSet rs = null; /// for getting data
			        try
					{
						Class.forName("com.mysql.jdbc.Driver");//load driver
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
						st = con.createStatement();//create statement
						rs = st.executeQuery(queryShow);//getting result
						
						while(rs.next())
						{
							String invoice = rs.getString("invoice_number");
						    String customerName = rs.getString("customer_name");
						    String employeeName = rs.getString("employee_name");
						    String buyDate = rs.getString("buy_date");
						    String productName = rs.getString("product_name");
						    String quantity = rs.getString("quantity");
						    String perUnitPrice = rs.getString("per_unit_price");
						    String totalPrice = rs.getString("total_price");
						    model.addRow(new Object[]{invoice, customerName, employeeName, buyDate, productName, quantity, perUnitPrice, totalPrice});
								
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
			            	if(rs != null)
			            	{
			            		rs.close();
			            	}
			            	
			                if(st!=null)
								st.close();

			                if(con!=null)
								con.close();
			            }
			            catch(Exception ex1){}
					}
				}else if(comboBoxCatagory.getSelectedItem().equals("Product Name")) {
					
					if(txtSearch.getText() == ""){
						return;
					}
					
					String query = txtSearch.getText();
					
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `sales_report_view` where product_name like '%" + query +"%'";     
			        Connection con=null;//for connection
			        Statement st = null;//for query execution
			        ResultSet rs = null; /// for getting data
			        try
					{
						Class.forName("com.mysql.jdbc.Driver");//load driver
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
						st = con.createStatement();//create statement
						rs = st.executeQuery(queryShow);//getting result
						
						while(rs.next())
						{
							String invoice = rs.getString("invoice_number");
						    String customerName = rs.getString("customer_name");
						    String employeeName = rs.getString("employee_name");
						    String buyDate = rs.getString("buy_date");
						    String productName = rs.getString("product_name");
						    String quantity = rs.getString("quantity");
						    String perUnitPrice = rs.getString("per_unit_price");
						    String totalPrice = rs.getString("total_price");
						    model.addRow(new Object[]{invoice, customerName, employeeName, buyDate, productName, quantity, perUnitPrice, totalPrice});
								
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
			            	if(rs != null)
			            	{
			            		rs.close();
			            	}
			            	
			                if(st!=null)
								st.close();

			                if(con!=null)
								con.close();
			            }
			            catch(Exception ex1){}
					}
				}
			}
			
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		
		addToGUI();
		btnListeners();
		
	}

	private void btnListeners() {
		btnLoad.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				String query = "SELECT * FROM `sales_report_view`";     
		        Connection con=null;//for connection
		        Statement st = null;//for query execution
		        ResultSet rs = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st = con.createStatement();//create statement
					rs = st.executeQuery(query);//getting result
					
					while(rs.next())
					{
						String invoice = rs.getString("invoice_number");
					    String customerName = rs.getString("customer_name");
					    String employeeName = rs.getString("employee_name");
					    String buyDate = rs.getString("buy_date");
					    String productName = rs.getString("product_name");
					    String quantity = rs.getString("quantity");
					    String perUnitPrice = rs.getString("per_unit_price");
					    String totalPrice = rs.getString("total_price");
					    model.addRow(new Object[]{invoice, customerName, employeeName, buyDate, productName, quantity, perUnitPrice, totalPrice});
							
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
		            	if(rs != null)
		            	{
		            		rs.close();
		            	}
		            	
		                if(st!=null)
							st.close();

		                if(con!=null)
							con.close();
		            }
		            catch(Exception ex){}
		            
		            JOptionPane.showMessageDialog(null, "All Transection Showed In Table.");
		        }
			}
		});
		
		btndeleteTransection.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(tableproductInfo.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Select a row for delete product");
					return;
				}
				
				int selectedRow = tableproductInfo.getSelectedRow();
				selectedRow = tableproductInfo.convertRowIndexToModel(selectedRow);
				String value = (String) tableproductInfo.getModel().getValueAt(selectedRow, 0);
				String query = "delete from `items` where `invoice_number` = '"+ value +"'";
				String query1 = "delete from `sales_report` where `invoice_number` = '"+ value +"'";
				String queryLoad = "SELECT * FROM `sales_report_view`";
				Connection con=null;//for connection
		        Statement st = null;//for query execution
		        ResultSet rs = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st = con.createStatement();//create statement
					st.executeUpdate(query);
					st.executeUpdate(query1);
					model.setRowCount(0);
					rs = st.executeQuery(queryLoad);//getting result
					
					while(rs.next())
					{
						String invoice = rs.getString("invoice_number");
					    String customerName = rs.getString("customer_name");
					    String employeeName = rs.getString("employee_name");
					    String buyDate = rs.getString("buy_date");
					    String productName = rs.getString("product_name");
					    String quantity = rs.getString("quantity");
					    String perUnitPrice = rs.getString("per_unit_price");
					    String totalPrice = rs.getString("total_price");
					    model.addRow(new Object[]{invoice, customerName, employeeName, buyDate, productName, quantity, perUnitPrice, totalPrice});
							
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
		            	if(rs != null)
		            	{
		            		rs.close();
		            	}
		            	
		                if(st!=null)
							st.close();

		                if(con!=null)
							con.close();
		            }
		            catch(Exception ex){}
		            
		            JOptionPane.showMessageDialog(null, "Transection Deleted.");
				}
			}
		});
	}

	private void addToGUI() {
		
		panelLebel.add(lblCustomerId);
		panelLebel.add(lblSearch);
		
		paneltxtPanel.add(comboBoxCatagory);
		paneltxtPanel.add(txtSearch);
		
		lblSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCustomerId.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblinvoiceNumber.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		panelButtons.add(btndeleteTransection);
		panelButtons.add(btnLoad);
		
		panelInput.add(panelLebel, BorderLayout.WEST);
		panelInput.add(paneltxtPanel, BorderLayout.CENTER);
		panelCenter.add(panelInput, BorderLayout.PAGE_START);
		panelCenter.add(scrollPane, BorderLayout.CENTER);
		panelCenter.add(panelButtons, BorderLayout.EAST);
		
		add(lblMenuName, BorderLayout.PAGE_START);
		add(panelCenter,BorderLayout.CENTER);
		
	}
	
	
	
	
	
}

